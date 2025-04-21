package ar.edu.utn.frba.dds.domain.entities.colecciones.filtros;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;

//TODO Cambiar el nombre de esta interfaz a "Filtro"
public interface Filtro {

  boolean cumple(Hecho hecho);
}
