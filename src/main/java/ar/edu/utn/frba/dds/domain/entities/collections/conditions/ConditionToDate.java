package ar.edu.utn.frba.dds.domain.entities.collections.conditions;

import ar.edu.utn.frba.dds.domain.entities.fact.Fact;
import java.time.LocalDate;

public class ConditionToDate extends Condition {

  LocalDate to;

  public ConditionToDate(LocalDate to) {
    this.to = to;
  }

  @Override
  public boolean fulfills(Fact fact) {
    return fact.getDateEvent().isBefore(to);
  }
}
