package ar.edu.utn.frba.dds.domain.entities.colecciones;

import ar.edu.utn.frba.dds.domain.entities.fuente.estrategias.Fuente;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class Coleccion {
  //-- Descripcion
  private final String titulo;
  private final String descripcion;
  //-- Funcionales
  private final List<Fuente> fuentesAsociadas;
  private final CriterioPertenencia criterioDePertenencia;

  public Coleccion(String titulo, String descripcion, Fuente... fuentes) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.criterioDePertenencia = new CriterioPertenencia();
    this.fuentesAsociadas = List.of(fuentes);
  }

  //--- Hechos pertenecientes
  private Set<Hecho> getHechosFromFuentes() {
    Set<Hecho> hechosCombinados = new HashSet<>();
    for (Fuente fuente : fuentesAsociadas) {
      hechosCombinados.addAll(fuente.getHechosAsociados());
    }

    return hechosCombinados;
  }

  private boolean pertenece(Hecho hecho) {
    return criterioDePertenencia.cumpleFiltros(hecho);
  }

  public Set<Hecho> getHechosPertenecientes() {
    Set<Hecho> hechosFuentes = getHechosFromFuentes();
    return hechosFuentes.stream().filter(this::pertenece).collect(Collectors.toSet());
  }
  /*
  !!No se que tan bueno es calcular los hechos pertenecientes cada vez que un usuario los pide
   pero si en un futuro las fuentes son dinamicas de alguna forma las colecciones deben actualizarce
   */
}