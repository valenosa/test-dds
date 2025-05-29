package ar.utn.edu.frba.ddsi.models.repositories.impl;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepository implements IEventRepository {
  Map<Long, Event> events = new HashMap<>();
  private final AtomicLong idGenerator = new AtomicLong(1);

  @Override
  public Event save(Event event) {
    if (event.getId() == null) {
      Long id = idGenerator.getAndIncrement();
      event.setId(id);
      events.put(id, event);
    } else {
      events.put(event.getId(), event);
    }
    return event;
  }

  @Override
  public List<Event> findAll() {
    return events.values().stream().toList();
  }

  @Override
  public List<Event> findByDeleted(boolean deleted) {
    return events.values().stream().filter(e -> e.isDeleted() == deleted).toList();
  }

  @Override
  public List<Event> findByAccepted() {
    return this.findByDeleted(false).stream().filter(Event::isAccepted).toList();
  }


  @Override
  public List<Event> findAfterDate(LocalDateTime lastUpdate) {
    return this.findByAccepted().stream().filter(e -> e.getUploadDate().isAfter(lastUpdate)).toList();
  }

  @Override
  public Event delete(Event event) { //Devenota: por ahora es igual a sabe, pero repository NECESITA una funcion delete.
    this.save(event);
    return event;
  }

  public Event findById(Long eventId) {
    return events.get(eventId);
  }

}
