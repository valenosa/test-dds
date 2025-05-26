package ar.utn.edu.frba.ddsi.models.dtos.output;

import ar.utn.edu.frba.ddsi.models.entities.request.DeletionRequest;
import ar.utn.edu.frba.ddsi.models.entities.request.DeletionRequestState;
import lombok.Data;

@Data
public class DeletionRequestOutputDTO {
  Long id;
  Long eventId;
  String argument;
  DeletionRequestState state;

  public DeletionRequestOutputDTO(Long id, Long eventId, String argument, DeletionRequestState state) {
    this.id = id;
    this.eventId = eventId;
    this.argument = argument;
    this.state = state;
  }

  public static DeletionRequestOutputDTO from(DeletionRequest dr){
      return new DeletionRequestOutputDTO(dr.getId(),dr.getEventId(),dr.getArgument(),dr.getState());
  }
}
