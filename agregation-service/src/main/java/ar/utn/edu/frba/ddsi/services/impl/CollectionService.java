package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.exceptions.NotFoundException;
import ar.utn.edu.frba.ddsi.models.dtos.input.CollectionCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.collections.Collection;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.Source;
import ar.utn.edu.frba.ddsi.models.repositories.ICollectionRepository;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.services.ICollectionService;
import ar.utn.edu.frba.ddsi.services.ISourceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionService implements ICollectionService {

  @Autowired
  ISourceService sourceService;

  @Autowired
  ICollectionRepository collectionRepository;

  @Autowired
  IEventRepository eventRepository;

  @Override
  public void create(CollectionCreationDTO collectionDto) {
    Collection collection = Collection.from(collectionDto);
    collectionRepository.save(collection);
  }

  @Override
  public List<EventOutputDTO> getEvents(String handler) {
    if (handler == null) {
      return eventRepository.findAll().stream().map(EventOutputDTO::from).toList();
    } else {
      return getEventsFromCollection(handler);
    }
  }

  private List<EventOutputDTO> getEventsFromCollection(String handler) {
    Collection collection = collectionRepository.findByHandler(handler);
    if (collection == null) throw new NotFoundException("Collection not found - Handler: " + handler);

    List<Event> events = eventRepository.findById(collection.getEventsIds());
    return events.stream().map(EventOutputDTO::from).toList();
  }
}
