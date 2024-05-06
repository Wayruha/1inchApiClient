package trade.wayruha.oneinch.service;

import trade.wayruha.oneinch.Chain;
import trade.wayruha.oneinch.ClientConstant;
import trade.wayruha.oneinch.OneInchParams;
import trade.wayruha.oneinch.client.InchApiClient;
import trade.wayruha.oneinch.request.SpotPricesRequest;
import trade.wayruha.oneinch.service.api.SpotApi;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class SpotService {
  private final SpotApi api;
  private final InchApiClient client;

  public SpotService(OneInchParams params) {
    this.client = new InchApiClient(params);
    this.api = client.createService(SpotApi.class);
  }

  public Map<String, BigDecimal> getTokenPrices(Chain chain, List<String> tokenAddresses, String currency) {
    final SpotPricesRequest payload = new SpotPricesRequest(tokenAddresses, currency);
    return client.executeSync(api.getTokenPrices(chain.getChainId(), payload));
  }

  public Map<String, BigDecimal> getTokenDollarPrices(Chain chain, List<String> tokenAddresses) {
    return getTokenPrices(chain, tokenAddresses, ClientConstant.USD_CURRENCY);
  }
}
