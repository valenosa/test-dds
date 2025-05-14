package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.models.entities.dtos.input.DeletionRequestInputDTO;
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
  public void accept(Long deletionRequestId, String evaluatorName) {
    //TODO: Validar si el usuario puede realizar esta peticion

    DeletionRequest deletionRequest = deletionRequestRepository.getById(deletionRequestId);

    eventRepository.delete(deletionRequest.getEventId());
    deletionRequest.registerEvaluation(evaluatorName);
    deletionRequest.setState(DeletionRequestState.ACCEPTED);

    deletionRequestRepository.save(deletionRequest);
  }

  @Override
  public void reject(Long deletionRequestId, String evaluatorName) {
    //TODO: Validar si el usuario puede realizar esta peticion

    DeletionRequest deletionRequest = deletionRequestRepository.getById(deletionRequestId);

    deletionRequest.registerEvaluation(evaluatorName);
    deletionRequest.setState(DeletionRequestState.REJECTED);

    deletionRequestRepository.save(deletionRequest);

  }

  @Override
  public void create(DeletionRequestInputDTO deletionRequestInputDTO){
    //TODO: Validar si el usuario puede realizar esta peticion

    DeletionRequest deletionRequest = DeletionRequest.from(deletionRequestInputDTO);

    deletionRequestRepository.save(deletionRequest);
  }
}
