package ar.utn.edu.frba.ddsi.models.dtos.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SourceInputDTO {
  String path;
  @JsonProperty("import_strategy")
  String importStrategy;
}