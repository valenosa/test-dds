package ar.utn.edu.frba.ddsi.config;

import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Value("${static-source.api.base-url}")
  private String staticUrl;

  @Value("${dynamic-source.api.base-url}")
  private String dynamicUrl;

  @Value("${proxy-source.api.base-url}")
  private String proxyUrl;

  private final Map<Origin, WebClient> webClients = new HashMap<>();

  @PostConstruct
  public void init() {
    webClients.put(Origin.STATIC, WebClient.builder().baseUrl(staticUrl).build());
    webClients.put(Origin.DYNAMIC, WebClient.builder().baseUrl(dynamicUrl).build());
    webClients.put(Origin.PROXY, WebClient.builder().baseUrl(proxyUrl).build());
  }

  public WebClient getClient(Origin sourceType) {
    return webClients.get(sourceType);
  }

  public List<WebClient> getAllClients() {
    return webClients.values().stream().toList();
  }
}
