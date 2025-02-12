package no.hvl.dat108.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

/**
 * Class that represents a {@link Deltager}. Contains methods for extracting the
 * necessary information about the participant.
 */
@Entity
@Table(schema = "deltager_db", name = "deltager")
public class Deltager implements TestableDeltager {
  
  @Id
  @Column(name = "mobil")
  @Pattern(regexp = "[0-9]{8}", message = "Mobilnummer må være eksakt 8 sifre.")
  private String mobil;

  @Column(name = "fornavn")
  @Pattern(regexp = "[A-ZÆØÅ][a-zæøåA-ZÆØÅ\\- ]{1,19}", message = "Fornavn må inneholde minst 2 tegn og ikke mer enn 20. Første tegn skal være en stor bokstav. Kan bare inneholde bokstaver, bindestrek og mellomrom.")
  private String fornavn;

  @Column(name = "etternavn")
  @Pattern(regexp = "[A-ZÆØÅ][a-zæøåA-ZÆØÅ\\-]{1,19}", message = "Etternavn må inneholde minst 2 tegn og ikke mer enn 20. Første tegn skal være en stor bokstav. Kan bare inneholde bokstaver og bindestrek.")
  private String etternavn;

  @Column(name = "kjonn")
  private String kjonn;

  @Column(name = "salt")
  private String salt;

  @Column(name = "hash")
  private String hash;

  /** Constructs a new {@link Deltager} with the given arguments. */
  public Deltager(String fornavn, String etternavn, String mobil, String kjonn, String hash,
      String salt) {
    this.fornavn = fornavn;
    this.etternavn = etternavn;
    this.mobil = mobil;
    this.kjonn = kjonn;
    this.salt = salt;
    this.hash = hash;
  }

  public Deltager() {
  }

  public String getFornavn() {
    return fornavn;
  }

  public String getEtternavn() {
    return etternavn;
  }

  public String getMobil() {
    return mobil;
  }

  public String getKjonn() {
    return kjonn;
  }

  public String getHash() {
    return hash;
  }

  public String getSalt() {
    return salt;
  }

  public String getFulltNavn() {
    return fornavn + " " + etternavn;
  }

  @Override
  public void setFornavn(String fornavn) {
    this.fornavn = fornavn;
  }

  @Override
  public void setEtternavn(String etternavn) {
    this.etternavn = etternavn;
  }

  @Override
  public void setMobil(String mobil) {
    this.mobil = mobil;
  }

  @Override
  public void setKjonn(String kjonn) {
    this.kjonn = kjonn;
  }

}
