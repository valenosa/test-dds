package ar.utn.edu.frba.ddsi.models.entities.source.impl;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.event.Origin;
import ar.utn.edu.frba.ddsi.models.entities.source.apis.IAPI;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


public class Source {

  @Getter
  @Setter
  private Long id;
  @Getter
  @Setter
  private Origin type;

  private final IAPI api;

  public Source(IAPI api, Origin type) {
    this.api = api;
    this.type = type;
  }

  public List<Event> importEvents(LocalDateTime lastUpdate) {
    List<Event> events = this.api.importEvents(lastUpdate);

    events.forEach(event -> event.setSource(this));

    return events;
  }

}

