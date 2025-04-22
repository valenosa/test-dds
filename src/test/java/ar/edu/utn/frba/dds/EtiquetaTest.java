package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.Etiqueta;
import ar.edu.utn.frba.dds.domain.entities.fuente.CreadorFuente;
import ar.edu.utn.frba.dds.domain.entities.fuente.estrategias.Fuente;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EtiquetaTest {
  @Test
  @DisplayName("Etiquetas - Etiquetar al hecho titulado “Caída de aeronave impacta en Olavarría” como Olavarría y etiquetar como Grave")
  public void etiquetar() {

    String titulo = "Caída de aeronave impacta en Venado Tuerto, Santa Fe";
    LocalDate fechaAcontecimiento = LocalDate.of(2008, 8, 8);
    String descripcion =  "Grave caída de aeronave ocurrió en las inmediaciones de Venado Tuerto, Santa Fe. El incidente destruyó viviendas y dejó a familias evacuadas. Autoridades nacionales se han puesto a disposición para brindar asistencia";

    Hecho hecho1 = new Hecho(titulo, descripcion, "Caída de aeronave", -33.768051, -61.921032 , fechaAcontecimiento, LocalDate.now(), null);

    Etiqueta etiqueta1 = new Etiqueta("Olavarría");
    Etiqueta etiqueta2 = new Etiqueta("Grave");

    hecho1.agregarEtiqueta(etiqueta1);
    hecho1.agregarEtiqueta(etiqueta2);


    Assertions.assertTrue(hecho1.etiquetas.contains(etiqueta1));
    Assertions.assertTrue(hecho1.etiquetas.contains(etiqueta2));
    Assertions.assertEquals(2, hecho1.etiquetas.size());

  }
}

