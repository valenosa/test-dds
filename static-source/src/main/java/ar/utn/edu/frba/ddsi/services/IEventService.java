package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.util.List;

public interface IEventService {
  List<EventOutputDTO> getEvents();
  List<EventOutputDTO> getEventsBySource(Long sourceId);
  EventOutputDTO deleteEvent(Long eventId);
}
