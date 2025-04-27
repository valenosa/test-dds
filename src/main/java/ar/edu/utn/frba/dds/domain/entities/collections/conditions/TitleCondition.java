package ar.edu.utn.frba.dds.domain.entities.collections.conditions;

import ar.edu.utn.frba.dds.domain.entities.fact.Fact;

public class TitleCondition extends Condition {

  String title;

  public TitleCondition(String title) {
    this.title = title;
  }

  public boolean isSatisfiedBy(Fact fact) {
    return this.title.equals(fact.getTitle());
  }

}
