package ar.utn.edu.frba.ddsi.controllers;

import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.services.IEventService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/source")
public class SourceController {

  @Autowired
  IEventService eventService;

  @GetMapping("/events")
  public List<EventOutputDTO> getEvents() {
    return eventService.getEvents();
  }

}
