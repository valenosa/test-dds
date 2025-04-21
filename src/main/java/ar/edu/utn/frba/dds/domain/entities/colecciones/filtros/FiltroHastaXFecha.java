package ar.edu.utn.frba.dds.domain.entities.colecciones.filtros;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;

import java.time.LocalDate;

public class FiltroHastaXFecha implements Filtro {

  LocalDate hasta;

  public FiltroHastaXFecha(LocalDate hasta) {
    this.hasta = hasta;
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return hecho.getFechaAcontecimiento().isBefore(hasta);
  }
}
