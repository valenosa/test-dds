package ar.edu.utn.frba.dds.domain.entities.source;

import ar.edu.utn.frba.dds.domain.entities.fact.Category;
import ar.edu.utn.frba.dds.domain.entities.fact.Fact;
import ar.edu.utn.frba.dds.domain.entities.fact.Origin;
import com.opencsv.CSVReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CSVImporter implements Importer {

  private final String filePath;

  public CSVImporter(String path) {
    this.filePath = path;
  }

  @Override
  public Set<Fact> importFacts() {

    Set<Fact> facts = new HashSet<>();

    try (
        CSVReader reader = new CSVReader(
            new InputStreamReader(new FileInputStream(this.filePath), StandardCharsets.UTF_8)
        )
    ) {

      String[] headers = reader.readNext();
      String[] row;

      while ((row = reader.readNext()) != null) {
        String title = row[0];
        String description = row[1];
        Category category = new Category(row[2]);
        Double latitude = Double.parseDouble(row[3]);
        Double longitude = Double.parseDouble(row[4]);
        LocalDate eventDate = null;
        LocalDate uploadDate = LocalDate.now();
        Origin origin = Origin.DATASET;

        try {
          eventDate = LocalDate.parse(row[5], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
          System.out.println("Fecha inválida para row: " + Arrays.toString(row));
        }


        Fact fact = new Fact(
            title,
            description,
            category,
            latitude,
            longitude,
            eventDate,
            uploadDate,
            origin);

        facts.add(fact);
      }
    } catch (Exception e) {
      e.printStackTrace(); //TODO Cambiar esto por un método de logging más robusto
    }

    return facts;
  }
}
