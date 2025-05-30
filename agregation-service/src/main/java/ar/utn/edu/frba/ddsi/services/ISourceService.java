package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.input.SourceClientDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface ISourceService {
  SourceClientDTO create(SourceClientDTO sourceClient);

  void refreshSources(LocalDateTime lastUpdate);
}
