package models.entities.source;

import models.entities.event.Event;

import java.util.Set;

public interface IEventSource {
  Set<Long> getEventsIds();
  Set<Event> fetchEvents();
}
