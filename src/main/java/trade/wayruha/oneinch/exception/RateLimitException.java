package trade.wayruha.oneinch.exception;

import lombok.Getter;
import lombok.Setter;
import trade.wayruha.oneinch.response.ApiError;

import java.net.URL;

@Getter
@Setter
public class RateLimitException extends ApiException {

  public RateLimitException(URL url, int httpCode, String errorMessage, ApiError apiError) {
    super(url, httpCode, errorMessage, apiError);
  }
}
