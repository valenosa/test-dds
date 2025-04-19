package ar.edu.utn.frba.dds.domain.entities;

import ar.edu.utn.frba.dds.domain.entities.multimedia.Multimedia;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

public class Hecho {

  private static int contadorIds = 1; //Variable estatica para el contador de ids. Estará momentaneamente hasta que trabajemos con bbdd.

  private Integer id;
  private String titulo;
  private String descripcion;
  private String categoria;
  private Double latitud;
  private Double longitud;
  private LocalDate fechaAcontecimiento;
  private Multimedia multimedia;
  private LocalDate fechaCarga;
  private Set<Etiqueta> etiquetas;

  @Setter
  @Getter
  private boolean eliminado;

  public Hecho(String titulo, String descripcion, String categoria, Double latitud, Double longitud, LocalDate fechaAcontecimiento, LocalDate fechaCarga) {
    this.id = Hecho.contadorIds++;
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.latitud = latitud;
    this.longitud = longitud;
    this.fechaAcontecimiento = fechaAcontecimiento;
    this.fechaCarga = fechaCarga;
  }
}

