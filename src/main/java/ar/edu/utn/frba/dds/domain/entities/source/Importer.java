package ar.edu.utn.frba.dds.domain.entities.source;

import ar.edu.utn.frba.dds.domain.entities.event.Event;
import java.util.Set;

public interface Importer {
  public Set<Event> importEvents();
}
