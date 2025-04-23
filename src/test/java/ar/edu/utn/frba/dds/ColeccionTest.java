package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.colecciones.Coleccion;
import ar.edu.utn.frba.dds.domain.entities.colecciones.CriterioPertenencia;
import ar.edu.utn.frba.dds.domain.entities.colecciones.filtros.FiltroCategoria;
import ar.edu.utn.frba.dds.domain.entities.colecciones.filtros.FiltroEntreFechas;
import ar.edu.utn.frba.dds.domain.entities.fuente.CreadorFuente;
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
    unaFuente = CreadorFuente.fuenteEstatica(pathCSV);
    unaColeccion = new Coleccion("Colección prueba", "Esto es una prueba", unaFuente);
  }

  @Test
  @DisplayName("Se pueden obtener hechos a partir de una colección")
  void testValidacionDeObtencionDeHechos() {
    Set<Hecho> hechosAsociados = unaColeccion.getHechosPertenecientes();

    Assertions.assertEquals(5, hechosAsociados.size());
  }

  @Test
  @DisplayName("Se aplicar Criterios de pertenencia")
  void testCriteriosDePertenencia() {
    //Obtengo criterio de la coleccion creada
    CriterioPertenencia criterioDeCol = unaColeccion.getCriterioDePertenencia();

    //Creo un set de hechos asociados
    Set<Hecho> hechosAsociados;

    //Agrego filtro entreFechas y recalculo
    criterioDeCol.addFiltros(new FiltroEntreFechas(LocalDate.of(2000, 1, 1), LocalDate.of(2010, 1, 1)));

    //Ya tengo el los filtros listos, me guardo los hechos de la coleccion
    hechosAsociados = unaColeccion.getHechosPertenecientes();

    Assertions.assertEquals(3, hechosAsociados.size());

    //Agrego filtro por categoria
    criterioDeCol.addFiltros(new FiltroCategoria("Caída de aeronave"));
    hechosAsociados = unaColeccion.getHechosPertenecientes();

    Assertions.assertEquals(2, hechosAsociados.size());

  }

}