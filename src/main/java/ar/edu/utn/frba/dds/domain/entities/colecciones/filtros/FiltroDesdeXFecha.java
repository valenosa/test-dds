package ar.edu.utn.frba.dds.domain.entities.colecciones.filtros;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;

import java.time.LocalDate;

public class FiltroDesdeXFecha implements Filtro {

  LocalDate desde;

  public FiltroDesdeXFecha(LocalDate desde) {
    this.desde = desde;
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return hecho.getFechaAcontecimiento().isAfter(desde);
  }
}