package ar.edu.utn.frba.dds.domain.entities.colecciones;

import ar.edu.utn.frba.dds.domain.entities.Hecho.Hecho;
import ar.edu.utn.frba.dds.domain.entities.excepciones.HechoEliminadoException;
import ar.edu.utn.frba.dds.domain.entities.importador.stretegies.ImportStrategy;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Coleccion {
  private String titulo;
  private String descripcion;
  @Getter
  private Set<Hecho> hechos;
  private List<ImportStrategy> fuentes;
  private CriterioPertenencia criterioDePertenencia;

  public Coleccion(String titulo, String descripcion) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.hechos = new HashSet<>();
  }

  public void addHecho(Hecho hecho) {
    if (hecho.isEliminado()) {
      throw new HechoEliminadoException();
    }
    hechos.add(hecho);
  }
}

