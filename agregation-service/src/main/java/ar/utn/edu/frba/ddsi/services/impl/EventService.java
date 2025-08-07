package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.exceptions.NotFoundException;
import ar.utn.edu.frba.ddsi.models.dtos.input.event.EventInputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.event.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.source.Source;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.models.repositories.impl.SourceRepository;
import ar.utn.edu.frba.ddsi.services.IEventService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService implements IEventService {

  @Autowired
  IEventRepository eventRepository;
  @Autowired
  private SourceRepository sourceRepository;


  @Override
  public List<EventOutputDTO> getEvents(
      String category,
      LocalDateTime untilUploadDate,
      LocalDateTime fromUploadDate,
      LocalDateTime untilEventDate,
      LocalDateTime fromEventDate) {
    return eventRepository.findFiltered(category, untilUploadDate, fromUploadDate, untilEventDate, fromEventDate).stream().map(EventOutputDTO::from).toList();
  }

  @Override
  public void createAll(List<EventInputDTO> dtos) {

    // TODO: Validar que la peticion venga de un modulo-fuente registrado (SEGURIDAD)

    dtos.forEach(dto -> {

      // Get event source
      Source source = sourceRepository.findByExternalIds(dto.getSourceClientId(), dto.getSourceId());
      if (source == null)
        throw new NotFoundException("Source not found for SourceClientId: " + dto.getSourceClientId() + " and sourceId: " + dto.getSourceId());

      // Create and saver event
      eventRepository.save(Event.from(dto, source));

    });
  }

}
