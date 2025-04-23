package ar.edu.utn.frba.dds.domain.entities.colecciones.filtros;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import java.time.LocalDate;

public class FiltroEntreFechas implements Filtro {

  Filtro filtroDesde;
  Filtro filtroHasta;

  public FiltroEntreFechas(LocalDate desde, LocalDate hasta) {
    filtroDesde = new FiltroDesdeFecha(desde);
    filtroHasta = new FiltroHastaFecha(hasta);
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return filtroHasta.cumple(hecho) && filtroDesde.cumple(hecho);
  }
}
