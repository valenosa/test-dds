package ar.utn.edu.frba.ddsi.models.repositories;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.util.List;


public interface IEventRepository {
  void save(Event event);
  void save(List<Event> events);
  List<Event> findAll();
  Event findById(Long eventId);
}
