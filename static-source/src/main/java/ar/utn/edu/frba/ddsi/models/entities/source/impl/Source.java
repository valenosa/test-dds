package ar.utn.edu.frba.ddsi.models.entities.source.impl;

import ar.utn.edu.frba.ddsi.models.dtos.input.SourceInputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.IEventSource;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Source implements IEventSource {

  @Getter
  @Setter
  private Long id;

  @Autowired
  private CsvImporter csvImporter;

  private final String path;

  public static Source from(SourceInputDTO dto) {
    return new Source(dto.getPath());
  }

  public Source(String path) {
    this.path = path;
  }

  @Override
  public Set<Event> importEvents() {
    return this.csvImporter.importEvents(this.path, id);
  }
}
