package ar.edu.utn.frba.dds.domain.entities.collections;

import ar.edu.utn.frba.dds.domain.entities.collections.conditions.Condition;
import ar.edu.utn.frba.dds.domain.entities.fact.Fact;
import ar.edu.utn.frba.dds.domain.entities.source.Source;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;


@Getter
public class Collection {
  //-- Descriptivos
  private final String title;
  private final String description;

  //-- Funcionales
  private final Source source;
  private Set<Fact> facts;
  private final BelongingCriteria belongingCriteria;

  public Collection(String title, String description, Source source) {
    this.title = title;
    this.description = description;
    this.belongingCriteria = new BelongingCriteria();
    this.source = source;
    this.fetchFacts();
  }

  /**
   * Calcula en base a su criterio de pertenencia los hechos que pertenecen a la coleccion
   */
  public void fetchFacts() {
    Set<Fact> factSource = this.source.getFacts();
    this.facts = factSource.stream().filter(this::belongsToCollection).collect(Collectors.toSet());
  }

  private boolean belongsToCollection(Fact fact) {
    return belongingCriteria.isSatisfiedBy(fact) && !fact.isEliminated();
  }

  /**
   * Permite agregar condiciones al criterioDePertenencia manteniendo el encapsulamiento
   */
  public void addCondition(Condition condition, Condition... conditions) {
    belongingCriteria.addCondition(condition, conditions);
    this.fetchFacts();
  }

  //TODO: getHechos(filtros...) como sobrecarga que permita obtener hechos filtrados
}