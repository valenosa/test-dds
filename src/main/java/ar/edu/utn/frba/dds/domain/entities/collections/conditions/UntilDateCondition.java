package ar.edu.utn.frba.dds.domain.entities.collections.conditions;

import ar.edu.utn.frba.dds.domain.entities.report.Report;
import java.time.LocalDate;

public class UntilDateCondition extends Condition {

  LocalDate to;

  public UntilDateCondition(LocalDate to) {
    this.to = to;
  }

  @Override
  public boolean isSatisfiedBy(Report report) {
    return report.getEventDate().isBefore(to);
  }
}
