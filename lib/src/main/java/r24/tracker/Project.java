package r24.tracker;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {

	private Long id;
	
	private String slug;
	
	private String title;
	
	private String country;
	
	private List<AggregateShareSales> shareSales;
	
	private String estimatedDurationLeft;
	
	private BigDecimal interestRate;
	
	private BigDecimal maxBonusRate = BigDecimal.ZERO;
	
}
