package no.hvl.dat108;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import no.hvl.dat108.model.Deltager;
import no.hvl.dat108.model.TestableDeltager;

public class DeltagerValideringTest {

  private Validator validator;
  private TestableDeltager testDeltager;

  @BeforeEach
  void setUp() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
    testDeltager = new Deltager("Arne", "Arnesen", "12345678", "mann", "HlkKw928nd", "Hlksw928nd");
  }

  @Test
  void testDeltagerHarGyldigeInitVerdier() {
    sjekkGyldig();
  }

  @Test
  void gyldigFornavnMellomrom() {
    testDeltager.setFornavn("Hanna Margrete");
    sjekkGyldig();
  }

  @Test
  void gyldigFornavnBindestrek() {
    testDeltager.setFornavn("Hanna-Margrete");
    sjekkGyldig();
  }

  @Test
  void ugyldigFornavnLitenBokstav() {
    testDeltager.setFornavn("sofie");
    sjekkUgyldigVedBestemtFeil(
        "Fornavn må inneholde minst 2 tegn og ikke mer enn 20. Første tegn skal være en stor bokstav. Kan bare inneholde bokstaver, bindestrek og mellomrom.");
  }

  @Test
  void ugyldigFornavnFørsteTegn() {
    testDeltager.setFornavn("-Tore");
    sjekkUgyldigVedBestemtFeil(
        "Fornavn må inneholde minst 2 tegn og ikke mer enn 20. Første tegn skal være en stor bokstav. Kan bare inneholde bokstaver, bindestrek og mellomrom.");
  }

  @Test
  void ugyldigFornavnUgyldigTegn() {
    testDeltager.setFornavn("Sofia*");
    sjekkUgyldigVedBestemtFeil(
        "Fornavn må inneholde minst 2 tegn og ikke mer enn 20. Første tegn skal være en stor bokstav. Kan bare inneholde bokstaver, bindestrek og mellomrom.");
  }

  @Test
  void ugyldigFornavnAntallMin() {
    testDeltager.setFornavn("K");
    sjekkUgyldigVedBestemtFeil("Fornavn må inneholde minst 2 tegn og ikke mer enn 20. Første tegn skal være en stor bokstav. Kan bare inneholde bokstaver, bindestrek og mellomrom.");
  }

  @Test
  void ugyldigFornavnAntallMaks() {
    testDeltager.setFornavn("Hdjdjrofksldkepslfknj");
    sjekkUgyldigVedBestemtFeil("Fornavn må inneholde minst 2 tegn og ikke mer enn 20. Første tegn skal være en stor bokstav. Kan bare inneholde bokstaver, bindestrek og mellomrom.");
  }

  @Test
  void gyldigEtternavnBindestrek() {
    testDeltager.setEtternavn("Pedersen-Hansen");
    sjekkGyldig();
  }

  @Test
  void ugyldigEtternavnMellomrom() {
    testDeltager.setEtternavn("Pedersen Hansen");
    sjekkUgyldigVedBestemtFeil(
        "Etternavn må inneholde minst 2 tegn og ikke mer enn 20. Første tegn skal være en stor bokstav. Kan bare inneholde bokstaver og bindestrek.");
  }
  @Test
  void ugyldigEtternavnAntallMin() {
    testDeltager.setEtternavn("L");
    sjekkUgyldigVedBestemtFeil("Etternavn må inneholde minst 2 tegn og ikke mer enn 20. Første tegn skal være en stor bokstav. Kan bare inneholde bokstaver og bindestrek.");
  }

  @Test
  void ugyldigEtternavnAntallMaks() {
    testDeltager.setEtternavn("Kjdhrjfksoejfhskfldli");
    sjekkUgyldigVedBestemtFeil("Etternavn må inneholde minst 2 tegn og ikke mer enn 20. Første tegn skal være en stor bokstav. Kan bare inneholde bokstaver og bindestrek.");
  }

  @Test
  void ugyldigNummerMedBokstav() {
    testDeltager.setMobil("1234567k");
    sjekkUgyldigVedBestemtFeil("Mobilnummer må være eksakt 8 sifre.");
  }

  @Test
  void ugyldigNummerAntallSiffer() {
    testDeltager.setMobil("12345");
    sjekkUgyldigVedBestemtFeil("Mobilnummer må være eksakt 8 sifre.");
  }

  @Test
  void gyldigPassord() {
    testDeltager.setPassord("Klei12345");
    sjekkGyldig();
  }

  @Test
  void ugyldigPassordUtenStorBokstav() {
    testDeltager.setPassord("kjoes0992");
    sjekkUgyldigVedBestemtFeil(
        "Passord må inneholde minst 8 tegn, med minst én stor bokstav, én liten bokstav og et tall.");
  }

  @Test
  void ugyldigPassordUtenLitenBokstav() {
    testDeltager.setPassord("92KDJ45J");
    sjekkUgyldigVedBestemtFeil("Passord må inneholde minst 8 tegn, med minst én stor bokstav, én liten bokstav og et tall.");
  }

  @Test
  void ugyldigPassordUtenTall() {
    testDeltager.setPassord("heDKeksjeL");
    sjekkUgyldigVedBestemtFeil("Passord må inneholde minst 8 tegn, med minst én stor bokstav, én liten bokstav og et tall.");
  }

  @Test
  void ugyldigPassordAntall() {
    testDeltager.setPassord("Hei82");
    sjekkUgyldigVedBestemtFeil("Passord må inneholde minst 8 tegn, med minst én stor bokstav, én liten bokstav og et tall.");
  }

  private void sjekkUgyldigVedBestemtFeil(String feilmelding) {
    Set<ConstraintViolation<TestableDeltager>> violations = validator.validate(testDeltager);
    assertFalse(violations.isEmpty());
    assertThat(violations).hasSize(1);
    String violationMessage = violations.iterator().next().getMessage();
    assertEquals(feilmelding, violationMessage);
  }

  private void sjekkGyldig() {
    Set<ConstraintViolation<TestableDeltager>> violations = validator.validate(testDeltager);
    assertTrue(violations.isEmpty());
  }
}
