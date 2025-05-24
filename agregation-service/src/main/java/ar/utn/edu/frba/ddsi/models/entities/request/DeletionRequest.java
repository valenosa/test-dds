package ar.utn.edu.frba.ddsi.models.entities.request;

import ar.utn.edu.frba.ddsi.models.dtos.input.DeletionRequestCreationDTO;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
public class DeletionRequest {

  @Setter
  private Long id;
  private Long eventId;

  private final String argument;
  @Setter
  private DeletionRequestState state;

  private final LocalDateTime uploadDate;
  private LocalDateTime evaluationDate;

  private final String applicantName; //TODO: Esto deberia ser un usuario
  private String evaluatorName; //TODO: Esto deberia ser un usuario

  public static DeletionRequest from(DeletionRequestCreationDTO dto) {
    return new DeletionRequest(dto.getEventId(), dto.getArgument(), dto.getApplicantName());
  }

  public DeletionRequest(Long eventId, String argument, String applicantName) {

    if (!this.isArgumentValid(argument)) throw new IllegalArgumentException("La justificación debe tener al menos 500 caracteres.");

    this.eventId = eventId;
    this.argument = argument;
    this.state = DeletionRequestState.PENDING;
    this.uploadDate = LocalDateTime.now();
    this.applicantName = applicantName;
  }

  private boolean isArgumentValid(String argument) {
    return argument.length() >= 500;
  }

  public void registerEvaluation(String evaluatorName) {
    this.evaluationDate = LocalDateTime.now();
    this.evaluatorName = evaluatorName;
  }
}
