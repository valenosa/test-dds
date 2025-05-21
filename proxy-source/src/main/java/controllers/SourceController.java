package controllers;

import models.dtos.output.EventOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.impl.SourceService;

import java.util.List;

@RestController
@RequestMapping("/api/source")
public class SourceController {

  @Autowired
  private SourceService sourceService;

  @GetMapping("/events")
  public List<EventOutputDTO> getEvents() {
    return sourceService.getEvents();
  }

  @PostMapping("/meta-mapa")
  public void create() {
    // TODO Crear fuente proxy de tipo metaMapa
  }

  //TODO getEventsByAPI?
}
