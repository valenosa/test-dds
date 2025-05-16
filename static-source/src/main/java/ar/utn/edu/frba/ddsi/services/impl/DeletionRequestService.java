package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.exceptions.NotFoundException;
import ar.utn.edu.frba.ddsi.models.dtos.input.DeletionRequestEvaluationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.DeletionRequestCreationDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.request.DeletionRequest;
import ar.utn.edu.frba.ddsi.models.entities.request.DeletionRequestState;
import ar.utn.edu.frba.ddsi.models.repositories.IDeletionRequestRepository;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.services.IDeletionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletionRequestService implements IDeletionRequestService {

  @Autowired
  private IDeletionRequestRepository deletionRequestRepository;

  @Autowired
  private IEventRepository eventRepository;


  @Override
  public void accept(DeletionRequestEvaluationDTO deletionRequestEvaluationDTO) {
    //TODO: Validar si el usuario puede realizar esta peticion

    DeletionRequest deletionRequest = deletionRequestRepository.getById(deletionRequestEvaluationDTO.getDeletionRequestId());
    if (deletionRequest == null) throw new NotFoundException("Deletion request not found - ID: " + deletionRequestEvaluationDTO.getDeletionRequestId());

    Event event = eventRepository.findById(deletionRequest.getEventId());
    if (event == null) throw new NotFoundException("Event not found - ID: " + deletionRequest.getEventId());

    event.markAsDeleted();
    eventRepository.save(event);

    deletionRequest.registerEvaluation(deletionRequestEvaluationDTO.getEvaluatorName());
    deletionRequest.setState(DeletionRequestState.ACCEPTED);

    deletionRequestRepository.save(deletionRequest);
  }

  @Override
  public void reject(DeletionRequestEvaluationDTO deletionRequestEvaluationDTO) {
    //TODO: Validar si el usuario puede realizar esta peticion

    DeletionRequest deletionRequest = deletionRequestRepository.getById(deletionRequestEvaluationDTO.getDeletionRequestId());
    if (deletionRequest == null) throw new NotFoundException("Deletion request not found - ID: " + deletionRequestEvaluationDTO.getDeletionRequestId());

    deletionRequest.registerEvaluation(deletionRequestEvaluationDTO.getEvaluatorName());
    deletionRequest.setState(DeletionRequestState.REJECTED);

    deletionRequestRepository.save(deletionRequest);

  }

  @Override
  public void create(DeletionRequestCreationDTO deletionRequestCreationDTO) {
    //TODO: Validar si el usuario puede realizar esta peticion

    DeletionRequest deletionRequest = DeletionRequest.from(deletionRequestCreationDTO);

    deletionRequestRepository.save(deletionRequest);
  }
}
