package ar.utn.edu.frba.ddsi.models.entities.observer;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.impl.Source;

import java.util.List;

public interface ISubscriber {
  void notifyEvents(List<Event> events);

  void notifySource(Source source);
}
