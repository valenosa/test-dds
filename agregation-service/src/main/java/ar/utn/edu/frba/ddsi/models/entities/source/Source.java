package ar.utn.edu.frba.ddsi.models.entities.source;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Source {
  @Setter
  private Long id;

  private final SourceClient sourceClient;
  private final Long inClientId;
  private final Map<Long, Event> events;

  public Source(SourceClient sourceClient, Long inClientId) {
    this.sourceClient = sourceClient;
    this.inClientId = inClientId;
    events = new HashMap<>();
  }

  public void addEvents(List<Event> eventsFromSource) {

    for (Event eventFromSource : eventsFromSource) {
      Event event = events.get(eventFromSource.getId());

      if (event == null) {
        // If the event does not exist, add it
        events.put(eventFromSource.getId(), eventFromSource);
      } else {
        event.update(eventFromSource);
      }

    }

  }

  public List<Event> getEvents(LocalDateTime lastUpdate) {
    if (lastUpdate == null) {
      return new ArrayList<>(events.values());
    }
    return events.values().stream().filter(e ->
        e.getUploadDate().isAfter(lastUpdate)
        &&
        !e.isDeleted())
        .toList();
  }

  public Origin getType() {
    return sourceClient.getType();
  }
}
