package ar.utn.edu.frba.ddsi.controllers;

import ar.utn.edu.frba.ddsi.models.dtos.input.DeletionRequestCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.DeletionRequestEvaluationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.DeletionRequestOutputDTO;
import ar.utn.edu.frba.ddsi.services.impl.DeletionRequestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submissions")
public class DeletionRequestController {

  private final DeletionRequestService deletionRequestService;

  public DeletionRequestController(DeletionRequestService deletionRequestService) {
    this.deletionRequestService = deletionRequestService;
  }

  @PostMapping("/api/submissions")
  public DeletionRequestOutputDTO create(@RequestBody DeletionRequestCreationDTO dto) {
    return deletionRequestService.create(dto);
  }

  @PutMapping("/api/submissions")
  public DeletionRequestOutputDTO evaluate(@RequestBody DeletionRequestEvaluationDTO dto) {
    return deletionRequestService.evaluate(dto);
  }
}