package ar.edu.utn.frba.dds.domain.entities.source;

import ar.edu.utn.frba.dds.domain.entities.event.Event;
import java.util.Set;
import lombok.Getter;

public class Source {

  @Getter
  public Set<Event> events;
  private final String path;

  public Source(String path) {
    this.path = path;
    this.update();
  }

  public void update() {
    this.events = CsvImporter.importEvents(this.path);
  }
}
