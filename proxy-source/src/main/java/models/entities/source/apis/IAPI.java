package models.entities.source.apis;

import java.util.Set;
import models.entities.event.Event;

public interface IAPI {
  Set<Event> importEvents();
}


