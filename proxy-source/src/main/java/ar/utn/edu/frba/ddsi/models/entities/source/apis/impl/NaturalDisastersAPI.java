package ar.utn.edu.frba.ddsi.models.entities.source.apis.impl;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.apis.IAPI;
import ar.utn.edu.frba.ddsi.models.external.LoginRequest;
import ar.utn.edu.frba.ddsi.models.external.LoginResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Set;

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
    //TODO Obtener accessToken
    return webClient.post()
        .uri("/api/login")
        .bodyValue(new LoginRequest(email, password))
        .retrieve()
        .bodyToMono(LoginResponse.class)
        .map(LoginResponse::getAccessToken)
        .block();
  }

  @Override
  public Set<Event> importEvents() {
    return null;
  }
}
