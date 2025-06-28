package ar.utn.edu.frba.ddsi.models.repositories;

import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.values.SourceKey;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import ar.utn.edu.frba.ddsi.models.entities.event.values.Consensus;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventRepository {
  void save(Event event);

  List<Event> findByDeleted(boolean deleted);

  Event findById(Long eventId);

  List<Event> findAllById(Set<Long> eventIds);

  //Not eliminated & After date
  List<Event> findAfterDate(LocalDateTime lastUpdate);

  //Not eliminated & SourceKey
  List<Event> findBySourceKey(SourceKey sourceKey);

  //Not eliminated & Filtered
  List<Event> findFiltered(
      String category,
      LocalDateTime untilUploadDate,
      LocalDateTime fromUploadDate,
      LocalDateTime untilEventDate,
      LocalDateTime fromEventDate,
      Consensus consensus
  );

  List<Event> findFilteredById(
      Set<Long> eventsIds,
      String category,
      LocalDateTime untilUploadDate,
      LocalDateTime fromUploadDate,
      LocalDateTime untilEventDate,
      LocalDateTime fromEventDate,
      Consensus consensus
  );


}
