package ar.edu.utn.frba.dds.domain.entities.fuente.estrategias;

import ar.edu.utn.frba.dds.domain.entities.BaseDeDatos;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class Fuente {

  public Set<String> nombresHechosAsociados;

  protected abstract Set<Hecho> importHechos();

  public Set<Hecho> getHechosAsociados() {
    return BaseDeDatos.obtenerHechos(nombresHechosAsociados);
  }

  protected Set<String> nombresHechos(Set<Hecho> hechosImportados) {
    return hechosImportados.stream().map(Hecho::getTitulo).collect(Collectors.toSet());
  }
}