package ar.edu.utn.frba.dds.domain.entities.colecciones;

import ar.edu.utn.frba.dds.domain.entities.colecciones.condiciones.Condicion;
import ar.edu.utn.frba.dds.domain.entities.fuente.Fuente;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;


@Getter
public class Coleccion {
  //-- Descriptivos
  private final String titulo;
  private final String descripcion;

  //-- Funcionales
  private final Fuente fuente;
  private Set<Hecho> hechos;
  private final CriterioPertenencia criterioDePertenencia;

  public Coleccion(String titulo, String descripcion, Fuente fuente) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.criterioDePertenencia = new CriterioPertenencia();
    this.fuente = fuente;
    this.calculateHechos();
  }

  /**
   Calcula en base a su criterio de pertenencia los hechos que pertenecen a la coleccion
   */
  public void calculateHechos() {
    Set<Hecho> hechosFuentes = this.fuente.getHechos();
    this.hechos = hechosFuentes.stream().filter(criterioDePertenencia::cumpleCondiciones).collect(Collectors.toSet());
  }

  /**
   Permite agregar condiciones al criterioDePertenencia manteniendo el encapsulamiento
   */
  public void addCondicion(Condicion condicion, Condicion... condiciones) {
    criterioDePertenencia.addCondicion(condicion, condiciones);
    this.calculateHechos();
  }

  //TODO: getHechos(filtros...) como sobrecarga que permita obtener hechos filtrados
}