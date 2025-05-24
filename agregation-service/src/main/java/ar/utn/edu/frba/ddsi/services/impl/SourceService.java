package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.config.WebClientConfig;
import ar.utn.edu.frba.ddsi.models.dtos.input.EventInputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.response.GetEventsResponse;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.services.ISourceService;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
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
    List<WebClient> sourceClients = webClientConfig.getAllClients(); //devuelve 3 webclient, debo hacerles la peti

    for (WebClient sourceClient : sourceClients) {
      GetEventsResponse getResponse = getEventRequest(sourceClient);

      // Transformo y guardo los eventos
      getResponse.getEvents()
          .stream()
          .map(Event::from)
          .forEach(eventRepository::save);

      //TODO Manejar GetEventsResponse error
    }
  }

  private GetEventsResponse getEventRequest(WebClient sourceClient) {
    return sourceClient.get().
        uri("/events")
        .retrieve()
        .onStatus(HttpStatusCode::isError, response -> Mono.error(new RuntimeException("Error HTTP: " + response.statusCode())))  //manejo de error HTTP
        //Recibo en forma de EventInputDTO
        .bodyToMono(GetEventsResponse.class)
        .block();
  }

  @Override
  public void refreshSources(LocalDateTime lastUpdate) {
    //Lo mismo que la otra pero con query lastUpdate
  }
}


