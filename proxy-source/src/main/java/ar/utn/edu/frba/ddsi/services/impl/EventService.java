package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.observer.Publisher;
import ar.utn.edu.frba.ddsi.repositories.ISourceRepository;
import ar.utn.edu.frba.ddsi.services.IEventService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService implements IEventService {

  @Autowired
  ISourceRepository sourceRepository;

  @Autowired
  Publisher publisher;

  @Override
  public List<EventOutputDTO> getEvents(LocalDateTime lastUpdate) {
    return sourceRepository.findAll() //? Qué onda con las proxy MetaMapa
        .stream()
        //Obtengo todos los elementos de todas las fuentes
        .flatMap(source -> source.importEvents(lastUpdate).stream())
        //Los transformo a DTO
        .map(EventOutputDTO::from)
        .toList();
  }

  public void notifyEvents(LocalDateTime lastUpdate) {
    List<EventOutputDTO> events = this.getEvents(lastUpdate);

    publisher.notifyNewEvents(events);
  }

}
