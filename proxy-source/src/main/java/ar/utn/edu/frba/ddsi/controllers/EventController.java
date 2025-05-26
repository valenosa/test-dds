package ar.utn.edu.frba.ddsi.controllers;

import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.utn.edu.frba.ddsi.services.impl.EventService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

  @Autowired
  private EventService eventService;

  @GetMapping
  public List<EventOutputDTO> getEvents(@RequestBody(required = false) LocalDateTime lastUpdate) { //TODO LocalDateTime -> ZonedDateTime
    return eventService.getEvents(lastUpdate);
  }

  //TODO Pasar a SourceController
  @PostMapping("/meta-mapa")
  public void create() {
    //TODO Crear fuente proxy de tipo metaMapa
  }

  //TODO getEventsByAPI?
}
