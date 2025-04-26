package ar.edu.utn.frba.dds.domain.entities.fuente;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import java.util.Set;

public interface Importador {
  public Set<Hecho>  importHechos();
}
