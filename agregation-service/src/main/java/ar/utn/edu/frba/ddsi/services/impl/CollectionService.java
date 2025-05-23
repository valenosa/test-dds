package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.exceptions.NotFoundException;
import ar.utn.edu.frba.ddsi.models.dtos.input.CollectionCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.CollectionOutputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.collections.Collection;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.repositories.ICollectionRepository;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.models.repositories.impl.EventRepository;
import ar.utn.edu.frba.ddsi.services.ICollectionService;

import java.util.List;
import java.util.Set;

import ar.utn.edu.frba.ddsi.services.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionService implements ICollectionService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ICollectionRepository collectionRepository;

    @Override
    public void create(CollectionCreationDTO collectionDto) {
        Collection collection = Collection.from(collectionDto);
        collectionRepository.save(collection);
    }

    @Override
    public List<CollectionOutputDTO> getCollections() {
        return collectionRepository.findAll().stream().map(CollectionOutputDTO::from).toList();
    }

    @Override
    public List<EventOutputDTO> getEventsFromCollection(String handler) {
        Collection collection = collectionRepository.findByHandler(handler);
        if (collection == null) throw new NotFoundException("Collection not found - Handler:" + handler);

        Set<Long> eventsIds = collection.getEventsIds();

        List<Event> events = eventRepository.findAllById(eventsIds);
        return events.stream().map(EventOutputDTO::from).toList();
    }


//  private List<EventOutputDTO> getEventsFromCollection(String handler) {
//    Collection collection = collectionRepository.findByHandler(handler);
//    if (collection == null) throw new NotFoundException("Collection not found - Handler: " + handler);

//    List<Event> events = eventRepository.findById(collection.getEventsIds());
//    return events.stream().map(EventOutputDTO::from).toList();
//  }
}
