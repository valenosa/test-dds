package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.models.dtos.input.SubscriberInputDTO;
import ar.utn.edu.frba.ddsi.models.entities.observer.Aggregator;
import ar.utn.edu.frba.ddsi.models.entities.observer.ISubscriber;
import ar.utn.edu.frba.ddsi.models.entities.observer.Publisher;
import ar.utn.edu.frba.ddsi.services.ISubscriberService;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService implements ISubscriberService {

  Publisher publisher;

  public SubscriberService(Publisher publisher) {
    this.publisher = publisher;
  }

  @Override
  public void subscribe(SubscriberInputDTO dto) {
    ISubscriber subscriber = Aggregator.from(dto);

    publisher.subscribe(subscriber);
  }
}
