package ar.utn.edu.frba.ddsi.models.entities.source.impl;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.apis.IAPI;
import ar.utn.edu.frba.ddsi.models.entities.source.IEventSource;
import org.springframework.stereotype.Component;

@Component
public class Source implements IEventSource {

  @Getter
  @Setter
  private Long id;

  private final IAPI api;

  public Source(IAPI api) {
    this.api = api;
  }

  @Override
  public Set<Event> fetchEvents() {
    Set<Event> events = this.api.importEvents();

    return events;
  }
}

