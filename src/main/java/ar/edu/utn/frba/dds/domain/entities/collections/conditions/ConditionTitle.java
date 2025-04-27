package ar.edu.utn.frba.dds.domain.entities.collections.conditions;

import ar.edu.utn.frba.dds.domain.entities.fact.Fact;

public class ConditionTitle extends Condition {

  String title;

  public ConditionTitle(String title) {
    this.title = title;
  }

  public boolean fulfills(Fact fact) {
    return this.title.equals(fact.getTitle());
  }

}
