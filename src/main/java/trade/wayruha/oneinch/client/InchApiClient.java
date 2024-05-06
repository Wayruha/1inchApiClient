package trade.wayruha.oneinch.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import trade.wayruha.oneinch.ClientConstant;
import trade.wayruha.oneinch.OneInchParams;
import trade.wayruha.oneinch.exception.ApiException;
import trade.wayruha.oneinch.exception.RateLimitException;
import trade.wayruha.oneinch.response.ApiError;

import java.io.IOException;
import java.net.URL;

import static java.util.Objects.isNull;

@Slf4j
public class InchApiClient extends BaseApiClient {
  public InchApiClient(final OneInchParams config) {
    this(config, new ObjectMapper());
  }

  public InchApiClient(final OneInchParams config, final ObjectMapper mapper) {
    super(ClientConstant.API_BASE_URL, config, mapper);
  }

  public <T> T executeSync(final Call<T> call) {
    final String rawRequestData = call.request().toString();
    final URL url = call.request().url().url();
    try {

      final Response<T> response = call.execute();
      final int responseCode = response.code();
      final T body = response.body();
      if (!response.isSuccessful()) {
        log.error(
            "Request failed: {}. {}. Message: {} ",
            responseCode,
            rawRequestData,
            response.message());
        final ApiError apiError = parseError(response.errorBody());
        if (apiError != null) {
          if (responseCode == ClientConstant.RATE_LIMIT_RESPONSE_CODE) {
            throw new RateLimitException(url, responseCode, apiError.getMessage(), apiError);
          } else {
            throw new ApiException(url, responseCode, apiError.getMessage(), apiError);
          }
        }
        throw new ApiException(url, responseCode, response.message());
      }
      return body;
    } catch (IOException e) {
      log.error("Unexpected error. Request data: {}", rawRequestData, e);
      throw new ApiException(url, e.getMessage(), e);
    }
  }

  private ApiError parseError(final ResponseBody errorBody) throws IOException {
    if (isNull(errorBody)) {
      return null;
    }
    final String json = errorBody.string();
    if (json == null || json.isBlank()) {
      return null;
    }
    return getObjectMapper().readValue(json, ApiError.class);
  }
}
