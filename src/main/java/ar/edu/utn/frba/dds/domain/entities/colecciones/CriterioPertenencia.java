package ar.edu.utn.frba.dds.domain.entities.colecciones;

import ar.edu.utn.frba.dds.domain.entities.colecciones.condiciones.Condicion;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import java.util.ArrayList;
import java.util.List;

public class CriterioPertenencia {

  List<Condicion> condiciones;

  public CriterioPertenencia() {
    condiciones = new ArrayList<>();
  }

  public void addCondicion(Condicion condicion, Condicion... condiciones) {
    this.condiciones.add(condicion);
    if (condiciones != null) {
      this.condiciones.addAll(List.of(condiciones));
    }
  }

  public boolean cumpleCondiciones(Hecho hecho) {
    return condiciones.stream().allMatch(condicion -> condicion.cumple(hecho));
  }
}
