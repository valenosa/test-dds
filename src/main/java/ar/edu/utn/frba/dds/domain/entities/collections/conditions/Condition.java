package ar.edu.utn.frba.dds.domain.entities.collections.conditions;

import ar.edu.utn.frba.dds.domain.entities.fact.Fact;

public abstract class Condition {

  public abstract boolean isSatisfiedBy(Fact fact);
}
