package trade.wayruha.oneinch;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import static trade.wayruha.oneinch.ClientConstant.API_BASE_URL;
import static trade.wayruha.oneinch.ClientConstant.HTTP_CLIENT_TIMEOUT_MS;

@Data
@RequiredArgsConstructor
public class OneInchParams {

  private String apiBaseUrl = API_BASE_URL;

  private long httpConnectTimeout = HTTP_CLIENT_TIMEOUT_MS;

  private long httpReadTimeout = HTTP_CLIENT_TIMEOUT_MS;

  private long httpWriteTimeout = HTTP_CLIENT_TIMEOUT_MS;

  // Retry on failed connection, default true.
  private boolean retryOnConnectionFailure = true;
  // Should we log request's data?
  private boolean logTraffic = false;

  private final String apiKey;
}
