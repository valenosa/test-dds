package ar.utn.edu.frba.ddsi.models.dtos.input;

import ar.utn.edu.frba.ddsi.models.entities.event.values.SubmissionState;
import lombok.Data;

@Data
public class SubmissionEvaluationDTO {

  Long submissionId;
  String reviewer; //TODO: Esto deberia ser un usuario
  SubmissionState submissionState;
  String suggestion;
}
