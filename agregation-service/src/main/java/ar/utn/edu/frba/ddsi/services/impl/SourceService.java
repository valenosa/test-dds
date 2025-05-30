package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.models.dtos.input.SourceClientDTO;
import ar.utn.edu.frba.ddsi.models.entities.sourceClient.SourceClient;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.models.repositories.impl.SourceClientRepository;
import ar.utn.edu.frba.ddsi.services.ISourceService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceService implements ISourceService {

  @Autowired
  private SourceClientRepository sourceClientRepository;

  @Autowired
  private IEventRepository eventRepository;

  @Override
  public SourceClientDTO create(SourceClientDTO dto) {
    SourceClient client = SourceClient.from(dto);
    sourceClientRepository.save(client);

    this.refresh(client);

    return dto;
  }

  @Override
  public void refreshSources(LocalDateTime lastUpdate) {
    for (SourceClient sourceClient : sourceClientRepository.getAllClients()) {
      this.refresh(sourceClient, lastUpdate);
    }
  }

  private void refresh(SourceClient sourceClient, LocalDateTime lastUpdate) {
    sourceClient
        .fetchEvents(lastUpdate)
        .forEach(eventRepository::save);
  }

  private void refresh(SourceClient sourceClient) {
    this.refresh(sourceClient, null);
  }

  @Override
  public List<SourceClientDTO> getAllClients() {
    return sourceClientRepository.getAllClients().stream()
        .map(SourceClientDTO::from)
        .toList();
  }
}


