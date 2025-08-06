package ar.utn.edu.frba.ddsi.models.entities.observer;

import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.impl.Source;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class Publisher {
  Set<ISubscriber> subscribers;

  public void subscribe(ISubscriber subscriber) {
    subscribers.add(subscriber);
  }

  public void unsubscribe(ISubscriber subscriber) {
    subscribers.remove(subscriber);
  }

  public void notifyNewEvents(List<EventOutputDTO> events) {
    for (ISubscriber subscriber : subscribers) {
      subscriber.notifyEvents(events);
    }
  }

  public void notifyNewSources(Source source) {  //? No deberia ser una sola source?
    for (ISubscriber subscriber : subscribers) {
      subscriber.notifySource(source);
    }
  }
}
