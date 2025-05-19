package models.dtos.input;

import lombok.Getter;

@Getter
public class SourceInputDTO {
  private String apiType; //Tipo de API, permite instanciar la clase correspondiente
  private String baseUrl;
  //Por ahora la apiKey se obtiene mediante un login. Debería pasar credenciales en este DTO?

}
