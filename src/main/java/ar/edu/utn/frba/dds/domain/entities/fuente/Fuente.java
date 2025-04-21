package ar.edu.utn.frba.dds.domain.entities.fuente;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import ar.edu.utn.frba.dds.domain.entities.fuente.estrategias.EstrategiaDeImportacion;
import lombok.Setter;

import java.util.Set;

public class Fuente {

  @Setter
  private EstrategiaDeImportacion estrategiaDeImportacion;

  public Fuente(EstrategiaDeImportacion estrategiaDeImportacion) {
    this.estrategiaDeImportacion = estrategiaDeImportacion;
  }

  public Set<Hecho> importHechos() {
    return this.estrategiaDeImportacion.importHechos();
  }
}

// Fuente.crearFuenteEstatica("path");
