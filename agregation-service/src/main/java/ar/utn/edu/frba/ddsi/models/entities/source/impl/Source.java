package ar.utn.edu.frba.ddsi.models.entities.source.impl;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.sourceClient.SourceClient;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Source {
  @Setter
  private Long id;

  private final SourceClient sourceClient;
  private final Long inClientId;
  private List<Event> events;

  public Source(SourceClient sourceClient, Long inClientId) {
    this.sourceClient = sourceClient;
    this.inClientId = inClientId;
    events = new ArrayList<>();
  }

  public void addEvents(List<Event> eventsFromSource) {
    events.addAll(eventsFromSource);
  }
}
