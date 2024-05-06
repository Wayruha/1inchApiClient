package trade.wayruha.oneinch.client;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import trade.wayruha.oneinch.OneInchParams;

import java.util.concurrent.TimeUnit;

public class HttpClientBuilder {
  private final OneInchParams config;
  private final HttpLoggingInterceptor loggingInterceptor;

  public HttpClientBuilder(final OneInchParams config) {
    this(config, getDefaultLoggingInterceptor());
  }

  public HttpClientBuilder(
      final OneInchParams config, final HttpLoggingInterceptor loggingInterceptor) {
    this.config = config;
    this.loggingInterceptor = loggingInterceptor;
  }

  public OkHttpClient buildClient() {
    final OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
    clientBuilder
        .connectTimeout(this.config.getHttpConnectTimeout(), TimeUnit.MILLISECONDS)
        .readTimeout(this.config.getHttpReadTimeout(), TimeUnit.MILLISECONDS)
        .writeTimeout(this.config.getHttpWriteTimeout(), TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(this.config.isRetryOnConnectionFailure());

    if (config.getApiKey() != null && !config.getApiKey().isBlank()) {
      final AuthenticationInterceptor signatureInterceptor =
          new AuthenticationInterceptor(config.getApiKey());
      clientBuilder.addInterceptor(signatureInterceptor);
    }

    if (this.config.isLogTraffic() && loggingInterceptor != null) {
      clientBuilder.addInterceptor(loggingInterceptor);
    }
    return clientBuilder.build();
  }

  @NotNull
  private static HttpLoggingInterceptor getDefaultLoggingInterceptor() {
    final HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new TrafficLogger());
    logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return logInterceptor;
  }
}
