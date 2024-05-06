package trade.wayruha.oneinch.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import trade.wayruha.oneinch.OneInchParams;

public abstract class BaseApiClient {
  private final Retrofit retrofit;
  @Getter private final OneInchParams config;

  @Getter(AccessLevel.PROTECTED)
  private final OkHttpClient httpClient;

  @Getter(AccessLevel.PROTECTED)
  private final ObjectMapper objectMapper;

  public BaseApiClient(
      final String baseUrl, final OneInchParams config, final ObjectMapper mapper) {
    this(baseUrl, config, mapper, new HttpClientBuilder(config), new RetrofitBuilder(mapper));
  }

  public BaseApiClient(
      final String baseUrl,
      final OneInchParams config,
      final ObjectMapper objectMapper,
      final HttpClientBuilder httpClientBuilder,
      final RetrofitBuilder retrofitBuilder) {
    this.config = config;
    this.httpClient = httpClientBuilder.buildClient();
    this.objectMapper = objectMapper;
    this.retrofit = retrofitBuilder.buildRetrofit(httpClient, baseUrl);
  }

  public <T> T createService(final Class<T> service) {
    return this.retrofit.create(service);
  }
}
