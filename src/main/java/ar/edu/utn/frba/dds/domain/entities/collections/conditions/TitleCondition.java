package ar.edu.utn.frba.dds.domain.entities.collections.conditions;

import ar.edu.utn.frba.dds.domain.entities.report.Report;

public class TitleCondition extends Condition {

  String title;

  public TitleCondition(String title) {
    this.title = title;
  }

  public boolean isSatisfiedBy(Report report) {
    return this.title.equals(report.getTitle());
  }

}
