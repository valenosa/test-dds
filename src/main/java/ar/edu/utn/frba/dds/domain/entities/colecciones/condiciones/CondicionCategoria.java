package ar.edu.utn.frba.dds.domain.entities.colecciones.condiciones;

import ar.edu.utn.frba.dds.domain.entities.hecho.Categoria;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;

public class CondicionCategoria extends Condicion {
  Categoria categoria;

  public CondicionCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  // TODO Creé una clase Categoría para reemplazar la string, pero solo contiene una string y me veo obligado a comparar las strings en vez de las clases? No tiene sentido de ser...
  @Override
  public boolean cumple(Hecho hecho) {
    String categoriaCondicion = categoria.getNombre();
    String categoriaHecho = hecho.getCategoria().getNombre();

    return categoriaCondicion.equals(categoriaHecho);
  }

}
