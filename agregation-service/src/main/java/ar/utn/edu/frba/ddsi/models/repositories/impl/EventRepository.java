package ar.utn.edu.frba.ddsi.models.repositories.impl;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.event.values.EventKey;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EventRepository implements IEventRepository {
    Map<EventKey, Event> events = new HashMap<>();

    @Override
    public void save(Event event) {
        events.put(event.getId(), event);
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(events.values());
    }

    @Override
    public List<Event> findAllById(Set<EventKey> eventIds) {
        return eventIds.stream().map(id -> events.get(id)).toList();
    }

    @Override
    public List<Event> findByNewOrModified() {
        return events.values().stream().filter(Event::isNewOrModified).toList();
    }
}
