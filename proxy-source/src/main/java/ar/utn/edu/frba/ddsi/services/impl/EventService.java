package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.utn.edu.frba.ddsi.repositories.ISourceRepository;
import ar.utn.edu.frba.ddsi.services.ISourceService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService implements ISourceService {

  @Autowired
  ISourceRepository sourceRepository;

  @Override
  public List<EventOutputDTO> getEvents(LocalDateTime lastUpdate) {
    return sourceRepository.findAll() //? Que onda con las proxy MetaMapa
        .stream()
        //Obtengo todos los elementos de todas las fuentes
        .flatMap(source -> source.importEvents(lastUpdate).stream())
        //Los transformo a DTO
        .map(EventOutputDTO::from)
        .toList();
  }
}
