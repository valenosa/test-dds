package ar.edu.utn.frba.dds.domain.entities.collections.conditions;

import ar.edu.utn.frba.dds.domain.entities.event.Event;

public abstract class Condition {

  public abstract boolean isSatisfiedBy(Event event);
}
