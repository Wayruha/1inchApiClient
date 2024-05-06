package trade.wayruha.oneinch.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiError {
  private int status;
  private String message;
  private Error error;

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Error {
    private String error;
    private String description;
    private int statusCode;
    private Object meta;
  }
}
