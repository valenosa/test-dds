package ar.utn.edu.frba.ddsi.models.entities.response;

import ar.utn.edu.frba.ddsi.models.dtos.input.EventInputDTO;
import java.util.List;
import lombok.Getter;

@Getter
public class GetEventsResponse {
  private List<EventInputDTO> events;
}
