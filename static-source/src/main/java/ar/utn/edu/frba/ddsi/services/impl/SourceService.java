package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.exceptions.NotFoundException;
import ar.utn.edu.frba.ddsi.models.dtos.input.SourceInputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.Source;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.models.repositories.ISourceRepository;
import ar.utn.edu.frba.ddsi.services.ISourceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceService implements ISourceService {

  @Autowired
  private ISourceRepository sourceRepository;

  @Autowired
  private IEventRepository eventRepository;

  @Override
  public void create(SourceInputDTO sourceInputDTO) {
    Source source = Source.from(sourceInputDTO);
    sourceRepository.save(source);
  }

  @Override
  public List<Event> findEventsBySource(Long idSource) {

    Source source = sourceRepository.findById(idSource);
    if (source == null) throw new NotFoundException("Source not found - ID: " + idSource);

    return eventRepository.findAll(source.getEvents());
  }
}