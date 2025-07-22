package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.exceptions.NotFoundException;
import ar.utn.edu.frba.ddsi.models.dtos.input.CollectionCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.CollectionOutputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.collections.Collection;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.impl.Source;
import ar.utn.edu.frba.ddsi.models.repositories.ICollectionRepository;
import ar.utn.edu.frba.ddsi.models.repositories.ISourceRepository;
import ar.utn.edu.frba.ddsi.services.ICollectionService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionService implements ICollectionService {

  @Autowired
  ICollectionRepository collectionRepository;

  @Autowired
  ISourceRepository sourceRepository;

  @Override
  public void create(CollectionCreationDTO collectionDto) {

    List<Source> collectionSources = collectionDto.getSourceIds().stream()
        .map(sourceRepository::findById)
        .toList();

    //TODO: validar que todas las fuentes solicitadas existan, tirar error en caso contrario

    Collection collection = Collection.from(collectionDto, collectionSources);

    collectionRepository.save(collection);
  }

  @Override
  public List<CollectionOutputDTO> getCollections() {
    return collectionRepository.findAll().stream().map(CollectionOutputDTO::from).toList();
  }

  @Override
  public List<EventOutputDTO> getEventsFromCollection(
      String handler,
      String category,
      LocalDateTime untilUploadDate,
      LocalDateTime fromUploadDate,
      LocalDateTime untilEventDate,
      LocalDateTime fromEventDate
  ) {

    Collection collection = collectionRepository.findByHandler(handler);
    if (collection == null) throw new NotFoundException("Collection not found - Handler:" + handler);

    // Get events from collection & filter by query params
    Set<Event> events = collection.getEvents().stream()
        .filter(e -> (category == null || e.getCategory().getName().equals(category)))
        .filter(e -> (untilUploadDate == null || e.getUploadDate().isBefore(untilUploadDate)))
        .filter(e -> (fromUploadDate == null || e.getUploadDate().isAfter(fromUploadDate)))
        .filter(e -> (untilEventDate == null || e.getEventDate().isBefore(untilEventDate)))
        .filter(e -> (fromEventDate == null || e.getEventDate().isAfter(fromEventDate)))
        .collect(Collectors.toSet());

    return events.stream().map(EventOutputDTO::from).toList();
  }

  @Override
  public void refreshCollections(LocalDateTime lastUpdate) {

    for (Collection collection : collectionRepository.findAll()) {
      collection.refresh(lastUpdate);
      collectionRepository.save(collection);
    }


  }
}

