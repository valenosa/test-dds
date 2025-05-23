package ar.utn.edu.frba.ddsi.models.entities.source;

import ar.utn.edu.frba.ddsi.models.entities.source.apis.IAPI;
import ar.utn.edu.frba.ddsi.models.entities.source.apis.impl.NaturalDisastersAPI;
import ar.utn.edu.frba.ddsi.models.entities.source.impl.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SourceFactory {

  private final NaturalDisastersAPI naturalDisastersAPI;

  public SourceFactory(NaturalDisastersAPI naturalDisastersAPI) {
    this.naturalDisastersAPI = naturalDisastersAPI;
  }

  public Source naturalDisaster() {
    return new Source(naturalDisastersAPI);
  }

  public Source metaMapa(String baseUrl) {
    //TODO Devolver fuente metaMapa
    return null;
  }
}
