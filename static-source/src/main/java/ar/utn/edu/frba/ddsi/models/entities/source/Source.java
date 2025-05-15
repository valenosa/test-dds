package ar.utn.edu.frba.ddsi.models.entities.source;

import ar.utn.edu.frba.ddsi.models.dtos.input.SourceInputDTO;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

public class Source {

  @Getter
  @Setter
  private Long id;

  @Getter
  public Set<Long> events;
  private final String path;

  public static Source from(SourceInputDTO dto) {
    return new Source(dto.getPath());
  }

  public Source(String path) {
    this.path = path;
    this.update();
  }

  public void update() {
    //this.events = CsvImporter.importEvents(this.path);
  }
}
