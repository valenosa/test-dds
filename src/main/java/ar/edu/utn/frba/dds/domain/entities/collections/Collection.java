package ar.edu.utn.frba.dds.domain.entities.collections;

import ar.edu.utn.frba.dds.domain.entities.collections.conditions.Condition;
import ar.edu.utn.frba.dds.domain.entities.event.Event;
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
  private Set<Event> events;
  private final CollectionCriteria collectionCriteria;

  public Collection(String title, String description, Source source) {
    this.title = title;
    this.description = description;
    this.collectionCriteria = new CollectionCriteria();
    this.source = source;
    this.fetchEvents();
  }

  /**
   * Calcula en base a su criterio de pertenencia los hechos que pertenecen a la coleccion
   */
  public void fetchEvents() {
    Set<Event> eventSource = this.source.getEvents();
    this.events = eventSource.stream().filter(this::belongsToCollection).collect(Collectors.toSet());
  }

  private boolean belongsToCollection(Event event) {
    return collectionCriteria.isSatisfiedBy(event) && !event.isDeleted();
  }

  /**
   * Permite agregar condiciones al criterioDePertenencia manteniendo el encapsulamiento
   */
  public void addCondition(Condition condition, Condition... conditions) {
    collectionCriteria.addCondition(condition, conditions);
    this.fetchEvents();
  }

  //TODO: getHechos(filtros...) como sobrecarga que permita obtener hechos filtrados
}