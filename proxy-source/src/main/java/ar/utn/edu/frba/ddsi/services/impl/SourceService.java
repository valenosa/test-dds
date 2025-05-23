package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.utn.edu.frba.ddsi.repositories.ISourceRepository;
import ar.utn.edu.frba.ddsi.services.ISourceService;

import java.util.List;

@Service
public class SourceService implements ISourceService {

  @Autowired
  ISourceRepository sourceRepository;

  @Override
  public List<EventOutputDTO> getEvents() {
    return sourceRepository.findAll()
        .stream()
        //Obtengo todos los elementos (ya filtrados por modificación) de todas las fuentes
        .flatMap(source -> source.fetchEvents().stream())
        //Los transformo a DTO
        .map(EventOutputDTO::from)
        .toList();
  }
}
