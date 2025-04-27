package ar.edu.utn.frba.dds.domain.entities.collections.conditions;

import ar.edu.utn.frba.dds.domain.entities.fact.Fact;
import java.time.LocalDate;

public class FromDateCondition extends Condition {

  LocalDate from;

  public FromDateCondition(LocalDate from) {
    this.from = from;
  }

  @Override
  public boolean isSatisfiedBy(Fact fact) {
    return fact.getDateEvent().isAfter(from);
  }
}