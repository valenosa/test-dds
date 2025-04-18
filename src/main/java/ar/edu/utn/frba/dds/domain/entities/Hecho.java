package ar.edu.utn.frba.dds.domain.entities;

import ar.edu.utn.frba.dds.domain.entities.multimedia.Multimedia;
import java.time.LocalDate;
import java.util.Set;

public class Hecho {
  private Integer id;
  private String titulo;
  private String descripcion;
  private String categoria;
  private Float latitud;
  private Float longitud;
  private LocalDate fechaAcontecimiento;
  private Multimedia multimedia;
  private LocalDate fechaCarga;
  private Set<Etiqueta> etiquetas;
  private boolean fueEliminado;

  public Hecho(String titulo, String descripcion, String categoria, Float latitud, Float longitud, LocalDate fechaAcontecimiento) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.latitud = latitud;
    this.longitud = longitud;
    this.fechaAcontecimiento = fechaAcontecimiento;
  }
}
