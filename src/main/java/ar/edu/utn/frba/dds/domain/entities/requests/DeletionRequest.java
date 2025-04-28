package ar.edu.utn.frba.dds.domain.entities.requests;

import ar.edu.utn.frba.dds.domain.entities.event.Event;
import lombok.Getter;

import java.time.LocalDate;

public class DeletionRequest {

  @Getter private final Event event;
  @Getter private final String justification;
  @Getter private DeletionRequestState state;

  private final LocalDate uploadDate;
  private LocalDate evaluationDate;

  private final String applicantName; //TODO luego será un usuario
  private String evaluatorName; //TODO luego será un usuario

  public DeletionRequest(Event event, String justification, String applicantName) {

    if (!this.isJustified(justification)) {
      throw new IllegalArgumentException("La justificación debe tener al menos 500 caracteres.");
    }
    this.event = event;
    this.justification = justification;
    this.state = DeletionRequestState.PENDING;

    this.uploadDate = LocalDate.now();
    this.applicantName = applicantName;
  }

  private boolean isJustified(String justification) {
    return justification.length() >= 500;
  }

  public void accept(String evaluatorName) {
    this.registerEvaluation(evaluatorName);
    this.state = DeletionRequestState.ACCEPTED;
    event.setDeleted(true);
  }

  public void reject(String evaluatorName) {
    this.registerEvaluation(evaluatorName);
    this.state = DeletionRequestState.REJECTED;
  }

  private void registerEvaluation(String evaluatorName) {
    this.evaluationDate = LocalDate.now();
    this.evaluatorName = evaluatorName;
  }
}