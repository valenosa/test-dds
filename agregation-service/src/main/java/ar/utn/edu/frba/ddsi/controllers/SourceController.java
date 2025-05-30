package ar.utn.edu.frba.ddsi.controllers;

import ar.utn.edu.frba.ddsi.models.dtos.input.SourceClientDTO;
import ar.utn.edu.frba.ddsi.services.impl.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sources")
public class SourceController {

  @Autowired
  SourceService sourceService;


  @PostMapping("/clients")
  public SourceClientDTO createSource(@RequestBody SourceClientDTO sourceClient) {
    return sourceService.create(sourceClient);
  }

  @GetMapping("/clients")
  public List<SourceClientDTO> getAllClients() {
    return sourceService.getAllClients();
  }
}
