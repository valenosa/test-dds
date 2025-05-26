package ar.utn.edu.frba.ddsi.models.dtos.input;

import ar.utn.edu.frba.ddsi.models.entities.event.values.SubmissionState;
import lombok.Data;

@Data
public class SubmissionEvaluationDTO {

  String reviwer; //TODO: Esto deberia ser un usuario

  Long eventId;
  SubmissionState submissionState;
  String suggestion;
}
