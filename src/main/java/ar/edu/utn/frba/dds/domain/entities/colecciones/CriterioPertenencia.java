package ar.edu.utn.frba.dds.domain.entities.colecciones;

import ar.edu.utn.frba.dds.domain.entities.Hecho.Hecho;
import java.util.ArrayList;
import java.util.List;

public class CriterioPertenencia {

  List<FiltroHecho> filtrosAplicados;

  public CriterioPertenencia() {
    filtrosAplicados = new ArrayList<>();
  }

  public void addFiltros(FiltroHecho filtro, FiltroHecho... filtros) {
    filtrosAplicados.add(filtro);
    if (filtros != null) {
      filtrosAplicados.addAll(List.of(filtros));
    }
  }

  private boolean cumpleFiltros(Hecho hecho) {
    return filtrosAplicados.stream().allMatch(filtro -> filtro.cumple(hecho));
  }
}
