package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.exceptions.NotFoundException;
import ar.utn.edu.frba.ddsi.models.dtos.input.CollectionCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.CollectionOutputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.collections.Collection;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.repositories.ICollectionRepository;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.services.ICollectionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionService implements ICollectionService {

  @Autowired
  IEventRepository eventRepository;

  @Autowired
  ICollectionRepository collectionRepository;

  @Override
  public void create(CollectionCreationDTO collectionDto) {
    Collection collection = this.fillCollection(Collection.from(collectionDto));

    collectionRepository.save(collection);
  }

  private Collection fillCollection(Collection collection) {

    List<Event> sourceEvents = collection.getSourceIds().stream().map(eventRepository::findBySourceKey).flatMap(List::stream).toList();
    collection.refresh(sourceEvents);

    return collection;
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

    Set<Long> eventsIds = collection.getEventsIds();

    return eventRepository
        .findFilteredById(eventsIds, category, untilUploadDate, fromUploadDate, untilEventDate, fromEventDate)
        .stream()
        .map(EventOutputDTO::from)
        .toList();

  }

  @Override
  public void refreshCollections(LocalDateTime lastUpdate) {
    List<Event> newOrModifiedEvents = eventRepository.findAfterDate(lastUpdate);
    collectionRepository.findAll().forEach(col -> refreshCollection(col, newOrModifiedEvents));
  }

  private void refreshCollection(Collection collection, List<Event> newOrModifiedEvents) {
    List<Event> newEventsFromCollectionSources = newOrModifiedEvents.stream()
        .filter(event -> collection.getSourceIds().stream()
            .anyMatch(event::isFromSource))
        .toList();

    collection.refresh(newEventsFromCollectionSources);

    collectionRepository.save(collection);
  }
}

