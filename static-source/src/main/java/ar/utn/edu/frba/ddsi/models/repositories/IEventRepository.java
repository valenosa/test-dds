package ar.utn.edu.frba.ddsi.models.repositories;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.util.List;
import java.util.Set;

public interface IEventRepository {
  void save(Event event);

  void save(Set<Event> events);

  List<Event> findAll();

  Event findById(Long eventId);

  List<Event> findBySourceId(Long sourceId);

  List<Event> findByDeleted(boolean deleted);

}
