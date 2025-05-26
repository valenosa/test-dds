package ar.utn.edu.frba.ddsi.models.entities.source.impl;

import ar.utn.edu.frba.ddsi.models.dtos.input.SourceInputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.IEventSource;
import java.util.Set;

import ar.utn.edu.frba.ddsi.models.entities.source.IImporter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class Source implements IEventSource {

  @Getter
  @Setter
  private Long id;

  private IImporter importer;

  private final String path;

  public Source(String path, IImporter importer) {
    this.path = path;
    this.importer = importer;
  }

  @Override
  public Set<Event> importEvents() {
    return this.importer.importEvents(this.path, this.id);
  }
}