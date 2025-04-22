package ar.edu.utn.frba.dds.domain.entities.colecciones;

import ar.edu.utn.frba.dds.domain.entities.fuente.estrategias.Fuente;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class Coleccion {
  private final String titulo;
  private final String descripcion;
  private final List<Fuente> fuentesAsociadas;
  private final CriterioPertenencia criterioDePertenencia;

  public Coleccion(String titulo, String descripcion, Fuente... fuentes) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.criterioDePertenencia = new CriterioPertenencia();
    this.fuentesAsociadas = List.of(fuentes);
  }

  //--- Hechos pertenecientes
  private Set<Hecho> getHechosfromFuentes() {
    Set<Hecho> hechosCombinados = new HashSet<>();
    for (Fuente fuente : fuentesAsociadas) {
      hechosCombinados.addAll(fuente.importHechos());
    }
    return hechosCombinados;
  }

  private boolean pertenece(Hecho hecho) {
    return criterioDePertenencia.cumpleFiltros(hecho) && !hecho.isEliminado();
  }

  public Set<Hecho> getHechosPertenecientes() {
    Set<Hecho> hechosFuentes = getHechosfromFuentes();
    return hechosFuentes.stream().filter(this::pertenece).collect(HashSet::new, HashSet::add, HashSet::addAll);
  }
}


