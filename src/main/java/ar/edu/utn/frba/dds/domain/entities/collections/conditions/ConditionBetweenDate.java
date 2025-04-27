package ar.edu.utn.frba.dds.domain.entities.collections.conditions;

import ar.edu.utn.frba.dds.domain.entities.fact.Fact;
import java.time.LocalDate;

public class ConditionBetweenDate extends Condition {

  Condition conditionFrom;
  Condition conditionTo;

  public ConditionBetweenDate(LocalDate from, LocalDate to) {
    conditionFrom = new ConditionFromDate(from);
    conditionTo = new ConditionToDate(to);
  }

  @Override
  public boolean fulfills(Fact fact) {
    return conditionFrom.fulfills(fact) && conditionTo.fulfills(fact);
  }
}
