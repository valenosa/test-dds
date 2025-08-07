package ar.utn.edu.frba.ddsi.models.entities.source;

import ar.utn.edu.frba.ddsi.models.entities.event.Origin;
import ar.utn.edu.frba.ddsi.models.entities.source.apis.impl.NaturalDisastersAPI;
import ar.utn.edu.frba.ddsi.models.entities.source.impl.Source;
import org.springframework.stereotype.Component;

@Component
public class SourceFactory {

  private final NaturalDisastersAPI naturalDisastersAPI;

  public SourceFactory(NaturalDisastersAPI naturalDisastersAPI) {
    this.naturalDisastersAPI = naturalDisastersAPI;
  }

  public Source naturalDisaster() {
    return new Source(naturalDisastersAPI, Origin.PROXY);
  }

  public Source metaMapa(String baseUrl) {
    //TODO Devolver fuente metaMapa
    return null;
  }
}
