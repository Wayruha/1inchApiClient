package trade.wayruha.oneinch.service.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import trade.wayruha.oneinch.request.SpotPricesRequest;

import java.math.BigDecimal;
import java.util.Map;

public interface SpotApi {
  @POST("price/v1.1/{chainId}")
  Call<Map<String, BigDecimal>> getTokenPrices(@Path("chainId") int chainId,
                                               @Body SpotPricesRequest request);
}
