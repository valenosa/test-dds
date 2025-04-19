package ar.edu.utn.frba.dds.domain.entities.colecciones.filtros;

import ar.edu.utn.frba.dds.domain.entities.Hecho.Hecho;
import java.time.LocalDate;

public class FiltroXEntreFechas implements FiltroHecho {

  LocalDate desde;
  LocalDate hasta;

  public FiltroXEntreFechas(LocalDate desde, LocalDate hasta) {
    this.desde = desde;
    this.hasta = hasta;
  }


  @Override
  public boolean cumple(Hecho hecho) {
    return hecho.getFechaAcontecimiento().isBefore(hasta) && hecho.getFechaAcontecimiento().isAfter(desde);
  }
}
