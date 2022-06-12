package co.mercadolibre.api.adn.analyzer.config.exception;

public class DNALengthException extends BaseException {
  private static final long serialVersionUID = 3325180189474835276L;

  public DNALengthException(Throwable cause, String message, String exceptionCode, String location, String moreInfo) {
    super(cause, message, exceptionCode, location, moreInfo);
  }
}
