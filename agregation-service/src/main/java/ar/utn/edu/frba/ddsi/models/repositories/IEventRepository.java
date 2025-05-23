package ar.utn.edu.frba.ddsi.models.repositories;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;

import ar.utn.edu.frba.ddsi.models.entities.event.values.EventKey;
import java.util.List;
import java.util.Set;

public interface IEventRepository {
  void save(Event event);

  List<Event> findAll();
  List<Event> findAllById(Set<EventKey> eventIds);
}
