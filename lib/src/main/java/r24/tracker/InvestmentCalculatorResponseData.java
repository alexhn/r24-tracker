package r24.tracker;

import java.util.List;

import lombok.Data;

@Data
public class InvestmentCalculatorResponseData {

	private Property property;
	
	private List<AggregateShareSales> aggregatedShareSales;
}
