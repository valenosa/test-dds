package ar.utn.edu.frba.ddsi.models.entities.source.impl;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.IEventSource;
import ar.utn.edu.frba.ddsi.models.entities.source.apis.IAPI;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
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
  public List<Event> importEvents(LocalDateTime lastUpdate) {
    return this.api.importEvents(lastUpdate);
  }

}

