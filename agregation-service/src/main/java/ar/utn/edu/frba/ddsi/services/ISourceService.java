package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.input.SourceInputDTO;

public interface ISourceService {
  void create(SourceInputDTO sourceInputDTO);
  //TODO: ver tema traer eventos desde la baseURL
}