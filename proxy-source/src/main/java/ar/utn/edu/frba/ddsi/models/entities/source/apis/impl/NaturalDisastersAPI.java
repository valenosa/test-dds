package ar.utn.edu.frba.ddsi.models.entities.source.apis.impl;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.apis.IAPI;
import ar.utn.edu.frba.ddsi.models.external.EventPage;
import ar.utn.edu.frba.ddsi.models.external.LoginRequest;
import ar.utn.edu.frba.ddsi.models.external.LoginResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class NaturalDisastersAPI implements IAPI {

  private final String accessToken;
  private final WebClient webClient;

  public NaturalDisastersAPI(
      @Value("${natural-disasters.api.base-url}") String baseUrl,
      @Value("${natural-disasters.api.credentials.email}") String email,
      @Value("${natural-disasters.api.credentials.password}") String password) {

    this.webClient = WebClient.builder()
        .baseUrl(baseUrl)
        .build();
    this.accessToken = login(email, password);
  }

  private String login(String email, String password) {
    return webClient.post()
        .uri("/api/login")
        .bodyValue(new LoginRequest(email, password))
        .retrieve()
        .bodyToMono(LoginResponse.class)
        .map(LoginResponse::getAccessToken)
        .block();
  }

  @Override
  public Set<Event> importEvents(LocalDateTime lastUpdate) {
    Set<Event> events = new HashSet<>();
    int currentPage = 1;

    while (true) {
      EventPage page = this.importPage(currentPage);

      events.addAll(page.getEvents(lastUpdate));

      if (page.getNextPageUrl() == null) {
        break;
      }

      //TODO Manejar caso de que la API esté mal y nunca devuelva nextPageUrl null (si no, espera activa)

      currentPage++;
    }

    return events;
  }

  private EventPage importPage(int currentPage) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/api/desastres")
            .queryParam("page", currentPage)
            .queryParam("per_page", 100)
            .build())
        .header("Authorization", "Bearer " + accessToken)
        .retrieve()
        .bodyToMono(EventPage.class)
        .block();
  }

}
