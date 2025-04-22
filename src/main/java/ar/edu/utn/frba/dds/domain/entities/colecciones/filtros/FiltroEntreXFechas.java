package ar.edu.utn.frba.dds.domain.entities.colecciones.filtros;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import java.time.LocalDate;

public class FiltroEntreXFechas implements Filtro {

  Filtro filtroDesde;
  Filtro filtroHasta;

  public FiltroEntreXFechas(LocalDate desde, LocalDate hasta) {
    filtroDesde = new FiltroDesdeXFecha(desde);
    filtroHasta = new FiltroHastaXFecha(hasta);
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return filtroHasta.cumple(hecho) && filtroDesde.cumple(hecho);
  }
}
