package trade.wayruha.oneinch;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Chain {
  ETHEREUM(1),
  ARBITRUM(42161),
  AURORA(1313161554),
  BSC(56),
  AVALANCHE(43114),
  BASE(8453),
  ZKSYNC(324),
  FANTOM(250),
  GNOSIS(100),
  KLAYTN(8217),
  OPTIMISM(10),
  POLYGON(137);

  @Getter
  private final int chainId;
}
