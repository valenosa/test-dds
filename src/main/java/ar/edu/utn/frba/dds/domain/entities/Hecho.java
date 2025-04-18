package ar.edu.utn.frba.dds.domain.entities;

import ar.edu.utn.frba.dds.domain.entities.multimedia.Multimedia;
import java.time.LocalDate;
import java.util.Set;
//import javafx.util.Pair;


public class Hecho {
  private Integer id;
  private String titulo;
  private String descripcion;
  private String categoria;
  private Double latitud;
  private Double longitud;
  //Pair <Double, Double> ubicacion = new Pair <> (latitud, longitud);
  private LocalDate fechaAcontecimiento;
  private Multimedia multimedia;
  private LocalDate fechaCarga;
  private Set<Etiqueta> etiquetas;
  private boolean fueEliminado;

  public Hecho(String titulo, String descripcion, String categoria, Double latitud, Double longitud, LocalDate fechaAcontecimiento, LocalDate fechaCarga) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.latitud = latitud;
    this.longitud = longitud;
    this.fechaAcontecimiento = fechaAcontecimiento;
    this.fechaCarga = fechaCarga;
  }
}
