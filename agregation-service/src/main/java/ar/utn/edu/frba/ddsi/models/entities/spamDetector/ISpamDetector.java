package ar.utn.edu.frba.ddsi.models.entities.spamDetector;

public interface ISpamDetector {
  boolean isSpam(long eventId, String argument);
}
