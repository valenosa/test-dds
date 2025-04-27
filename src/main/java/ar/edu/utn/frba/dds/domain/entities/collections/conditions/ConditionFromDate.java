package ar.edu.utn.frba.dds.domain.entities.collections.conditions;

import ar.edu.utn.frba.dds.domain.entities.fact.Fact;
import java.time.LocalDate;

public class ConditionFromDate extends Condition {

  LocalDate from;

  public ConditionFromDate(LocalDate from) {
    this.from = from;
  }

  @Override
  public boolean fulfills(Fact fact) {
    return fact.getDateEvent().isAfter(from);
  }
}