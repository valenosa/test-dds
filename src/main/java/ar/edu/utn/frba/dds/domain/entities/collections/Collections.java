package ar.edu.utn.frba.dds.domain.entities.collections;

import ar.edu.utn.frba.dds.domain.entities.collections.conditions.Condition;
import ar.edu.utn.frba.dds.domain.entities.fact.Fact;
import ar.edu.utn.frba.dds.domain.entities.source.Source;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;


@Getter
public class Collections {
  //-- Descriptivos
  private final String title;
  private final String description;

  //-- Funcionales
  private final Source source;
  private Set<Fact> facts;
  private final JudgmentBelonging judgmentBelonging;

  public Collections(String title, String description, Source source) {
    this.title = title;
    this.description = description;
    this.judgmentBelonging = new JudgmentBelonging();
    this.source = source;
    this.calculateFacts();
  }

  /**
   * Calcula en base a su criterio de pertenencia los hechos que pertenecen a la coleccion
   */
  public void calculateFacts() {
    Set<Fact> sourceFacts = this.source.getFacts();
    this.facts = sourceFacts.stream().filter(this::belongs).collect(Collectors.toSet());
  }

  private boolean belongs(Fact fact) {
    return judgmentBelonging.fulfillConditions(fact) && !fact.isEliminated();
  }

  /**
   * Permite agregar condiciones al criterioDePertenencia manteniendo el encapsulamiento
   */
  public void addCondition(Condition condition, Condition... conditions) {
    judgmentBelonging.addCondicion(condition, conditions);
    this.calculateFacts();
  }

  //TODO: getHechos(filtros...) como sobrecarga que permita obtener hechos filtrados
}