package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.services.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService implements IEventService {

    @Autowired
    IEventRepository eventRepository;

    @Override
    public List<EventOutputDTO> getEvents() {
        return eventRepository.findAll().stream().map(EventOutputDTO::from).toList();
    }
}
