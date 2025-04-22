package ar.edu.utn.frba.dds.domain.entities.fuente.estrategias;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;

import java.util.Set;
import lombok.Getter;

@Getter
public abstract class Fuente {

  //TODO: Esta es la informacion se subira a la base de datos posteriormente.
  public Set<Hecho> hechosAsociados; // Podria guardarse simplemente un id (x ej el titulo) y despues traerlo de la BD

  protected abstract Set<Hecho> importHechos();
}