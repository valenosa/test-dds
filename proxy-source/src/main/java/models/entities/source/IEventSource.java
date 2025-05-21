package models.entities.source;

import models.entities.event.Event;

import java.util.Set;

public interface IEventSource {
  Set<Event> fetchEvents();
}
