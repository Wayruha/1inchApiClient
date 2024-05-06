package trade.wayruha.oneinch.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpotPricesRequest {
  private List<String> tokens;
  private String currency;
}
