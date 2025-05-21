package ar.utn.edu.frba.ddsi.controller;

import ar.utn.edu.frba.ddsi.models.dtos.input.DeletionRequestCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.DeletionRequestEvaluationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.DeletionRequestOutputDTO;
import ar.utn.edu.frba.ddsi.services.impl.DeletionRequestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deletion-requests")
public class DeletionRequestController {

  private final DeletionRequestService deletionRequestService;

  public DeletionRequestController(DeletionRequestService deletionRequestService) {
    this.deletionRequestService = deletionRequestService;
  }

  @PostMapping
  public DeletionRequestOutputDTO create(@RequestBody DeletionRequestCreationDTO dto) {
    return deletionRequestService.create(dto);
  }

  @PutMapping
  public DeletionRequestOutputDTO evaluate(@RequestBody DeletionRequestEvaluationDTO dto) {
    return deletionRequestService.evaluate(dto);
  }
}
