package ar.utn.edu.frba.ddsi.controllers;

import ar.utn.edu.frba.ddsi.models.dtos.input.SourceInputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.SourceOutputDTO;
import ar.utn.edu.frba.ddsi.services.impl.SourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/source")
public class SourceController {

  private final SourceService sourceService;

  public SourceController(SourceService sourceService) {
    this.sourceService = sourceService;
  }

  @PostMapping
  public SourceOutputDTO createSource(@RequestBody SourceInputDTO dto) {
    return sourceService.create(dto);
  }

  @GetMapping
  public List<Long> getSources() {
    return sourceService.getSources();
  }

  @GetMapping("/events")
  public List<EventOutputDTO> getEvents() {
    return sourceService.getEvents();
  }

  @GetMapping("/{sourceId}/events")
  public List<EventOutputDTO> getSourceEvents(@PathVariable Long sourceId) {
    return sourceService.findEventsBySource(sourceId);
  }
}
