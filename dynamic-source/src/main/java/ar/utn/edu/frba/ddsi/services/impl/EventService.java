package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.models.dtos.input.EventCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.services.IEventService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService implements IEventService {

  @Autowired
  IEventRepository eventRepository;

  @Override
  public List<EventOutputDTO> getEvents() {
    return eventRepository.findAll().stream().map(EventOutputDTO :: from).collect(Collectors.toList());
  }

  @Override
  public EventOutputDTO getEventById(Long id) {
    return EventOutputDTO.from(eventRepository.findById(id));
  }

  @Override
  public void save(EventCreationDTO dto) {
    //TODO: Validar si el usuario puede realizar esta peticion

    eventRepository.save(Event.from(dto));
  }
}
