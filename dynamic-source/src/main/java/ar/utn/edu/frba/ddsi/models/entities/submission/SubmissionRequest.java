package ar.utn.edu.frba.ddsi.models.entities.submission;

import ar.utn.edu.frba.ddsi.models.dtos.input.SubmissionEvaluationDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.event.values.SubmissionState;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SubmissionRequest {
  @Setter
  private Long id;

  private final Event event;

  //-- Post Evaluation
  @Setter
  private LocalDateTime evaluationDate;
  @Setter
  private String reviewer; //TODO: Esto deberia ser un usuario
  @Setter
  private SubmissionState state;
  @Setter
  private String suggestion;

  public SubmissionRequest(Event event) {

    this.event = event;
    this.state = SubmissionState.PENDING;
  }

  public void setEvaluation(SubmissionEvaluationDTO submissionEvaluation) {
    this.evaluationDate = LocalDateTime.now();
    this.reviewer = submissionEvaluation.getReviewer();
    this.state = submissionEvaluation.getState();
    this.suggestion = submissionEvaluation.getSuggestion();

    if (this.isAccepted()) {
      event.markAsAccepted();
    }
  }

  public boolean isAccepted() {
    return state == SubmissionState.ACCEPTED || state == SubmissionState.ACCEPTED_WITH_SUGGESTIONS;
  }

  public boolean isPending() {
    return state == SubmissionState.PENDING;
  }

}
