package ar.utn.edu.frba.ddsi.models.entities.source.impl;

import ar.utn.edu.frba.ddsi.models.dtos.input.event.EventInputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.source.SourceClientInputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.source.SourceInputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.source.SubscribeOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.ISourceClientAdapter;
import ar.utn.edu.frba.ddsi.models.entities.source.Source;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Getter
public class SourceClient implements ISourceClientAdapter {

  @Setter
  private Long id;
  private final String url;
  private final WebClient webClient;

  public SourceClient(String url) {
    this.url = url;
    this.webClient = WebClient.builder().baseUrl(url).build();
  }

  public static SourceClient from(SourceClientInputDTO dto) {
    if (dto == null || dto.getUrl() == null || dto.getUrl().isEmpty()) {
      throw new IllegalArgumentException("SourceClient URL cannot be null or empty");
    }
    return new SourceClient(dto.getUrl());
  }

  @Override
  public void subscribe() {
    try {
      webClient.post()
          .uri("/subscribers")
          .bodyValue(SubscribeOutputDTO.from(this))
          .retrieve()
          .bodyToMono(Void.class)
          .block();
    } catch (Exception e) {
      throw new RuntimeException("Error in subscription: " + e.getMessage(), e);
    }
  }

  @Override
  public List<Source> fetchSources() {
    List<SourceInputDTO> sourceDTOs =
        webClient.get()
            .uri("/sources")
            .retrieve()
            .onStatus(HttpStatusCode::isError, response ->
                Mono.error(new RuntimeException("Error fetching sources: " + response.statusCode())))
            .bodyToFlux(SourceInputDTO.class)
            .collectList()
            .block();

    if (sourceDTOs == null) {
      return List.of();
    }

    return sourceDTOs.stream().map(sDto -> Source.from(sDto, this)).toList();
  }

  @Override
  public List<Event> fetchEventsBySource(Source source) {
    List<EventInputDTO> eventsDTOs =
        webClient.get()
            .uri("/sources/" + source.getInClientId() + "/events")
            .retrieve()
            .onStatus(HttpStatusCode::isError, response ->
                Mono.error(new RuntimeException("Error fetching events: " + response.statusCode())))
            .bodyToFlux(EventInputDTO.class)
            .collectList()
            .block();

    if (eventsDTOs == null) {
      return List.of();
    }
    return eventsDTOs.stream().map(e -> Event.from(e, source)).toList();
  }

  @Override
  public void deleteEvent(Event event) {
    try {
      webClient.delete()
          .uri("/events/" + event.getInSourceEventId())
          .retrieve()
          .bodyToMono(Void.class)
          .block();
    } catch (Exception e) {
      throw new RuntimeException("Error updating event in source origin: " + e.getMessage(), e);
    }
  }

}
