package models.entities.source.apis.impl;

import models.entities.event.Event;
import models.entities.source.apis.IAPI;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Set;

public class NaturalDisastersAPI implements IAPI {

  private final String accessToken;
  private final WebClient webClient;

  public NaturalDisastersAPI(String baseUrl, String email, String password) {
    this.webClient = WebClient.builder()
        .baseUrl(baseUrl)
        .build();
    this.accessToken = login(email, password);
  }

  public String login(String email, String password) {
    //TODO Obtener accessToken
    return "";
  }


  @Override
  public Set<Event> importEvents() {
    //TODO Importar eventos en base a cada API
    return Set.of();
  }
}
