package r24.tracker;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class InvestmentCalculatorQuery {

	private String operationName = "investmentCalculator";
	
	private Map<String, Object> variables = new HashMap<>();
	
	private String query = "query investmentCalculator($id: Int!, $amount: Float, $useCredits: Boolean) {\n  property(id: $id) {\n    id\n    ...PropertyFundingProgressFragment\n    ...PropertyProfitEstimateFragment\n    ...PropertyValueLabelFragment\n    status\n    deal_type\n    primary_market_stats {\n      min_investment\n      shares_left\n      __typename\n    }\n    secondary_market_stats {\n      min_investment\n      __typename\n    }\n    __typename\n  }\n  me {\n    id\n    email\n    ...MyCreditsBalanceFragment\n    __typename\n  }\n  aggregatedShareSales(property_id: $id, ignore_my_sales: false) {\n    ...AggregatedShareSalesFragment\n    __typename\n  }\n  myAggregatedShareSales(property_id: $id) {\n    ...AggregatedShareSalesFragment\n    __typename\n  }\n  calculation: calculateInvestment(\n    property_id: $id\n    total_amount: $amount\n    use_credits: $useCredits\n  ) {\n    shares\n    share_initial_price\n    share_buying_price\n    share_price_change\n    net_amount\n    commission_amount\n    gross_amount\n    credits\n    total_amount\n    share_distribution {\n      shares\n      share_price\n      __typename\n    }\n    capital_change\n    annual_dividend_yield\n    annual_capital_growth\n    roi\n    total_interest\n    bonus_interest\n    __typename\n  }\n  validateInvestment(\n    property_id: $id\n    total_amount: $amount\n    use_credits: $useCredits\n  )\n}\n\nfragment PropertyFundingProgressFragment on Property {\n  id\n  status\n title percents_funded\n  funded_amount\n  funding_left\n  __typename\n}\n\nfragment PropertyProfitEstimateFragment on Property {\n  id\n  status\n  success_fee\n  buy_to_let {\n    dividend_yield\n    gross_yield\n    capital_growth\n    __typename\n  }\n  buy_to_sell {\n    irr\n    roi\n    term\n    __typename\n  }\n  loan {\n    interest_rate\n    length\n    max_bonus_rate\n    __typename\n  }\n  exited_irr\n  estimated_duration_left\n  __typename\n}\n\nfragment PropertyValueLabelFragment on Property {\n  id\n  deal_type\n  status\n  book_value\n  funding_target\n  exited_price\n  __typename\n}\n\nfragment MyCreditsBalanceFragment on User {\n  id\n  selected_customer {\n    id\n    credits_balance {\n      total\n      primary_market\n      secondary_market\n      __typename\n    }\n    __typename\n  }\n  __typename\n}\n\nfragment AggregatedShareSalesFragment on ShareSaleAggregationItem {\n  share_price\n  shares_left\n  __typename\n}";
	
	
}
