package ar.utn.edu.frba.ddsi.models.entities.collections.conditions;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;

public abstract class Condition {

  public abstract boolean isSatisfiedBy(Event event);
}
