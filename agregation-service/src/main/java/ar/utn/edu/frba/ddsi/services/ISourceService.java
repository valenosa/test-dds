package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.input.SourceClientInputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.SourceClientOutputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.SourceOutputDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface ISourceService {

  SourceClientOutputDTO create(SourceClientInputDTO dto);

  void refreshSources(LocalDateTime lastUpdate);

  List<SourceClientOutputDTO> getAllClients();

  List<SourceOutputDTO> getAllSources();
}
