package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.exceptions.NotFoundException;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.services.IEventService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService implements IEventService {

  @Autowired
  IEventRepository eventRepository;

  @Override
  public List<EventOutputDTO> getEvents() {

    Set<Event> events = eventRepository.findAll().stream().filter(e-> (!e.isDeleted() && e.isNewOrModified())).collect(Collectors.toSet());

    //Actualizo que los eventos ya fueron enviados
    events.forEach(e-> e.setNewOrModified(false));
    eventRepository.save(events);

    return events.stream().map(EventOutputDTO::from).toList();
  }

  @Override
  public EventOutputDTO deleteEvent(Long eventId) {
    Event event = eventRepository.findById(eventId);
    if (event == null) throw new NotFoundException("Event not found - Id: " + eventId);

    event.markAsDeleted();
    eventRepository.save(event);
    return EventOutputDTO.from(event);
  }
}
