package ar.utn.edu.frba.ddsi.models.entities.observer;

import ar.utn.edu.frba.ddsi.models.dtos.input.SubscriberInputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.source.impl.Source;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class Aggregator implements ISubscriber {

  WebClient clientCallBack;

  public Aggregator(String callbackUrl) {
    this.clientCallBack = WebClient.builder().baseUrl(callbackUrl).build();
  }

  public static ISubscriber from(SubscriberInputDTO dto) {
    return new Aggregator(dto.getCallbackUrl());
  }

  public void notifyEvents(List<EventOutputDTO> events) {
    clientCallBack.post()
        .uri("/events")
        .bodyValue(events)
        .retrieve()
        .bodyToMono(Void.class)//? Hay respuesta
        .block();
  }

  public void notifySource(Source source) {
    clientCallBack.post()
        .uri("/sources")
        .bodyValue(source)
        .retrieve()
        .bodyToMono(Void.class)
        .block();
  }
}
