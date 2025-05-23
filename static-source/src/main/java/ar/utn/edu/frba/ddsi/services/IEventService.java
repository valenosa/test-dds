package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import java.util.List;

public interface IEventService {
  List<EventOutputDTO> getEvents();
  EventOutputDTO deleteEvent(Long eventId);
}
