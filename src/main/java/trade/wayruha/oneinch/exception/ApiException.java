package trade.wayruha.oneinch.exception;

import lombok.Getter;
import lombok.ToString;
import trade.wayruha.oneinch.response.ApiError;

import java.net.URL;

@Getter
@ToString
public class ApiException extends RuntimeException {
  private final URL url;
  private final Integer httpCode;
  private final String errorMessage;
  private final ApiError apiError;

  public ApiException(final URL url, final int httpCode, final String errorMessage) {
    super(errorMessage);
    this.url = url;
    this.httpCode = httpCode;
    this.errorMessage = errorMessage;
    this.apiError = null;
  }

  public ApiException(
      final URL url, final int httpCode, final String errorMessage, final ApiError apiError) {
    super(errorMessage);
    this.url = url;
    this.httpCode = httpCode;
    this.errorMessage = errorMessage;
    this.apiError = apiError;
  }

  public ApiException(final URL url, final String message, final Throwable cause) {
    super(message, cause);
    this.url = url;
    this.httpCode = null;
    this.errorMessage = null;
    this.apiError = null;
  }
}
