package ar.utn.edu.frba.ddsi.models.entities.source;

import ar.utn.edu.frba.ddsi.models.dtos.input.event.EventInputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.source.SourceInputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
  private final List<Event> events;

  public static Source from(SourceInputDTO dto, ISourceClientAdapter sourceClient) {
    return new Source(sourceClient, dto.getInClientId(), dto.getType());
  }

  public Source(ISourceClientAdapter sourceClient, Long inClientId, Origin type) {

    this.sourceClient = sourceClient;
    this.inClientId = inClientId;

    this.type = type;
    events = new ArrayList<>();
  }

  public List<Event> getEvents(LocalDateTime lastUpdate) {
    if (lastUpdate == null) {
      return events;
    }
    return events.stream().filter(e ->
        e.getUploadDate().isAfter(lastUpdate) && !e.isDeleted())
        .toList();
  }

  public void addEvent(Event event) {
    events.add(event);
  }

  public void notifyEventDeleted(Event event){
    if (this.isNotifiable()) {
      sourceClient.deleteEvent(event);
    }
  }

  public List<EventInputDTO> fetchEvents(){
    return this.sourceClient.fetchEventsBySource(this);
  }

  private boolean isNotifiable(){
    return type != Origin.PROXY && type != Origin.METAMAPA;
  }

  public boolean isMetamapa() {
    return type == Origin.METAMAPA;
  }
}
