package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.SourceOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import ar.utn.edu.frba.ddsi.models.entities.observer.Publisher;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.services.ISourceService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceService implements ISourceService {

  @Autowired
  private IEventRepository eventRepository;

  @Override
  public List<SourceOutputDTO> getSources() {
    //Dynamic has no sources, but we mock one for traceability purposes between microservices
    SourceOutputDTO dto = new SourceOutputDTO();
    dto.setInClientId(1L);
    dto.setType(Origin.DYNAMIC);

    List<SourceOutputDTO> list = new ArrayList<>();
    list.add(dto);
    return list;
  }

  @Override
  public List<EventOutputDTO> getAllEventsBySourceId(Long id) {

    List<Event> events = eventRepository.findBySourceId(id);

    //?TODO Return 404 if source not found

    return events
          .stream()
          .map(EventOutputDTO::from)
          .toList();
  }
}