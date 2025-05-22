package ar.utn.edu.frba.ddsi.models.entities.collections;

import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.Condition;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.Source;
import java.util.Collections;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


public class Collection {

  @Getter @Setter
  private String handler;

  //-- Descriptivos
  @Getter private final String title;
  @Getter private final String description;

  //-- Funcionales
  // private final Source source;
  private Set<Event> events;
  private final CollectionCriteria collectionCriteria;

  public Set<Event> getEvents() {
    return Collections.unmodifiableSet(events);
  }

  public Collection(String title, String description, Source source) {
    this.title = title;
    this.description = description;
    this.collectionCriteria = new CollectionCriteria();
    //this.source = source;
    this.fetchEvents();
  }

  /**
   * Calcula en base a su criterio de pertenencia los hechos que pertenecen a la coleccion
   */
  public void fetchEvents() {
//    Set<Event> eventSource = this.source.fetchEvents();
//    this.events =
//        eventSource.stream().filter(this::belongsToCollection).collect(Collectors.toSet());
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