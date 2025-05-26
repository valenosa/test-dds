package ar.utn.edu.frba.ddsi.models.entities.source;

import ar.utn.edu.frba.ddsi.models.dtos.input.SourceInputDTO;
import ar.utn.edu.frba.ddsi.models.entities.source.impl.Source;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SourceFactory {

  private final Map<String, IImporter> importers;

  public SourceFactory(List<IImporter> importerList) {
    this.importers = importerList.stream()
        .collect(Collectors.toMap(IImporter::getType, i -> i));
  }

  public Source createFrom(SourceInputDTO dto) {
    IImporter importer = importers.get(dto.getImportStrategy());
    return new Source(dto.getFileName(), importer);
  }
}
