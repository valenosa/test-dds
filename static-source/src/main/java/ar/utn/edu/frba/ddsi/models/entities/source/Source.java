package ar.utn.edu.frba.ddsi.models.entities.source;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Source{

  @Setter
  private Long id;

  private final IImporter importer;

  private final Origin type;
  private final String path;

  public Source(String path, IImporter importer) {
    this.path = path;
    this.importer = importer;
    this.type = Origin.STATIC;
  }

  public Set<Event> importEvents() {
    return this.importer.importEvents(this);
  }
}