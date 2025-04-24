package ar.edu.utn.frba.dds.domain.entities.colecciones.condiciones;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import java.time.LocalDate;

public class CondicionEntreFechas extends Condicion {

  Condicion condicionDesde;
  Condicion condicionHasta;

  public CondicionEntreFechas(LocalDate desde, LocalDate hasta) {
    condicionDesde = new CondicionDesdeFecha(desde);
    condicionHasta = new CondicionHastaFecha(hasta);
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return condicionHasta.cumple(hecho) && condicionDesde.cumple(hecho);
  }
}
