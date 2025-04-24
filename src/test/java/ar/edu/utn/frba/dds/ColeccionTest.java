package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.colecciones.Coleccion;
import ar.edu.utn.frba.dds.domain.entities.colecciones.filtros.FiltroCategoria;
import ar.edu.utn.frba.dds.domain.entities.colecciones.filtros.FiltroEntreFechas;
import ar.edu.utn.frba.dds.domain.entities.fuente.estrategias.Fuente;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColeccionTest {

  Coleccion unaColeccion;
  Fuente unaFuente;


  @BeforeEach
  public void setUp() {
    String pathCSV = "./src/test/java/ar/edu/utn/frba/dds/resources/CSV/sample_CSVtest_2.csv";
    unaFuente = new Fuente(pathCSV);
    unaColeccion = new Coleccion("Colección prueba", "Esto es una prueba", unaFuente);
  }

  @Test
  @DisplayName("Se pueden obtener hechos a partir de una colección")
  void testValidacionDeObtencionDeHechos() {
    Set<Hecho> hechos = unaColeccion.getHechos();

    Assertions.assertEquals(5, hechos.size());
  }

  @Test
  @DisplayName("Se aplicar Criterios de pertenencia")
  void testCriteriosDePertenencia() {
    //Creo un set de hechos asociados
    Set<Hecho> hechosAsociados;

    //Agrego filtro entreFechas y recalculo
    unaColeccion.addFiltros(new FiltroEntreFechas(LocalDate.of(2000, 1, 1), LocalDate.of(2010, 1, 1)));

    //Ya tengo el los filtros listos, me guardo los hechos de la colección
    hechosAsociados = unaColeccion.getHechos();

    Assertions.assertEquals(3, hechosAsociados.size());

    //Agrego filtro por categoria
    unaColeccion.addFiltros(new FiltroCategoria("Caída de aeronave"));
    hechosAsociados = unaColeccion.getHechos();

    Assertions.assertEquals(2, hechosAsociados.size());

  }

}