package ar.utn.edu.frba.ddsi.models.entities.collections.conditions.impl;

import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.values.ICondition;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.time.LocalDate;

public class FromDateCondition implements ICondition {

  LocalDate from;

  public FromDateCondition(LocalDate from) {
    this.from = from;
  }

  @Override
  public boolean isSatisfiedBy(Event event) {
    return event.getEventDate().isAfter(from);
  }
}