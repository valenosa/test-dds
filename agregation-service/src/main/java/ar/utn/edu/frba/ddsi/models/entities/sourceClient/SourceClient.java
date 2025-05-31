package ar.utn.edu.frba.ddsi.models.entities.sourceClient;

import ar.utn.edu.frba.ddsi.models.dtos.input.EventInputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.SourceClientInputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;


@Getter
public class SourceClient {

  @Setter
  private Long id;
  private final String url;
  private final Origin type;
  private final WebClient webClient;

  public SourceClient(String url, Origin type) {
    this.url = url;
    this.type = type;
    this.webClient = WebClient.builder().baseUrl(url).build();
  }

  public List<Event> fetchEvents(LocalDateTime lastUpdate) {
    List<EventInputDTO> eventsDTOs = this.webClient.get()
        .uri(
            uriBuilder ->
                uriBuilder
                    .path("/events")
                    .queryParam("lastUpdate", lastUpdate)
                    .build()
        )
        .retrieve()
        .onStatus(HttpStatusCode::isError, response ->
            Mono.error(new RuntimeException("Error HTTP: " + response.statusCode())))
        .bodyToFlux(EventInputDTO.class)
        .collectList()
        .block();

    if (eventsDTOs == null) {
      return List.of();
    }
    return eventsDTOs.stream()
        .map(dto -> Event.from(dto, this.id))
        .toList();
  }

  public static SourceClient from(SourceClientInputDTO dto) {
    if (dto == null || dto.getUrl() == null || dto.getUrl().isEmpty()) {
      throw new IllegalArgumentException("SourceClient URL cannot be null or empty");
    }
    return new SourceClient(dto.getUrl(), dto.getType());
  }
}
