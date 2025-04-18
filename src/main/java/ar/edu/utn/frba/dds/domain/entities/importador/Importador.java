package ar.edu.utn.frba.dds.domain.entities.importador;

import ar.edu.utn.frba.dds.domain.entities.Hecho;
import ar.edu.utn.frba.dds.domain.entities.importador.stretegies.ImportStrategy;
import lombok.Setter;

import java.util.Set;

public class Importador {

  @Setter
  private ImportStrategy importStrategy;

  public Importador(ImportStrategy importStrategy) {
    this.importStrategy = importStrategy;
  }

  public Set<Hecho> importarHechos() {
    return this.importStrategy.importHechos();
  }
}
