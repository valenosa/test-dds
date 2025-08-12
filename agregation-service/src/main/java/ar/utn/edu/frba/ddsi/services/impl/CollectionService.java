package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.exceptions.NotFoundException;
import ar.utn.edu.frba.ddsi.models.dtos.input.collection.CollectionCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.collection.CollectionOutputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.event.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.collections.Collection;
import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.values.CollectionCriteria;
import ar.utn.edu.frba.ddsi.models.entities.source.Source;
import ar.utn.edu.frba.ddsi.models.repositories.ICollectionRepository;
import ar.utn.edu.frba.ddsi.models.repositories.ISourceRepository;
import ar.utn.edu.frba.ddsi.services.ICollectionService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    // Get sources from repository by IDs
    List<Source> collectionSources = collectionDto.getSourceIds().stream()
        .map(sourceRepository::findById)
        .toList();

    //TODO: validar que todas las fuentes solicitadas existan, tirar error en caso contrario

    //Construct CollectionCriteria from DTO
    CollectionCriteria collectionCriteria = CollectionCriteria.from(collectionDto.getConditions());

    // Construct Collection
    Collection collection = Collection.builder()
        .title(collectionDto.getTitle())
        .description(collectionDto.getDescription())
        .sources(collectionSources)
        .collectionCriteria(collectionCriteria)
        .build();

    //Refresh collection to populate events
    collection.refresh(null);

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
    if (collection == null) throw new NotFoundException("Collection not found - Handler: " + handler);

    // Get events from collection
    List<EventOutputDTO> dtoEvents = new ArrayList<>(collection.getEvents().stream().map(EventOutputDTO::from).toList());

    //Get events from sources Metamapa
    dtoEvents.addAll(
        collection.getMetamapaSources()
            .parallelStream()
            .flatMap(source -> source.fetchEvents().stream().map(EventOutputDTO::from)) //TODO: Mandar por query params al fetchEvents el collection criteria como filtros
            .toList()
    );

    return dtoEvents.stream()
        .filter(e -> (category == null || e.getCategory().equals(category)))
        .filter(e -> (untilUploadDate == null || e.getUploadDate().isBefore(untilUploadDate)))
        .filter(e -> (fromUploadDate == null || e.getUploadDate().isAfter(fromUploadDate)))
        .filter(e -> (untilEventDate == null || e.getEventDate().isBefore(untilEventDate)))
        .filter(e -> (fromEventDate == null || e.getEventDate().isAfter(fromEventDate)))
        .toList();
  }

  @Override
  public void refreshCollections(LocalDateTime lastUpdate) {
    for (Collection collection : collectionRepository.findAll()) {
      collection.refresh(lastUpdate);
      collectionRepository.save(collection);
    }
  }
}

