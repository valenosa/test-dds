package ar.utn.edu.frba.ddsi.models.dtos.input;

import lombok.Data;

@Data
public class DeletionRequestInputDTO {
  Long eventId;
  String argument;
  String applicantName; //TODO: Esto deberia ser un usuario
}
