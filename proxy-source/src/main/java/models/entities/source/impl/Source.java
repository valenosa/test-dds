package models.entities.source.impl;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import models.dtos.input.SourceInputDTO;
import models.entities.event.Event;
import models.entities.source.apis.APIFactory;
import models.entities.source.apis.IAPI;
import models.entities.source.IEventSource;
import org.springframework.stereotype.Component;

@Component
public class Source implements IEventSource {

  @Getter
  @Setter
  private Long id;

  private IAPI api;

  @Getter
  public Set<Long> eventsIds;

  public static Source from(SourceInputDTO dto) {
    return new Source(APIFactory.createAPI(dto));
  }

  public Source(IAPI api) {
    this.api = api;
  }

  @Override
  public Set<Event> fetchEvents() {
    Set<Event> events = this.api.importEvents();
    this.eventsIds = events.stream().map(Event::getId).collect(Collectors.toSet());

    return events;
  }
}

