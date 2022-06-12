package co.mercadolibre.api.adn.analyzer.config.exception;

public abstract class ExceptionBuilder<T extends BaseException> {
  private String errorCode;
  private String message;
  private String location;
  private Throwable cause;
  private String moreInfo;

  public ExceptionBuilder() {
  }

  public ExceptionBuilder<T> exceptionCode(String exceptionCode) {
    this.errorCode = exceptionCode;
    return this;
  }

  public ExceptionBuilder<T> message(String message) {
    this.message = message;
    return this;
  }

  public ExceptionBuilder<T> location(String location) {
    this.location = location;
    return this;
  }

  public ExceptionBuilder<T> cause(Throwable cause) {
    this.cause = cause;
    return this;
  }

  public ExceptionBuilder<T> moreInfo(String moreInfo) {
    this.moreInfo = moreInfo;
    return this;
  }

  public T build() {
    return this.internalBuild();
  }

  public abstract T internalBuild();

  public String getErrorCode() {
    return this.errorCode;
  }

  public String getMessage() {
    return this.message;
  }

  public String getLocation() {
    return this.location;
  }

  public Throwable getCause() {
    return this.cause;
  }

  public String getMoreInfo() {
    return this.moreInfo;
  }
}
