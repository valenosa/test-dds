package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.input.DeletionRequestEvaluationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.DeletionRequestInputDTO;

public interface IDeletionRequestService {
  void accept(DeletionRequestEvaluationDTO deletionRequestEvaluationDTO);
  void reject(DeletionRequestEvaluationDTO deletionRequestEvaluationDTO);
  void create(DeletionRequestInputDTO deletionRequestInputDTO);
}
