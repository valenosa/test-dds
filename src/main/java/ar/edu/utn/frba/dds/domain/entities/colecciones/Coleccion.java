package ar.edu.utn.frba.dds.domain.entities.colecciones;

import ar.edu.utn.frba.dds.domain.entities.Hecho;
import ar.edu.utn.frba.dds.domain.entities.fuentes.Fuente;
import java.util.List;
import java.util.Set;

public class Coleccion {
  private String titulo;
  private String descripcion;
  private Set<Hecho> hechos;
  private List<Fuente> fuentes;
  private CriterioPertenencia criterioDePertenencia;

}
