package ar.edu.utn.frba.dds.domain.entities.colecciones.filtros;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import java.time.LocalDate;

//TODO Borrarlo y usar la combinación de filtro desde y filtro hasta en vez de este filtro compuesto

public class FiltroXEntreFechas implements FiltroHecho {

  FiltroHecho filtroDesde;
  FiltroHecho filtroHasta;

  public FiltroXEntreFechas(LocalDate desde, LocalDate hasta) {
    filtroDesde = new FiltroDesdeXFecha(desde);
    filtroHasta = new FiltroHastaXFecha(hasta);
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return filtroHasta.cumple(hecho) && filtroDesde.cumple(hecho);
  }
}
