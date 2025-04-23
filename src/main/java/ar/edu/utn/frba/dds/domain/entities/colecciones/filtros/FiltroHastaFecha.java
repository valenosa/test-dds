package ar.edu.utn.frba.dds.domain.entities.colecciones.filtros;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import java.time.LocalDate;

public class FiltroHastaFecha implements Filtro {

  LocalDate hasta;

  public FiltroHastaFecha(LocalDate hasta) {
    this.hasta = hasta;
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return hecho.getFechaAcontecimiento().isBefore(hasta);
  }
}
