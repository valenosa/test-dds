package repositories;

import models.entities.event.Event;

import java.util.List;
import java.util.Set;

public interface IEventRepository {
  void save(Event event);
  void save(Set<Event> events);
  List<Event> findAll();
  List<Event> findAll(Set<Long> ids);
  List<Event> findByDeleted(boolean deleted);
  Event findById(Long eventId);
}