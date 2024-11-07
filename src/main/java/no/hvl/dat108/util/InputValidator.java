package no.hvl.dat108.util;

public class InputValidator {

  public static final String FORNAVN_PATTERN = "[A-ZÆØÅ][a-zæøåA-ZÆØÅ\\- ]{1,19}";
  public static final String ETTERNAVN_PATTERN = "[A-ZÆØÅ][a-zæøåA-ZÆØÅ\\-]{1,19}";
  public static final String MOBIL_PATTERN = "[0-9]{8}";
  public static final String PASSORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$";


  public static boolean isValidFornavn(String fornavn) {
    return fornavn != null && fornavn.matches(FORNAVN_PATTERN);
  }

  public static boolean isValidEtternavn(String etternavn) {
    return etternavn != null && etternavn.matches(ETTERNAVN_PATTERN);
  }

  public static boolean isValidMobil(String mobil) {
    return mobil != null && mobil.matches(MOBIL_PATTERN);
  }

  public static boolean isValidPassord(String passord) {
    return passord != null && passord.matches(PASSORD_PATTERN);
  }

}
