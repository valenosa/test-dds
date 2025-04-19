package ar.edu.utn.frba.dds.domain.entities.colecciones;

import ar.edu.utn.frba.dds.domain.entities.Hecho.Hecho;
import ar.edu.utn.frba.dds.domain.entities.importador.Importador;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Coleccion {
  @Getter
  private String titulo;
  @Getter
  private String descripcion;
  @Getter
  private List<Importador> fuentes;
  private CriterioPertenencia criterioDePertenencia;

  public Coleccion(String titulo, String descripcion) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.hechos = new HashSet<>();
  }

//  public void addHecho(Hecho hecho) {
//
//    if (criterioDePertenencia.cumple(hecho) && !hecho.isEliminado()) {
//      hechos.add(hecho);
//    }
//
//    //TODO: ver que onda con esto, no se si es necesaria la exception
//    if (hecho.isEliminado()) {
//      throw new HechoEliminadoException();
//    }
//  }

  //--- Hechos pertenecientes

  //TODO: sseguro se cambia cuando la fuente tenga sus hechos guardados en mem
  private Set<Hecho> getHechosfromFuentes() {
    Set<Hecho> hechosCombinados = new HashSet<>();
    for (Importador fuente : fuentes) {
      hechosCombinados.addAll(fuente.importHechos());
    }
    return hechosCombinados;
  }

  private boolean pertenece(Hecho hecho) {
    return criterioDePertenencia.cumple(hecho) && !hecho.isEliminado();
  }

  public Set<Hecho> getHechosPertenecientes() {
    Set<Hecho> hechosFuentes = getHechosfromFuentes();
    return hechosFuentes.stream().filter(this::pertenece).collect(HashSet::new, HashSet::add, HashSet::addAll);
  }
}

