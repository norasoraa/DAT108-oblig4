package no.hvl.dat108.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.hvl.dat108.DeltagerRepo;
import no.hvl.dat108.model.Deltager;

/**
 * Class that contains a list of all participants as a list of {@link Deltager}.
 * Contains methods for extracting and modifying the list.
 */
@Service
public class DeltagerService {

  @Autowired
  private DeltagerRepo deltagerRepo;

  private static List<Deltager> alleDeltagere = Stream
      .of(new Deltager("Anne", "Panne", "23456789", "kvinne", "jdd3kedkWle", "jdd3kedkWle"),
          new Deltager("Arne", "Arnesen", "90123456", "mann", "kdwOke2a", "kdwOke2a"),
          new Deltager("Lars-Petter", "Helland", "12345679", "mann", "Kiwd9383fmf--e", "Kiwd9383fmf--e"),
          new Deltager("Per", "Viskelær", "34534534", "mann", "Egdu83-2dl3", "Egdu83-2dl3"),
          new Deltager("Xx-x", "Xxx", "12321378", "kvinne", "Poedk9-dwU9", "Poedk9-dwU9"))
      .collect(Collectors.toList());

  /**
   * Adds a {@link Deltager} to the list of participants if the number is not
   * already registered.
   * 
   * @param deltager The {@link Deltager} to add
   * @return {@code false} if the number already exists, {@code true} if the
   *         addition was successful
   */
  public boolean addDeltager(Deltager deltager) {
    if (eksistererNummer(deltager.getMobil())) {
      return false;
    }
    alleDeltagere.add(deltager);
    return true;
  }

  public Deltager saveDeltager(Deltager deltager) {
    return deltagerRepo.save(deltager);
  }

  /**
   * Checks if the number is already registered.
   * 
   * @param mobilnummer the number to check
   * @return {@code true} if the number already exists, {@code false} otherwise
   */
  public boolean eksistererNummer(String mobil) {
    for (Deltager deltager : alleDeltagere) {
      if (deltager.getMobil().equals(mobil)) {
        return true;
      }
    }
    return false;
  }

}
