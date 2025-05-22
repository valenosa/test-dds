package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.models.dtos.input.SourceInputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.SourceOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.impl.Source;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.models.repositories.ISourceRepository;
import ar.utn.edu.frba.ddsi.services.ISourceService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceService implements ISourceService {

  @Autowired
  private ISourceRepository sourceRepository;

  @Autowired
  private IEventRepository eventRepository;

  @Override
  public SourceOutputDTO create(SourceInputDTO dto) {

    Source source = Source.from(dto);

    Set<Event> importedEvents = source.importEvents();
    eventRepository.save(importedEvents);

    sourceRepository.save(source);

    return SourceOutputDTO.from(source);
  }

  @Override
  public List<Long> getSources() {
    return sourceRepository.findAll().stream().map(Source::getId).collect(Collectors.toList());
  }
}