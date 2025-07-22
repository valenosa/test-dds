package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.models.dtos.input.EventInputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.SourceClientInputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.SourceClientOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.Source;
import ar.utn.edu.frba.ddsi.models.entities.source.SourceClient;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.models.repositories.ISourceClientRepository;
import ar.utn.edu.frba.ddsi.models.repositories.ISourceRepository;
import ar.utn.edu.frba.ddsi.services.ISourceService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceService implements ISourceService {

  @Autowired
  private ISourceClientRepository sourceClientRepository;

  @Autowired
  private IEventRepository eventRepository;

  @Autowired
  private ISourceRepository sourceRepository;

  @Override
  public SourceClientOutputDTO create(SourceClientInputDTO dto) {
    SourceClient client = sourceClientRepository.save(SourceClient.from(dto));

    this.refresh(client);

    return SourceClientOutputDTO.from(client);
  }

  @Override
  public void refreshSources(LocalDateTime lastUpdate) {
    for (SourceClient sourceClient : sourceClientRepository.getAllClients()) {
      this.refresh(sourceClient, lastUpdate);
    }
  }

  private void refresh(SourceClient sourceClient, LocalDateTime lastUpdate) {
    List<EventInputDTO> eventDTOsFromClient = sourceClient.fetchEvents(lastUpdate);

    //Map the events by their sourceId
    Map<Long, List<EventInputDTO>> eventsByInnerSourceId = eventDTOsFromClient.stream()
        .collect(Collectors.groupingBy(EventInputDTO::getSourceId));

    for (Map.Entry<Long, List<EventInputDTO>> entry : eventsByInnerSourceId.entrySet()) {

      Long sourceId = entry.getKey();
      List<EventInputDTO> eventDTOsBySource = entry.getValue();

      // Get or Create the source
      Source source = sourceRepository.findByExternalIds(sourceClient.getId(), sourceId);
      if(source == null) {
        Source nuevaSource = new Source(
            sourceClient,
            sourceId
        );
        source = sourceRepository.save(nuevaSource);
      }

      // Convert DTOs to Events and add them to the source
      Source finalSource = source;
      List<Event> eventsFromSource = eventDTOsBySource.stream().map(dto -> Event.from(dto, finalSource)).toList();
      finalSource.addEvents(eventsFromSource);

      eventsFromSource.forEach(event -> eventRepository.save(event));
      sourceRepository.save(finalSource);
      //? Al guardar la source, se guardan los eventos asociados? (operación en cascada)
    }
  }

  private void refresh(SourceClient sourceClient) {
    this.refresh(sourceClient, null);
  }

  @Override
  public List<SourceClientOutputDTO> getAllClients() {
    return sourceClientRepository.getAllClients().stream()
        .map(SourceClientOutputDTO::from)
        .toList();
  }
}


