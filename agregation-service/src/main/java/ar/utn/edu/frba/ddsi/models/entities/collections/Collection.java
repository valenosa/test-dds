package ar.utn.edu.frba.ddsi.models.entities.collections;

import ar.utn.edu.frba.ddsi.models.dtos.input.CollectionCreationDTO;
import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.values.CollectionCriteria;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.impl.Source;
import java.time.LocalDateTime;
import java.util.HashSet;
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

  private final List<Source> sources;
  private final Set<Event> events;
  private final CollectionCriteria collectionCriteria;

  public static Collection from(CollectionCreationDTO dto, List<Source> sources) {
    CollectionCriteria collectionCriteria = CollectionCriteria.from(dto.getConditions());

    return new Collection(dto.getTitle(), dto.getDescription(), sources, collectionCriteria);
  }

  public Collection(String title, String description, List<Source> sources, CollectionCriteria collectionCriteria) {
    this.title = title;
    this.description = description;
    this.collectionCriteria = collectionCriteria;
    this.sources = sources;
    this.events = new HashSet<>();

    this.refresh(null);
  }

  public void refresh(LocalDateTime lastUpdate) {
    for (Source source : this.sources) {
      for (Event event : source.getEvents(lastUpdate)) {
        if(this.collectionCriteria.isSatisfiedBy(event)) {
          events.add(event);
        }
        else {
          events.remove(event);
        }
      }
    }
  }
}