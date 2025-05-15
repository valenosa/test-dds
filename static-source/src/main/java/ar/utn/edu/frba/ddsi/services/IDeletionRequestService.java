package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.input.DeletionRequestInputDTO;

public interface IDeletionRequestService {
  void accept(Long deletionRequestId, String evaluatorName);
  void reject(Long deletionRequestId, String evaluatorName);
  void create(DeletionRequestInputDTO deletionRequestInputDTO); //TODO Deberia recibir un DTO
}
