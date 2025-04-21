package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.colecciones.Coleccion;
import ar.edu.utn.frba.dds.domain.entities.colecciones.CriterioPertenencia;
import ar.edu.utn.frba.dds.domain.entities.colecciones.filtros.FiltroXCategoria;
import ar.edu.utn.frba.dds.domain.entities.colecciones.filtros.FiltroXEntreFechas;
import ar.edu.utn.frba.dds.domain.entities.fuente.Fuente;
import ar.edu.utn.frba.dds.domain.entities.fuente.estrategias.EstrategiaDeImportacion;
import ar.edu.utn.frba.dds.domain.entities.fuente.estrategias.EstrategiaDeImportacionEstatica;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColeccionTest {

  Fuente unaFuente;
  Coleccion unaColeccion;
  EstrategiaDeImportacion unaEstrategiaDeImportacion;


  @BeforeEach
  public void setUp() {
    String pathCSV = "./src/test/java/ar/edu/utn/frba/dds/resources/CSV/sample_CSVtest_2.csv";
    unaEstrategiaDeImportacion = new EstrategiaDeImportacionEstatica(pathCSV);
    unaFuente = new Fuente(unaEstrategiaDeImportacion);
    unaColeccion = new Coleccion("Colección prueba", "Esto es una prueba", unaFuente);
  }

  @Test
  @DisplayName("Se pueden obtener hechos a partir de una colección")
  void testValidacionDeObtencionDeHechos() {
    Set<Hecho> hechosAsociados = unaColeccion.getHechosPertenecientes();

    Set<Hecho> hechosEsperados = unaFuente.importHechos();
    Assertions.assertEquals(5, hechosAsociados.size());
  }

  @Test
  @DisplayName("Se aplicar Criterios de pertenencia")
  void testCriteriosDePertenencia() {
    CriterioPertenencia criterioDeCol = unaColeccion.getCriterioDePertenencia();
    Set<Hecho> hechosAsociados;

    //Agrego filtro entreFechas y recalculo
    criterioDeCol.addFiltros(new FiltroXEntreFechas(LocalDate.of(2000, 1, 1), LocalDate.of(2010, 1, 1)));
    hechosAsociados = unaColeccion.getHechosPertenecientes();

    Assertions.assertEquals(3, hechosAsociados.size());

    //Agrego filtro por categoria
    criterioDeCol.addFiltros(new FiltroXCategoria("Caída de aeronave"));
    hechosAsociados = unaColeccion.getHechosPertenecientes();

    Assertions.assertEquals(2, hechosAsociados.size());

  }

}