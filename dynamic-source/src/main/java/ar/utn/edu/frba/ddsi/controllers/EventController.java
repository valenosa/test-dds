package ar.utn.edu.frba.ddsi.controllers;

import ar.utn.edu.frba.ddsi.models.dtos.input.EventCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.EventUpdateDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.services.IEventService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("events")
public class EventController {

  @Autowired
  private IEventService eventService;

  @GetMapping
  public List<EventOutputDTO> getEvents(@RequestParam(required = false) LocalDateTime lastUpdate ) {
    return eventService.getEvents(lastUpdate);
  }

  @PostMapping
  public EventOutputDTO submitEvent(@RequestBody EventCreationDTO eventDto) {
    return eventService.save(eventDto);
  }

  @PutMapping
  public EventOutputDTO updateEvent(@RequestBody EventUpdateDTO eventDto) {
    return eventService.update(eventDto);
  }

  @DeleteMapping("/{id}")
  public EventOutputDTO deleteEvent(@PathVariable Long id) {
    return eventService.deleteEvent(id);
  }
}
