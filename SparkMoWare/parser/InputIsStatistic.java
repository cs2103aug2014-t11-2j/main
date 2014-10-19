package parser;

import parser.EnumGroup.CommandType;

public class InputIsStatistic {
	
	protected static RefinedUserInput refineInput(String userInput) {
		
		RefinedUserInput inputStatistic =  new RefinedUserInput(
				CommandType.STATISTIC, null,
				null, null,
				null, null,
				null, null,
				null);
		
		return inputStatistic;
	}
	
}
