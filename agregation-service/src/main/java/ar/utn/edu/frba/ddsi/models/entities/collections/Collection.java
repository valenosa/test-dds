package ar.utn.edu.frba.ddsi.models.entities.collections;

import ar.utn.edu.frba.ddsi.models.dtos.input.CollectionCreationDTO;
import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.values.CollectionCriteria;
import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.values.SourceKey;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Collection {

  @Setter
  private String handler;

  private final String title;
  private final String description;

  private final Set<SourceKey> sourceIds;
  private final Set<Long> eventsIds;
  private final CollectionCriteria collectionCriteria;

  public static Collection from(CollectionCreationDTO dto) {
    CollectionCriteria collectionCriteria = CollectionCriteria.from(dto.getConditions());

    Set<SourceKey> sourceIds = dto.getSourceIds().stream().map(SourceKey::from).collect(Collectors.toSet());

    return new Collection(dto.getTitle(), dto.getDescription(), sourceIds, collectionCriteria);
  }

  public Collection(String title, String description, Set<SourceKey> sourceIds, CollectionCriteria collectionCriteria) {
    this.title = title;
    this.description = description;
    this.collectionCriteria = collectionCriteria;
    this.sourceIds = sourceIds;
    this.eventsIds = new HashSet<>();
  }

  public void refresh(List<Event> newEvents) {
    newEvents.forEach(this::addOrRemove);
  }

  private void addOrRemove(Event event) {
    boolean belongsToCollection = collectionCriteria.isSatisfiedBy(event);

    if (belongsToCollection) {
      eventsIds.add(event.getId());
    } else {
      //Si tras una modificacion deja de estar includo se elimina
      eventsIds.remove(event.getId());
    }
  }
}