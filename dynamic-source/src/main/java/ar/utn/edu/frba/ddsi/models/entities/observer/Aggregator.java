package ar.utn.edu.frba.ddsi.models.entities.observer;

import ar.utn.edu.frba.ddsi.models.dtos.input.SubscriberInputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Set;

public class Aggregator implements ISubscriber {

  private final WebClient clientCallBack;
  private final Long sourceClientId;

  public Aggregator(String callbackUrl, Long sourceClientId) {
    this.clientCallBack = WebClient
        .builder()
        .baseUrl(callbackUrl)
        .build();
    this.sourceClientId = sourceClientId;
  }

  public static ISubscriber from(SubscriberInputDTO dto) {
    return new Aggregator(dto.getCallbackUrl(), dto.getSourceClientId());
  }

  public void notifyEvents(Set<Event> events) {
    List<EventOutputDTO> outputEvents = events
        .stream()
        .map(EventOutputDTO::from)
        .toList();

    outputEvents.forEach(event -> event.setSourceClientId(sourceClientId));

    clientCallBack.post()
        .uri("/events")
        .bodyValue(outputEvents)
        .retrieve()
        .bodyToMono(Void.class)//? Hay respuesta
        .block();
  }
}
