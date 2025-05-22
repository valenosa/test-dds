package ar.utn.edu.frba.ddsi.models.entities.collections.conditions;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;

public interface ICondition {

  boolean isSatisfiedBy(Event event);
}
