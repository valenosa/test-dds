package ar.utn.edu.frba.ddsi.models.entities.observer;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.Source;

import java.util.Set;

public interface ISubscriber {
  void notifyEvents(Set<Event> events);

  void notifySource(Source source);
}
