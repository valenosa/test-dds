package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.exceptions.NotFoundException;
import ar.utn.edu.frba.ddsi.exceptions.UnauthorizedException;
import ar.utn.edu.frba.ddsi.models.dtos.input.EventCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.EventUpdateDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.event.values.SubmissionState;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.services.IEventService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService implements IEventService {

  @Autowired
  IEventRepository eventRepository;

  @Override
  public List<EventOutputDTO> getEvents() {

    List<Event> events = eventRepository.findAll().stream().filter(e-> !e.isValid() && e.isNewOrModified()).toList();

    //Actualizo que el evento ya fue enviado
    events.forEach(e-> e.setNewOrModified(false));
    eventRepository.save(events);

    return events.stream().map(EventOutputDTO :: from).toList();
  }

  @Override
  public List<EventOutputDTO> getPendingEvents() {
    return eventRepository.findAll().stream().filter(e-> e.getState() == SubmissionState.PENDING).map(EventOutputDTO :: from).toList();
  }

  @Override
  public void save(EventCreationDTO dto) {
    //TODO: Validar si el usuario puede realizar esta peticion

    eventRepository.save(Event.from(dto));
  }

  @Override
  public void update(EventUpdateDTO dto) {
    //TODO: Validar si el usuario puede realizar esta peticion

    Event event = eventRepository.findById(dto.getEventId());

    if(event == null)
      throw new NotFoundException("Event not found - ID: " + dto.getEventId());

    if(!event.getContributor().equals(dto.getContributor()))
      throw new UnauthorizedException("Unauthorized action: event ownership mismatch");

    if(event.getUploadDate().plusDays(7).isBefore(LocalDate.now()))
      throw new IllegalStateException("Event editing window has expired. Modifications are no longer allowed");

    event.updateWith(dto);
    eventRepository.save(event);

  }
}
