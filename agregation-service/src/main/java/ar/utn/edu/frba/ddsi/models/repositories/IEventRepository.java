package ar.utn.edu.frba.ddsi.models.repositories;

import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.values.SourceKey;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface IEventRepository {
  void save(Event event);

  List<Event> findByDeleted(boolean deleted);

  Event findById(Long eventId);

  List<Event> findAfterDate(LocalDateTime lastUpdate);

  List<Event> findBySourceKey(SourceKey sourceKey);
}
