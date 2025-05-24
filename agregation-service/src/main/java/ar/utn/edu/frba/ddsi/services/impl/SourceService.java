package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.config.WebClientConfig;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.services.ISourceService;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SourceService implements ISourceService {

  @Autowired
  private WebClientConfig webClientConfig;

  @Autowired
  private IEventRepository eventRepository;

  @Override
  @PostConstruct
  public void initSources(){
    List<WebClient> sourceClients = webClientConfig.getAllClients();

    for(WebClient sourceClient : sourceClients){
      // Hacer GET /events
      //Transformar lo recibido a Event
      //Guardar en el Repo los events que te devuelve
      //Ser feliz
    }
  }

  @Override
  public void refreshSources(LocalDateTime lastUpdate){
    //Lo mismo que la otra pero con query lastUpdate
  }
}
