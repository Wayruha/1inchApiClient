package trade.wayruha.oneinch.service;

import trade.wayruha.oneinch.Chain;
import trade.wayruha.oneinch.OneInchParams;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

class SpotServiceTest {
  public static void main(final String[] args) {
    final String apiKey = "apiKey";
    final OneInchParams params = new OneInchParams(apiKey);
    final SpotService service = new SpotService(params);

    System.out.println("Loading token prices...");
    final Map<String, BigDecimal> response = service.getTokenDollarPrices(Chain.ETHEREUM, List.of("0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2"));
    System.out.println(response);
  }
}