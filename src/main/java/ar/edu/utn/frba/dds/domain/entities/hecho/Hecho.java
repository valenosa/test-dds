package ar.edu.utn.frba.dds.domain.entities.hecho;

import ar.edu.utn.frba.dds.domain.entities.Etiqueta;
import ar.edu.utn.frba.dds.domain.entities.multimedia.Multimedia;
import java.util.Collections;
import java.util.HashSet;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


@Getter
public class Hecho {

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
  public Set<Etiqueta> etiquetas; //lo pongo public para usarlo en test
  private Origen origen;

  public Hecho(String titulo, String descripcion, String categoria, Double latitud, Double longitud, LocalDate fechaAcontecimiento, LocalDate fechaCarga, Origen origen) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.latitud = latitud;
    this.longitud = longitud;
    this.fechaAcontecimiento = fechaAcontecimiento;
    this.fechaCarga = fechaCarga;
    this.origen = origen;
    etiquetas = new HashSet<>();
  }

  public void agregarEtiqueta(Etiqueta etiqueta) {
    etiquetas.add(etiqueta);
  }

}