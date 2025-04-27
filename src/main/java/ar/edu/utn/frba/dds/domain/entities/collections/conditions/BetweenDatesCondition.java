package ar.edu.utn.frba.dds.domain.entities.collections.conditions;

import ar.edu.utn.frba.dds.domain.entities.fact.Fact;
import java.time.LocalDate;

public class BetweenDatesCondition extends Condition {

  Condition fromDateCondition;
  Condition untilDateCondition;

  public BetweenDatesCondition(LocalDate from, LocalDate to) {
    fromDateCondition = new FromDateCondition(from);
    untilDateCondition = new UntilDateCondition(to);
  }

  @Override
  public boolean isSatisfiedBy(Fact fact) {
    return fromDateCondition.isSatisfiedBy(fact) && untilDateCondition.isSatisfiedBy(fact);
  }
}
