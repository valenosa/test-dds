package ar.utn.edu.frba.ddsi.models.entities.source.apis;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.time.LocalDateTime;
import java.util.List;

public interface IAPI {
  List<Event> importEvents(LocalDateTime lastUpdate);
}


