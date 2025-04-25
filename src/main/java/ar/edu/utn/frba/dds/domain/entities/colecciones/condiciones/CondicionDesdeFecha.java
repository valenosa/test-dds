package ar.edu.utn.frba.dds.domain.entities.colecciones.condiciones;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import java.time.LocalDate;

public class CondicionDesdeFecha extends Condicion {

  LocalDate desde;

  public CondicionDesdeFecha(LocalDate desde) {
    this.desde = desde;
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return hecho.getFechaAcontecimiento().isAfter(desde);
  }
}