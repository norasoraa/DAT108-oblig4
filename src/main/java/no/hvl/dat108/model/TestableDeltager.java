package no.hvl.dat108.model;

/**
 * Interface used for representing the {@link Deltager} in tests.
 * This interface will ensure that the test classes only have access to the
 * methods that is needed for testing the {@link Deltager}.
 */
public interface TestableDeltager {

  void setFornavn(String fornavn);

  void setEtternavn(String etternavn);

  void setMobilnummer(String mobilnummer);

  void setKjonn(String kjonn);

  void setPassord(String passord);

  void setRepetertPassord(String repetertPassord);

}
