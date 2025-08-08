package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.input.SourceInputDTO;

public interface ISourceService {
  void create(SourceInputDTO dto);

  void getAllEventsBySourceId(Long id);
}
