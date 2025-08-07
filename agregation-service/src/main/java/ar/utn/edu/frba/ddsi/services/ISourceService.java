package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.input.source.SourceClientInputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.source.SourceInputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.source.SourceClientOutputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.source.SourceOutputDTO;
import java.util.List;

public interface ISourceService {

  SourceClientOutputDTO create(SourceClientInputDTO dto);

  SourceOutputDTO create(SourceInputDTO dto);

  List<SourceClientOutputDTO> getAllClients();

  List<SourceOutputDTO> getAllSources();
}
