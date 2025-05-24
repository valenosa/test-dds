package ar.utn.edu.frba.ddsi.controller;

import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.services.impl.EventService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/events")
public class EventController {

  private final EventService eventService;

  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @GetMapping
  public List<EventOutputDTO> getEvents(@RequestParam(required = false) LocalDateTime lastUpdate ) {
    return eventService.getEvents(lastUpdate);
  }

  @DeleteMapping("/{eventId}")
  public EventOutputDTO deleteEvent(@PathVariable("eventId") Long eventId) {
    return eventService.deleteEvent(eventId);
  }
}