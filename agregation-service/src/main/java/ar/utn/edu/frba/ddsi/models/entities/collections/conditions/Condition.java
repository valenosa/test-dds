package ar.utn.edu.frba.ddsi.models.entities.collections.conditions;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;

public interface Condition {

  boolean isSatisfiedBy(Event event);
}
