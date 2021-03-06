package co.mercadolibre.api.adn.analyzer.domain;

public class Constant {

  private Constant() {

  }

  public static final String ERROR_SIZE_SEQUENCE = "**The minimum length of the DNA sequence to be "
      + "analyzed should be greater than %s **";
  public static final String ERROR_HOMOGENEOUS_MATRIX = "The matrix must be homogeneous. A row of %s was expected and "
      + "%s was received";
  public static final String ERROR_CHARACTERS = "Invalid characters, the valid characters are: A, T, C, G";

}
