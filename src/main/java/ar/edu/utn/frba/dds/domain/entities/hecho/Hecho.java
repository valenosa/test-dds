package ar.edu.utn.frba.dds.domain.entities.hecho;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Getter
public class Hecho {

  //-- Descripcion
  @Getter
  private final String titulo;
  private final String descripcion;
  private final Categoria categoria;
  //-- Ubicacion
  private final Double latitud;
  private final Double longitud;
  //-- Fechas
  private final LocalDate fechaAcontecimiento;
  private final LocalDate fechaCarga;
  //-- Extras
  @Setter
  private boolean eliminado;
  private final Origen origen;
  public Set<Etiqueta> etiquetas; //lo pongo public para usarlo en test

  public Hecho(String titulo,
               String descripcion,
               Categoria categoria,
               Double latitud,
               Double longitud,
               LocalDate fechaAcontecimiento,
               LocalDate fechaCarga,
               Origen origen) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.latitud = latitud;
    this.longitud = longitud;
    this.fechaAcontecimiento = fechaAcontecimiento;
    this.fechaCarga = fechaCarga;
    this.origen = origen;
    this.eliminado = false;
    etiquetas = new HashSet<>();
  }

  public void agregarEtiqueta(Etiqueta etiqueta, Etiqueta ... etiquetasAgregadas) {
    etiquetas.add(etiqueta);
    if (etiquetasAgregadas != null) {
      etiquetas.addAll(Set.of(etiquetasAgregadas));
    }
  }

}