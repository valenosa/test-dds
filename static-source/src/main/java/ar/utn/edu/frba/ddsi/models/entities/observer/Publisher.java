package ar.utn.edu.frba.ddsi.models.entities.observer;

import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.impl.Source;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Publisher {
  Set<ISubscriber> subscribers;

  public Publisher() {
    this.subscribers = new HashSet<>();
  }

  public void subscribe(ISubscriber subscriber) {
    subscribers.add(subscriber);
  }

  public void unsubscribe(ISubscriber subscriber) {
    subscribers.remove(subscriber);
  }

  public void notifyNewEvents(Set<Event> events) {
    for (ISubscriber subscriber : subscribers) {
      subscriber.notifyEvents(events);
    }
  }

  public void notifyNewSource(Source source) {
    for (ISubscriber subscriber : subscribers) {
      subscriber.notifySource(source);
    }
  }
}
