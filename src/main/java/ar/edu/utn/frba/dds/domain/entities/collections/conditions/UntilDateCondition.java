package ar.edu.utn.frba.dds.domain.entities.collections.conditions;

import ar.edu.utn.frba.dds.domain.entities.fact.Fact;
import java.time.LocalDate;

public class UntilDateCondition extends Condition {

  LocalDate to;

  public UntilDateCondition(LocalDate to) {
    this.to = to;
  }

  @Override
  public boolean isSatisfiedBy(Fact fact) {
    return fact.getEventDate().isBefore(to);
  }
}
