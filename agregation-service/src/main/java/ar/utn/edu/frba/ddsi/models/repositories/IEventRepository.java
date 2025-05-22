package ar.utn.edu.frba.ddsi.models.repositories;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;

import java.util.List;
import java.util.Set;

public interface IEventRepository {
  void save(Event event);
  List<Event> findAll();
  List<Event> findByDeleted(boolean deleted);
  Event findById(Long eventId);
  List<Event> findById(Set<Long> eventIds);
}
