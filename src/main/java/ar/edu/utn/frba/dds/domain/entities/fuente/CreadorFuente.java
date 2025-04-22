package ar.edu.utn.frba.dds.domain.entities.fuente;

import ar.edu.utn.frba.dds.domain.entities.fuente.estrategias.Fuente;
import ar.edu.utn.frba.dds.domain.entities.fuente.estrategias.FuenteEstatica;

public class CreadorFuente {
  public static Fuente fuenteEstatica(String rutaArchivoCsv) {
    return new FuenteEstatica(rutaArchivoCsv);
  }
}