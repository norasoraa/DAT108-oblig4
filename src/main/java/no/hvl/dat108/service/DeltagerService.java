package no.hvl.dat108.service;

import java.util.List;

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

  public List<Deltager> getAlleDeltagere() {
    return deltagerRepo.findAll();
  }

  public Deltager saveDeltager(Deltager deltager) {
    return deltagerRepo.save(deltager);
  }

  public Deltager finnDeltager(String mobil) {
    return deltagerRepo.findByMobil(mobil);
  }

  /**
   * Checks if the number is already registered.
   * 
   * @param mobilnummer the number to check
   * @return {@code true} if the number already exists, {@code false} otherwise
   */
  public boolean eksistererNummer(String mobil) {
    return deltagerRepo.existsById(mobil);
  }

}
