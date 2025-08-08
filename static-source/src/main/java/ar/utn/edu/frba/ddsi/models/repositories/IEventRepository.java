package ar.utn.edu.frba.ddsi.models.repositories;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.time.LocalDateTime;
import java.util.List;

public interface IEventRepository {
  void save(Event event);

  Event findById(Long eventId);

  List<Event> findByDeleted(boolean deleted);

  //Not deleted & by sourceId
  List<Event> findBySourceId(Long sourceId);
}
