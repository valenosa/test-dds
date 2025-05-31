package ar.utn.edu.frba.ddsi.models.entities.submission;

import ar.utn.edu.frba.ddsi.models.entities.event.values.SubmissionState;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SubmissionRequest {
  @Setter
  private Long id;
  private final Long eventId;

  //-- Post Evaluation
  @Setter
  private LocalDateTime evaluationDate;
  @Setter
  private String reviewer; //TODO: Esto deberia ser un usuario
  @Setter
  private SubmissionState state;
  @Setter
  private String suggestion;

  public SubmissionRequest(Long eventId) {

    this.eventId = eventId;
    this.state = SubmissionState.PENDING;
  }

  public void setEvaluation(String reviewer, SubmissionState state, String suggestion) {
    this.evaluationDate = LocalDateTime.now();
    this.reviewer = reviewer;
    this.state = state;
    this.suggestion = suggestion;
  }

  public boolean isAccepted() {
    return state == SubmissionState.ACCEPTED || state == SubmissionState.ACCEPTED_WITH_SUGGESTIONS;
  }

  public boolean isPending() {
    return state == SubmissionState.PENDING;
  }
}
