package ar.edu.utn.frba.dds.domain.entities.requests;

import ar.edu.utn.frba.dds.domain.entities.event.Event;
import java.time.LocalDate;
import lombok.Getter;

public class DeletionRequest {

  private Event event;
  @Getter private final String argument;
  @Getter private DeletionRequestState state;

  @Getter private final LocalDate uploadDate;
  @Getter private LocalDate evaluationDate;

  @Getter private final String applicantName; //TODO luego será un usuario
  @Getter private String evaluatorName; //TODO luego será un usuario

  private void setEvent(Event event) {
    this.event = event;
  }

  private void markEventAsDeleted() {
    this.event.setDeleted(true);
  }

  public DeletionRequest(Event event, String argument, String applicantName) {

    if (!this.isArgumentValid(argument)) {
      throw new IllegalArgumentException("La justificación debe tener al menos 500 caracteres.");
    }
    this.setEvent(event);
    this.argument = argument;
    this.state = DeletionRequestState.PENDING;

    this.uploadDate = LocalDate.now();
    this.applicantName = applicantName;
  }

  private boolean isArgumentValid(String argument) {
    return argument.length() >= 500;
  }

  public void accept(String evaluatorName) {
    this.registerEvaluation(evaluatorName);
    this.state = DeletionRequestState.ACCEPTED;
    this.markEventAsDeleted();
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