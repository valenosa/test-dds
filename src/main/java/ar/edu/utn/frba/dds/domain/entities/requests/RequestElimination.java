package ar.edu.utn.frba.dds.domain.entities.requests;

import ar.edu.utn.frba.dds.domain.entities.fact.Fact;
import lombok.Getter;

public class RequestElimination {

  @Getter
  private final Fact fact;
  @Getter
  private final String justification;
  @Getter
  private StateRequest state;
  private final MetaDataRequestOfElimination metadata;

  public RequestElimination(Fact fact, String justification, String applicantName) {

    if (!this.isJustificated(justification)) {
      throw new IllegalArgumentException("La justificación debe tener al menos 500 caracteres.");
    }
    this.fact = fact;
    this.justification = justification;
    this.state = StateRequest.PENDING;
    this.metadata = new MetaDataRequestOfElimination(applicantName);
  }

  private boolean isJustificated(String justification) {
    return justification.length() >= 500;
  }

  public void accept(String evaluatorName) {
    this.metadata.registerEvaluation(evaluatorName);
    this.state = StateRequest.ACCEPTED;
    fact.setEliminated(true);
  }

  public void reject(String evaluatorName) {
    this.metadata.registerEvaluation(evaluatorName);
    this.state = StateRequest.REJECTED;
  }
}