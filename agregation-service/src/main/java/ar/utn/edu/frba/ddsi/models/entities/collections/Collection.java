package ar.utn.edu.frba.ddsi.models.entities.collections;

import ar.utn.edu.frba.ddsi.models.dtos.input.CollectionCreationDTO;
import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.values.CollectionCriteria;
import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.values.SourceKey;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Collection {

  @Setter
  private String handler;

  private final String title;
  private final String description;

  private Set<SourceKey> sourceIds;
  private Set<Long> eventsIds;
  private final CollectionCriteria collectionCriteria;

  public static Collection from(CollectionCreationDTO dto) {
    CollectionCriteria collectionCriteria = CollectionCriteria.from(dto.getConditions());

    return new Collection(dto.getTitulo(), dto.getDescription(), collectionCriteria);
  }

  public Collection(String title, String description, CollectionCriteria collectionCriteria) {
    this.title = title;
    this.description = description;
    this.collectionCriteria = collectionCriteria;
    //TODO: Agregar las sourceIds
  }

  public void refresh(List<Event> newEvents) {
    newEvents.forEach(this::addOrRemove);
  }

  private void addOrRemove(Event event) {
    boolean belongsToCollection = collectionCriteria.isSatisfiedBy(event);

    if(belongsToCollection){
      eventsIds.add(event.getId());
    }
    else {
      //Si tras una modificacion deja de estar includo se elimina
      eventsIds.remove(event.getId());
    }
  }
}