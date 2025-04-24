package ar.edu.utn.frba.dds.domain.entities.colecciones.condiciones;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import java.time.LocalDate;

public class CondicionHastaFecha extends Condicion {

  LocalDate hasta;

  public CondicionHastaFecha(LocalDate hasta) {
    this.hasta = hasta;
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return hecho.getFechaAcontecimiento().isBefore(hasta);
  }
}
