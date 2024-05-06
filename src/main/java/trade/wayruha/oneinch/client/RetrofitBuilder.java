package trade.wayruha.oneinch.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitBuilder {
  private final ObjectMapper objectMapper;

  public RetrofitBuilder(final ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public Retrofit buildRetrofit(final OkHttpClient httpClient, final String baseUrl) {
    Retrofit.Builder builder = new Retrofit.Builder();
    builder.client(httpClient);
    builder.addConverterFactory(JacksonConverterFactory.create(objectMapper));
    builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    builder.baseUrl(baseUrl);
    return builder.build();
  }
}
