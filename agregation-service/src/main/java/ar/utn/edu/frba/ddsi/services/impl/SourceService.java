package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.config.WebClientConfig;
import ar.utn.edu.frba.ddsi.models.dtos.input.EventInputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.services.ISourceService;
import jakarta.annotation.PostConstruct;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

@Service
public class SourceService implements ISourceService {

  @Autowired
  private WebClientConfig webClientConfig;

  @Autowired
  private IEventRepository eventRepository;

  @Override
  @PostConstruct
  public void initSources() {
    for (WebClient sourceClient : webClientConfig.getAllClients()) {
      fetchAndSaveEvents(sourceClient, uriBuilder -> uriBuilder.path("/events").build());
    }
  }

  @Override
  public void refreshSources(LocalDateTime lastUpdate) {
    for (WebClient sourceClient : webClientConfig.getAllClients()) {
      fetchAndSaveEvents(sourceClient, uriBuilder ->
          uriBuilder.path("/events").queryParam("lastUpdate", lastUpdate).build());
    }
  }

  private void fetchAndSaveEvents(WebClient sourceClient, Function<UriBuilder, URI> uriFunction) {
    List<EventInputDTO> events = sourceClient.get()
        .uri(uriFunction)
        .retrieve()
        .onStatus(HttpStatusCode::isError, response ->
            Mono.error(new RuntimeException("Error HTTP: " + response.statusCode())))
        .bodyToFlux(EventInputDTO.class)
        .collectList()
        .block();

    if (events != null) {
      events.stream()
          .map(Event::from)
          .forEach(eventRepository::save);
    }
  }
}


