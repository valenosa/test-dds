package ar.utn.edu.frba.ddsi.models.entities.collections.conditions.impl;

import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.Condition;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.time.LocalDate;

public class UntilDateCondition extends Condition {

  LocalDate to;

  public UntilDateCondition(LocalDate to) {
    this.to = to;
  }

  @Override
  public boolean isSatisfiedBy(Event event) {
    return event.getEventDate().isBefore(to);
  }
}
