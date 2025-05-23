package ar.utn.edu.frba.ddsi.models.entities.event.values;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class EventKey {
  Long eventId;
  Origin eventOrigin;

  public EventKey(Long eventId, Origin eventOrigin) {
    this.eventId = eventId;
    this.eventOrigin = eventOrigin;
  }
}
