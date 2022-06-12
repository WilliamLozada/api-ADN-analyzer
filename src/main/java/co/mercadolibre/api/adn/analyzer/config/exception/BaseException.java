package co.mercadolibre.api.adn.analyzer.config.exception;

import java.util.Optional;

public abstract class BaseException extends RuntimeException {
  private static final long serialVersionUID = 6577914901097700186L;
  private final String exceptionCode;
  private final String location;
  private final String moreInfo;

  protected BaseException(Throwable cause, String message, String exceptionCode, String location, String moreInfo) {
    super(message, cause);
    this.exceptionCode = exceptionCode;
    this.location = location;
    this.moreInfo = moreInfo;
  }

  public String getErrorCode() {
    return this.exceptionCode;
  }

  public String getLocation() {
    return this.location;
  }

  public Optional<String> getMoreInfo() {
    return Optional.ofNullable(this.moreInfo);
  }
}