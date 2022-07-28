package r24.tracker;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AggregateShareSales {

	@JsonProperty("share_price")
	private BigDecimal sharePrice;
	
	@JsonProperty("shares_left")
	private Integer sharesLeft;
	
}
