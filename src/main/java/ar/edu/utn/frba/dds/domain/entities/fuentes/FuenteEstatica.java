package ar.edu.utn.frba.dds.domain.entities.fuentes;

import ar.edu.utn.frba.dds.domain.entities.Hecho;
import java.util.List;
import java.util.Set;

public class FuenteEstatica implements Fuente {

  @Override
  public Set<Hecho> importarHechos() {

    return Set.of();
  }

  @Override
  public Set<Hecho> obtenerTipoDeFuente() {
    return Set.of();
  }
}
