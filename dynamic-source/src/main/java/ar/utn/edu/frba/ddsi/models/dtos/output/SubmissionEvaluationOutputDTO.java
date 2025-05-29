package ar.utn.edu.frba.ddsi.models.dtos.output;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.event.values.SubmissionState;
import lombok.Data;

@Data
public class SubmissionEvaluationOutputDTO {
  private EventOutputDTO event;
  private SubmissionState submissionState;

  public static SubmissionEvaluationOutputDTO from(Event event, SubmissionState submissionState) {
    SubmissionEvaluationOutputDTO dto = new SubmissionEvaluationOutputDTO();
    dto.setEvent(EventOutputDTO.from(event));
    dto.setSubmissionState(submissionState);
    return dto;
  }
}