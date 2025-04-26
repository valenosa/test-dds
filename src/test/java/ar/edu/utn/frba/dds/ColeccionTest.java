package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.colecciones.Coleccion;
import ar.edu.utn.frba.dds.domain.entities.colecciones.condiciones.CondicionCategoria;
import ar.edu.utn.frba.dds.domain.entities.colecciones.condiciones.CondicionEntreFechas;
import ar.edu.utn.frba.dds.domain.entities.fuente.Fuente;
import ar.edu.utn.frba.dds.domain.entities.fuente.ImportadorCSV;
import ar.edu.utn.frba.dds.domain.entities.hecho.Categoria;
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
    unaFuente = new Fuente(new ImportadorCSV(pathCSV));
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

    //Agrego condicion entreFechas y recalculo
    unaColeccion.addCondicion(new CondicionEntreFechas(LocalDate.of(2000, 1, 1), LocalDate.of(2010, 1, 1)));

    //Ya tengo el los condiciones listos, me guardo los hechos de la colección
    hechosAsociados = unaColeccion.getHechos();

    Assertions.assertEquals(3, hechosAsociados.size());

    //Agrego condicion por categoria
    unaColeccion.addCondicion(new CondicionCategoria(new Categoria("Caída de aeronave")));
    hechosAsociados = unaColeccion.getHechos();

    Assertions.assertEquals(2, hechosAsociados.size());

  }

}