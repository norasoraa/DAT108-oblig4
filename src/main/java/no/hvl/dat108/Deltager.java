package no.hvl.dat108;

import jakarta.validation.constraints.Pattern;

/**
 * Class that represents a {@link Deltager}. Contains methods for extracting the
 * necessary information about the participant.
 */
public class Deltager implements TestableDeltager {

  @Pattern(regexp = "[A-ZÆØÅ][a-zæøåA-ZÆØÅ\\- ]{1,19}", message = "Fornavn må inneholde minst 2 tegn og ikke mer enn 20. Første tegn skal være en stor bokstav. Kan bare inneholde bokstaver, bindestrek og mellomrom.")
  private String fornavn;

  @Pattern(regexp = "[A-ZÆØÅ][a-zæøåA-ZÆØÅ\\-]{1,19}", message = "Etternavn må inneholde minst 2 tegn og ikke mer enn 20. Første tegn skal være en stor bokstav. Kan bare inneholde bokstaver og bindestrek.")
  private String etternavn;

  @Pattern(regexp = "[0-9]{8}", message = "Mobilnummer må være eksakt 8 sifre.")
  private String mobilnummer;

  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$", message = "Passord må inneholde minst 8 tegn, med minst én stor bokstav, én liten bokstav og et tall.")
  private String passord;

  private String repetertPassord;

  private String kjonn;

  /** Constructs a new {@link Deltager} with the given arguments. */
  public Deltager(String fornavn, String etternavn, String mobilnummer, String kjonn, String passord, String repetertPassord) {
    this.fornavn = fornavn;
    this.etternavn = etternavn;
    this.mobilnummer = mobilnummer;
    this.kjonn = kjonn;
    this.passord = passord;
    this.repetertPassord = repetertPassord;
  }

  public String getFornavn() {
    return fornavn;
  }

  public String getEtternavn() {
    return etternavn;
  }

  public String getMobilnummer() {
    return mobilnummer;
  }

  public String getPassord() {
    return passord;
  }

  public String getRepetertPassord() {
    return repetertPassord;
  }

  public String getKjonn() {
    return kjonn;
  }

  public String getNavn() {
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
  public void setMobilnummer(String mobilnummer) {
    this.mobilnummer = mobilnummer;
  }

  @Override
  public void setKjonn(String kjonn) {
    this.kjonn = kjonn;
  }

  @Override
  public void setPassord(String passord) {
    this.passord = passord;
  }

  @Override
  public void setRepetertPassord(String repetertPassord) {
    this.repetertPassord = repetertPassord;
  }

}
