package co.mercadolibre.api.adn.analyzer.config.exception;

public class DNAInvalidExceptionBuilder extends ExceptionBuilder<DNAInvalidException> {
  public DNAInvalidExceptionBuilder() {
    // TODO document why this constructor is empty
  }

  public DNAInvalidException internalBuild() {
    return new DNAInvalidException(this.getCause(), this.getMessage(), this.getErrorCode(), this.getLocation(), this.getMoreInfo());
  }

  public static ExceptionBuilder<DNAInvalidException> builder() {
    return new DNAInvalidExceptionBuilder();
  }
}
