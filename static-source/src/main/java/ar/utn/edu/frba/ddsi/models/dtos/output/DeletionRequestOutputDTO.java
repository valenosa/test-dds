package ar.utn.edu.frba.ddsi.models.dtos.output;

import ar.utn.edu.frba.ddsi.models.entities.request.DeletionRequest;
import lombok.Data;

@Data
public class DeletionRequestOutputDTO {

  public static DeletionRequestOutputDTO from(DeletionRequest deletionRequest){
    new DeletionRequestOutputDTO();

  }
  //TODO: Completar que info necesita
}
