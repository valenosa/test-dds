package ar.edu.utn.frba.dds.domain.entities.colecciones.condiciones;

import ar.edu.utn.frba.dds.domain.entities.hecho.Categoria;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;

public class CondicionCategoria extends Condicion {
  Categoria categoria;

  public CondicionCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  @Override
  public boolean cumple(Hecho hecho) {
    String categoriaCondicion = categoria.getNombre();
    String categoriaHecho = hecho.getCategoria().getNombre();

    return categoriaCondicion.equals(categoriaHecho);
  }

  // TODO: Se compara la cotegoria a partir del String, es correcto?

}
