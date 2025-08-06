package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.exceptions.NotFoundException;
import ar.utn.edu.frba.ddsi.models.dtos.input.source.SourceClientInputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.source.SourceInputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.source.SourceClientOutputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.source.SourceOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.ISourceClientAdapter;
import ar.utn.edu.frba.ddsi.models.entities.source.Source;
import ar.utn.edu.frba.ddsi.models.entities.source.impl.SourceClient;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.models.repositories.ISourceClientRepository;
import ar.utn.edu.frba.ddsi.models.repositories.ISourceRepository;
import ar.utn.edu.frba.ddsi.services.ISourceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceService implements ISourceService {

  @Autowired
  private ISourceClientRepository sourceClientRepository;

  @Autowired
  private ISourceRepository sourceRepository;

  @Autowired
  private IEventRepository eventRepository;

  @Override
  public SourceClientOutputDTO create(SourceClientInputDTO dto) {
    SourceClient sourceClient = sourceClientRepository.save(SourceClient.from(dto));

    //Subscribe to the source client to receive NEW sources and events.
    sourceClient.subscribe();

    // First connection: Fetch the preexisting sources and events from the client and saves it.
    List<Source> sources = sourceClient.fetchSources();

    sources.parallelStream().forEach(source -> {
      // For each source, fetch its events
      List<Event> events = sourceClient.fetchEventsBySource(source);
      source.addEvents(events);
    });

    //? Se puede guardar en cascada?
    sources.forEach(source -> {
      source.getEvents(null).forEach(eventRepository::save);
      sourceRepository.save(source);
    });
    return SourceClientOutputDTO.from(sourceClient);
  }

  @Override
  public SourceOutputDTO create(SourceInputDTO dto) {

    ISourceClientAdapter sourceClient = sourceClientRepository.getById(dto.getSourceClientId());
    if (sourceClient == null)
      throw new NotFoundException("Source not found - ID: " + dto.getSourceClientId());

    Source source = new Source(sourceClient, dto.getInClientId(), dto.getType());

    sourceRepository.save(source);
    return SourceOutputDTO.from(source);
  }

  @Override
  public List<SourceClientOutputDTO> getAllClients() {
    return sourceClientRepository.getAllClients().stream()
        .map(SourceClientOutputDTO::from)
        .toList();
  }

  @Override
  public List<SourceOutputDTO> getAllSources() {
    return sourceRepository.findAll().stream()
        .map(SourceOutputDTO::from)
        .toList();
  }
}


