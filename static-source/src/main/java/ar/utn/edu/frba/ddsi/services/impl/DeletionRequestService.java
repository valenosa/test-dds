package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.exceptions.NotFoundException;
import ar.utn.edu.frba.ddsi.exceptions.SpamException;
import ar.utn.edu.frba.ddsi.models.dtos.input.DeletionRequestEvaluationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.DeletionRequestCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.DeletionRequestOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.request.DeletionRequest;
import ar.utn.edu.frba.ddsi.models.entities.request.DeletionRequestState;
import ar.utn.edu.frba.ddsi.models.repositories.IDeletionRequestRepository;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.services.IDeletionRequestService;
import ar.utn.edu.frba.ddsi.models.entities.spamDetector.ISpamDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeletionRequestService implements IDeletionRequestService, ISpamDetector {

  @Autowired
  private IDeletionRequestRepository deletionRequestRepository;

  @Autowired
  private IEventRepository eventRepository;

  private DeletionRequestOutputDTO accept(DeletionRequestEvaluationDTO evaluation) {
    //TODO: Validar si el usuario puede realizar esta peticion

    DeletionRequest deletionRequest = deletionRequestRepository.getById(evaluation.getDeletionRequestId());
    if (deletionRequest == null)
      throw new NotFoundException("Deletion request not found - ID: " + evaluation.getDeletionRequestId());

    Event event = eventRepository.findById(deletionRequest.getEventId());
    if (event == null) throw new NotFoundException("Event not found - ID: " + deletionRequest.getEventId());

    event.markAsDeleted();
    eventRepository.save(event);

    deletionRequest.registerEvaluation(evaluation.getEvaluatorName());
    deletionRequest.setState(DeletionRequestState.ACCEPTED);

    deletionRequestRepository.save(deletionRequest);

    return DeletionRequestOutputDTO.from(deletionRequest);
  }

  private DeletionRequestOutputDTO reject(DeletionRequestEvaluationDTO evaluation) {
    //TODO: Validar si el usuario puede realizar esta peticion

    DeletionRequest deletionRequest = deletionRequestRepository.getById(evaluation.getDeletionRequestId());
    if (deletionRequest == null)
      throw new NotFoundException("Deletion request not found - ID: " + evaluation.getDeletionRequestId());

    deletionRequest.registerEvaluation(evaluation.getEvaluatorName());
    deletionRequest.setState(DeletionRequestState.REJECTED);

    deletionRequestRepository.save(deletionRequest);

    return DeletionRequestOutputDTO.from(deletionRequest);
  }

  @Override
  public boolean isSpam(long eventId, String argument) { // Nota: lo puse public porque lo requiere la interfaz.
    List<DeletionRequest> deletionRequests = deletionRequestRepository.getByEventId(eventId);

    for (DeletionRequest deletionRequest : deletionRequests) {
      if (deletionRequest.getArgument().equals(argument)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public DeletionRequestOutputDTO create(DeletionRequestCreationDTO deletionRequestCreationDTO) {

    if (isSpam(deletionRequestCreationDTO.getEventId(), deletionRequestCreationDTO.getArgument())) {
      throw new SpamException("Deletion request deleted due to spam.");
    }

    DeletionRequest deletionRequest = DeletionRequest.from(deletionRequestCreationDTO);

    deletionRequestRepository.save(deletionRequest);

    return DeletionRequestOutputDTO.from(deletionRequest);
  }

  @Override
  public DeletionRequestOutputDTO evaluate(DeletionRequestEvaluationDTO evaluation) {

    if (evaluation.isAccepted()) {
      return accept(evaluation);
    }
    return reject(evaluation);
  }
}
