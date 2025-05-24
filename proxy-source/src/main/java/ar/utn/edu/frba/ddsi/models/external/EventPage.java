package ar.utn.edu.frba.ddsi.models.external;

import ar.utn.edu.frba.ddsi.models.dtos.input.ExternalDisasterDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class EventPage {
  private Set<ExternalDisasterDTO> data;
  private String next_page_url;

  public Set<Event> getEvents(LocalDateTime lastUpdate) {
    //TODO Manejar caso data null
    return data
        .stream()
        .map(Event::from)
        .filter(event -> lastUpdate == null || event.isOutdated(lastUpdate))
        .collect(Collectors.toSet());
  }

  public String getNextPageUrl() {
    return next_page_url;
  }
}
