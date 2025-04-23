package ar.edu.utn.frba.dds.domain.entities.colecciones;

import ar.edu.utn.frba.dds.domain.entities.colecciones.filtros.Filtro;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import java.util.ArrayList;
import java.util.List;

public class CriterioPertenencia {

  List<Filtro> filtrosAplicados;

  public CriterioPertenencia() {
    filtrosAplicados = new ArrayList<>();
  }

  public void addFiltros(Filtro filtro, Filtro... filtros) {
    filtrosAplicados.add(filtro);
    if (filtros != null) {
      filtrosAplicados.addAll(List.of(filtros));
    }
  }

  public boolean cumpleFiltros(Hecho hecho) {
    return filtrosAplicados.stream().allMatch(filtro -> filtro.cumple(hecho));
  }
}
