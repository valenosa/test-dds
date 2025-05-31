package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.input.DeletionRequestCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.DeletionRequestEvaluationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.DeletionRequestOutputDTO;
import java.util.List;

public interface IDeletionRequestService {
  DeletionRequestOutputDTO evaluate(DeletionRequestEvaluationDTO deletionRequestEvaluationDTO);

  DeletionRequestOutputDTO create(DeletionRequestCreationDTO deletionRequestCreationDTO);

  List<DeletionRequestOutputDTO> getAll();
}
