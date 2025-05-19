package ar.utn.edu.frba.ddsi.models.entities.source.impl;

import ar.utn.edu.frba.ddsi.models.entities.event.Category;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.event.Origin;
import ar.utn.edu.frba.ddsi.models.entities.source.IImporter;
import com.opencsv.CSVReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class CsvImporter implements IImporter {

  public Set<Event> importEvents(String path) {
    Set<Event> events = new HashSet<>();

    try (
        CSVReader reader = new CSVReader(
            new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)
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
          System.out.println("Invalid Date in row: " + Arrays.toString(row));
        }

        Event event = new Event(
            title,
            description,
            category,
            latitude,
            longitude,
            eventDate,
            uploadDate,
            origin);

        events.add(event);
      }
    } catch (Exception e) {
      e.printStackTrace(); //TODO Cambiar esto por un método de logging más robusto
    }

    return events;
  }
}
