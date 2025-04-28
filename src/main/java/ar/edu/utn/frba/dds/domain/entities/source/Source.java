package ar.edu.utn.frba.dds.domain.entities.source;

import ar.edu.utn.frba.dds.domain.entities.event.Event;
import java.util.Set;
import lombok.Getter;

public class Source {

  @Getter
  public Set<Event> events;
  private Importer importer;

  public Source(Importer importingStrategy) {
    importer = importingStrategy;
    events = importer.importEvents();
  }

}
