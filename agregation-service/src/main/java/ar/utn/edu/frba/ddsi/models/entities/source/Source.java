package ar.utn.edu.frba.ddsi.models.entities.source;

import ar.utn.edu.frba.ddsi.models.dtos.input.source.SourceInputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Source {

  @Setter
  private Long id;

  //-- Source Client
  private final ISourceClientAdapter sourceClient;
  private final Long inClientId;

  //-- Data
  private final Origin type;
  private final Map<Long, Event> events;

  public static Source from(SourceInputDTO dto, ISourceClientAdapter sourceClient) {
    return new Source(sourceClient, dto.getInClientId(), dto.getType());
  }

  public Source(ISourceClientAdapter sourceClient, Long inClientId, Origin type) {

    this.sourceClient = sourceClient;
    this.inClientId = inClientId;

    this.type = type;
    events = new HashMap<>();
  }

  public List<Event> getEvents(LocalDateTime lastUpdate) {
    if (lastUpdate == null) {
      return events.values().stream().toList();
    }
    return events.values().stream().filter(e ->
        e.getUploadDate().isAfter(lastUpdate) && !e.isDeleted())
        .toList();
  }

  public void addEvents(List<Event> eventsFromSource) {

    for (Event eventFromSource : eventsFromSource) {
      Event event = events.get(eventFromSource.getInSourceEventId());

      if (event == null) {
        // If the event does not exist, add it
        events.put(eventFromSource.getInSourceEventId(), eventFromSource);
      } else {
        event.update(eventFromSource);
        //! Revisar que realmente se esta actualizando luego en el repo el evento y que no se guarade el "fake nuevo"
      }
    }
  }

  public void notifyEventDeleted(Event event){
    if (this.isNotifiable()) {
      sourceClient.deleteEvent(event);
    }
  }

  public List<Event> fetchEvents(){
    return this.sourceClient.fetchEventsBySource(this);
  }

  private boolean isNotifiable(){
    return type != Origin.PROXY && type != Origin.METAMAPA;
  }

  public boolean isMetamapa() {
    return type == Origin.METAMAPA;
  }
}
