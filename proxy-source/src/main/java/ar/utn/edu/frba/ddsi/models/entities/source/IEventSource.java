package ar.utn.edu.frba.ddsi.models.entities.source;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.time.LocalDateTime;
import java.util.List;

public interface IEventSource {
  List<Event> importEvents(LocalDateTime lastUpdate);
}
