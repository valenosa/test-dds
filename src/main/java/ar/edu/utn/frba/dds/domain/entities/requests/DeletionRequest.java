package ar.edu.utn.frba.dds.domain.entities.requests;

import ar.edu.utn.frba.dds.domain.entities.fact.Fact;
import lombok.Getter;

public class DeletionRequest {

  @Getter
  private final Fact fact;
  @Getter
  private final String justification;
  @Getter
  private DeletionRequestState state;
  private final DeletionRequestMetadata metadata;

  public DeletionRequest(Fact fact, String justification, String applicantName) {

    if (!this.isJustificated(justification)) {
      throw new IllegalArgumentException("La justificación debe tener al menos 500 caracteres.");
    }
    this.fact = fact;
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
    fact.setDeleted(true);
  }

  public void reject(String evaluatorName) {
    this.metadata.registerEvaluation(evaluatorName);
    this.state = DeletionRequestState.REJECTED;
  }
}