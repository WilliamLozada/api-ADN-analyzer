package co.mercadolibre.api.adn.analyzer.config.exception;

public class DNALengthExceptionBuilder extends ExceptionBuilder<DNALengthException> {
  public DNALengthExceptionBuilder() {
  }

  public DNALengthException internalBuild() {
    return new DNALengthException(this.getCause(), this.getMessage(), this.getErrorCode(), this.getLocation(), this.getMoreInfo());
  }

  public static ExceptionBuilder<DNALengthException> builder() {
    return new DNALengthExceptionBuilder();
  }
}