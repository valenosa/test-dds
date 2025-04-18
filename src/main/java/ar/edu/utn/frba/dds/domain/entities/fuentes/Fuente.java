package ar.edu.utn.frba.dds.domain.entities.fuentes;

import ar.edu.utn.frba.dds.domain.entities.Hecho;
import java.util.List;
import java.util.Set;

public interface Fuente {
  Set<Hecho> importarHechos();

  Set<Hecho> obtenerTipoDeFuente();
}
