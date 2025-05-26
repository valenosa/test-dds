package ar.utn.edu.frba.ddsi.models.entities.source.apis;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;

public interface IAPI {
  List<Event> importEvents(LocalDateTime lastUpdate);
}


