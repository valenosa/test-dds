package ar.edu.utn.frba.dds.domain.entities.collections.conditions;

import ar.edu.utn.frba.dds.domain.entities.event.Event;
import java.time.LocalDate;

public class BetweenDatesCondition extends Condition {

  Condition fromDateCondition;
  Condition untilDateCondition;

  public BetweenDatesCondition(LocalDate from, LocalDate to) {
    fromDateCondition = new FromDateCondition(from);
    untilDateCondition = new UntilDateCondition(to);
  }

  @Override
  public boolean isSatisfiedBy(Event event) {
    return fromDateCondition.isSatisfiedBy(event) && untilDateCondition.isSatisfiedBy(event);
  }
}
