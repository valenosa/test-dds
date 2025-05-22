package ar.utn.edu.frba.ddsi.models.entities.collections.conditions.impl;

import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.ICondition;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;

import java.time.LocalDate;

public class BetweenDatesCondition implements ICondition {

  ICondition fromDateCondition;
  ICondition untilDateCondition;

  public BetweenDatesCondition(LocalDate from, LocalDate to) {
    fromDateCondition = new FromDateCondition(from);
    untilDateCondition = new UntilDateCondition(to);
  }

  @Override
  public boolean isSatisfiedBy(Event event) {
    return fromDateCondition.isSatisfiedBy(event) && untilDateCondition.isSatisfiedBy(event);
  }
}
