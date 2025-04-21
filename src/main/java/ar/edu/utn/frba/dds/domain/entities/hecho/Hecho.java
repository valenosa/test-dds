package ar.edu.utn.frba.dds.domain.entities.hecho;

import ar.edu.utn.frba.dds.domain.entities.Etiqueta;
import ar.edu.utn.frba.dds.domain.entities.multimedia.Multimedia;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


@Getter
public class Hecho {

  private static int contadorIds = 1; //Variable estatica para el contador de ids. Estará momentaneamente hasta que trabajemos con bbdd.

  private Integer id;
  //-- Descripcion
  private String titulo;
  private String descripcion;
  private String categoria;
  //-- Ubicacion
  private Double latitud;
  private Double longitud;
  //-- Fechas
  private LocalDate fechaAcontecimiento;
  private LocalDate fechaCarga;
  //-- Extras
  @Setter
  private boolean eliminado;
  private Multimedia multimedia;
  private Set<Etiqueta> etiquetas;
  private Origen origen;

  public Hecho(String titulo, String descripcion, String categoria, Double latitud, Double longitud, LocalDate fechaAcontecimiento, LocalDate fechaCarga, Origen origen) {
    this.id = Hecho.contadorIds++;
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.latitud = latitud;
    this.longitud = longitud;
    this.fechaAcontecimiento = fechaAcontecimiento;
    this.fechaCarga = fechaCarga;
    this.origen = origen;
  }
}