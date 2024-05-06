package trade.wayruha.oneinch;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class ClientConstant {
  public static final String API_BASE_URL = "https://api.1inch.dev/";

  /**
   * The default timeout is 30 seconds.
   */
  public static final long HTTP_CLIENT_TIMEOUT_MS = 30 * 1000;

  public static final int RATE_LIMIT_RESPONSE_CODE = 429;

  //  currencies
  public static final String USD_CURRENCY = "USD";

}
