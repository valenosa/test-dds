package ar.edu.utn.frba.dds.domain.entities.fuente;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import java.util.Set;
import lombok.Getter;

public class Fuente {

  @Getter
  public Set<Hecho> hechos;
  private Importador importador;

  public Fuente(Importador importador) {
    importador = importador;
    hechos = importador.importHechos();
  }

}
