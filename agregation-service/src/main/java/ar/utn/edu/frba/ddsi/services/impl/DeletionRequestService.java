package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.exceptions.NotFoundException;
import ar.utn.edu.frba.ddsi.exceptions.SpamException;
import ar.utn.edu.frba.ddsi.models.dtos.input.DeletionRequestCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.DeletionRequestEvaluationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.DeletionRequestOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import ar.utn.edu.frba.ddsi.models.entities.request.DeletionRequest;
import ar.utn.edu.frba.ddsi.models.entities.request.DeletionRequestState;
import ar.utn.edu.frba.ddsi.models.entities.sourceClient.SourceClient;
import ar.utn.edu.frba.ddsi.models.entities.spamDetector.ISpamDetector;
import ar.utn.edu.frba.ddsi.models.repositories.IDeletionRequestRepository;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.models.repositories.impl.SourceClientRepository;
import ar.utn.edu.frba.ddsi.services.IDeletionRequestService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletionRequestService implements IDeletionRequestService {

  @Autowired
  private IDeletionRequestRepository deletionRequestRepository;

  @Autowired
  private IEventRepository eventRepository;

  @Autowired
  private ISpamDetector spamDetector;

  @Autowired
  SourceClientRepository sourceClientRepository;

  private DeletionRequestOutputDTO accept(DeletionRequestEvaluationDTO evaluation) {
    //TODO: validate that user can do this petition.

    DeletionRequest deletionRequest = deletionRequestRepository.getById(evaluation.getDeletionRequestId());
    if (deletionRequest == null)
      throw new NotFoundException("Deletion request not found - ID: " + evaluation.getDeletionRequestId());

    Event event = eventRepository.findById(deletionRequest.getEventId());
    if (event == null) throw new NotFoundException("Event not found - ID: " + deletionRequest.getEventId());

    event.markAsDeleted();

    deletionRequest.registerEvaluation(evaluation.getEvaluatorName());
    deletionRequest.setState(DeletionRequestState.ACCEPTED);

    // Update in the origin source.
    SourceClient sourceClient = sourceClientRepository.findById(event.getSourceClientId());
    if (sourceClient == null) throw new NotFoundException("Source Client not found - ID: " + event.getSourceClientId());

    if (sourceClient.getType() != Origin.PROXY) {
      try {
        sourceClient.getWebClient().delete().uri("/events/" + event.getInSourceEventId())
            .retrieve()
            .bodyToMono(Void.class)
            .block();
      } catch (Exception e) {
        throw new RuntimeException("Error updating event in source origin: " + e.getMessage(), e);
      }
    }

    eventRepository.save(event);
    deletionRequestRepository.save(deletionRequest);

    return DeletionRequestOutputDTO.from(deletionRequest);
  }

  private DeletionRequestOutputDTO reject(DeletionRequestEvaluationDTO evaluation) {
    //TODO: validate that user can do this petition.

    DeletionRequest deletionRequest = deletionRequestRepository.getById(evaluation.getDeletionRequestId());
    if (deletionRequest == null) throw new NotFoundException("Deletion request not found - ID: " + evaluation.getDeletionRequestId());

    deletionRequest.registerEvaluation(evaluation.getEvaluatorName());
    deletionRequest.setState(DeletionRequestState.REJECTED);

    deletionRequestRepository.save(deletionRequest);

    return DeletionRequestOutputDTO.from(deletionRequest);
  }

  @Override
  public DeletionRequestOutputDTO create(DeletionRequestCreationDTO drDTO) {

    // Get a List of the DRs of the same event.
    List<DeletionRequest> eventDeletionRequests = deletionRequestRepository.getByEventId(drDTO.getEventId());

    // Check spam.
    if (spamDetector.isSpam(eventDeletionRequests, drDTO.getArgument()) && !eventDeletionRequests.isEmpty()) {
      throw new SpamException("Error: deletion request denied due to spam.");
    }

    DeletionRequest deletionRequest = DeletionRequest.from(drDTO);

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

  @Override
  public List<DeletionRequestOutputDTO> getAll() {
    return deletionRequestRepository.findAll().stream()
        .map(DeletionRequestOutputDTO::from)
        .toList();
  }

}
