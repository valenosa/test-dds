package ar.edu.utn.frba.dds.domain.entities.requests;

import ar.edu.utn.frba.dds.domain.entities.event.Event;
import lombok.Getter;

public class DeletionRequest {

  @Getter
  private final Event event;
  @Getter
  private final String justification;
  @Getter
  private DeletionRequestState state;
  private final DeletionRequestMetadata metadata;

  public DeletionRequest(Event event, String justification, String applicantName) {

    if (!this.isJustificated(justification)) {
      throw new IllegalArgumentException("La justificación debe tener al menos 500 caracteres.");
    }
    this.event = event;
    this.justification = justification;
    this.state = DeletionRequestState.PENDING;
    this.metadata = new DeletionRequestMetadata(applicantName);
  }

  private boolean isJustificated(String justification) {
    return justification.length() >= 500;
  }

  public void accept(String evaluatorName) {
    this.metadata.registerEvaluation(evaluatorName);
    this.state = DeletionRequestState.ACCEPTED;
    event.setDeleted(true);
  }

  public void reject(String evaluatorName) {
    this.metadata.registerEvaluation(evaluatorName);
    this.state = DeletionRequestState.REJECTED;
  }
}