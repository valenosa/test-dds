package ar.utn.edu.frba.ddsi.services;

public interface SpamDetector {
  boolean isSpam(long eventId, String argument);
}
