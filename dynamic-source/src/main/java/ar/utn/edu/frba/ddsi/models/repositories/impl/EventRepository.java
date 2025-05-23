package ar.utn.edu.frba.ddsi.models.repositories.impl;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
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
  public void save(Event event) {
    if (event.getId() == null) {
      Long id = idGenerator.getAndIncrement();
      event.setId(id);
      events.put(id, event);
    } else {
      events.put(event.getId(), event);
    }
  }

  @Override
  public void save(List<Event> events) {
    events.forEach(this::save);
  }

  @Override
  public List<Event> findAll() {
    return events.values().stream().toList();
  }

  public Event findById(Long eventId) {
    return events.get(eventId);
  }

}
