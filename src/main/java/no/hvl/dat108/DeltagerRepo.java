package no.hvl.dat108;

import org.springframework.data.jpa.repository.JpaRepository;

import no.hvl.dat108.model.Deltager;

public interface DeltagerRepo extends JpaRepository<Deltager, String> {

  Deltager findByMobil(String mobil); 
}
