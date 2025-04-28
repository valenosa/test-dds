package ar.edu.utn.frba.dds.domain.entities.collections.conditions;

import ar.edu.utn.frba.dds.domain.entities.report.Report;

public abstract class Condition {

  public abstract boolean isSatisfiedBy(Report report);
}
