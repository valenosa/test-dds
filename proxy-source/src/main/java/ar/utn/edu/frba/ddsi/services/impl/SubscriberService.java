package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.models.dtos.input.SubscriberInputDTO;
import ar.utn.edu.frba.ddsi.models.entities.observer.Aggregator;
import ar.utn.edu.frba.ddsi.models.entities.observer.ISubscriber;
import ar.utn.edu.frba.ddsi.models.entities.observer.Publisher;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService {

  Publisher publisher;

  public SubscriberService(Publisher publisher) {  //Esto deberia ser lo mismo que el autowired
    this.publisher = publisher;
  }

  public void subscribe(SubscriberInputDTO dto) {
    ISubscriber subscriber = Aggregator.from(dto); //A futuro necesitariamos un builder si hay otros tipos de suscribers

    publisher.subscribe(subscriber);
  }
}
