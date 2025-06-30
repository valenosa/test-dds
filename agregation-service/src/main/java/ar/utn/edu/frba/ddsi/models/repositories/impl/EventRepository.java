package ar.utn.edu.frba.ddsi.models.repositories.impl;

import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.values.SourceKey;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Consensus;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepository implements IEventRepository {
  Map<Long, Event> events = new HashMap<>();
  private final AtomicLong idGenerator = new AtomicLong(1);

  @Override
  public void save(Event event) {
    //Internal Id
    if (event.getId() == null) {
      Long id = idGenerator.getAndIncrement();
      event.setId(id);
      events.put(id, event);
    } else {
      events.put(event.getId(), event);
    }
  }

  @Override
  public void saveAll(List<Event> events) {
    for (Event event : events) {
      this.save(event);
    }
  }

  @Override
  public List<Event> findAllEvents() {
    return events.values().stream().toList();
  }

  @Override
  public List<Event> findByDeleted(boolean deleted) {
    return events.values().stream().filter(e -> e.isDeleted() == deleted).toList();
  }

  @Override
  public Event findById(Long id) {
    return events.get(id);
  }

  @Override
  public List<Event> findAllById(Set<Long> eventIds) {
    return events.values().stream().filter(e -> eventIds.contains(e.getId())).toList();
  }

  @Override
  public List<Event> findAfterDate(LocalDateTime lastUpdate) {
    return this.findByDeleted(false).stream().filter(e -> e.getUploadDate().isAfter(lastUpdate)).toList();
  }

  @Override
  public List<Event> findBySourceKey(SourceKey sourceKey) {
    return this.findByDeleted(false).stream().filter(e -> e.isFromSource(sourceKey)).toList();
  }

  @Override
  public List<Event> findFiltered(
      String category,
      LocalDateTime untilUploadDate,
      LocalDateTime fromUploadDate,
      LocalDateTime untilEventDate,
      LocalDateTime fromEventDate,
      Consensus consensus
  ) {
    return filterEvents(
        this.findByDeleted(false),
        category, untilUploadDate, fromUploadDate, untilEventDate, fromEventDate, consensus
    );
  }

  @Override
  public List<Event> findFilteredById(
      Set<Long> eventsIds,
      String category,
      LocalDateTime untilUploadDate,
      LocalDateTime fromUploadDate,
      LocalDateTime untilEventDate,
      LocalDateTime fromEventDate,
      Consensus consensus
  ) {
    return filterEvents(
        this.findAllById(eventsIds),
        category, untilUploadDate, fromUploadDate, untilEventDate, fromEventDate, consensus
    );
  }

  private List<Event> filterEvents(
      List<Event> events,
      String category,
      LocalDateTime untilUploadDate,
      LocalDateTime fromUploadDate,
      LocalDateTime untilEventDate,
      LocalDateTime fromEventDate,
      Consensus consensus
  ) {
    return events.stream()
        .filter(e -> (category == null || e.getCategory().getName().equals(category)))
        .filter(e -> (untilUploadDate == null || e.getUploadDate().isBefore(untilUploadDate)))
        .filter(e -> (fromUploadDate == null || e.getUploadDate().isAfter(fromUploadDate)))
        .filter(e -> (untilEventDate == null || e.getEventDate().isBefore(untilEventDate)))
        .filter(e -> (fromEventDate == null || e.getEventDate().isAfter(fromEventDate)))
        .filter(e -> (consensus == null || e.getConsensus().contains(consensus)))
        .toList();
  }

}
