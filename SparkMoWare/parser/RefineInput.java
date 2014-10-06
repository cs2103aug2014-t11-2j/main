package parser;

import java.util.Arrays;

public class RefineInput {

	// Fundamentally the same as CommandType in SparkMoVare, but without single
	// word commands
	enum RefinementType {
		ADD, EDIT, DELETE, TENTATIVE, CONFIRM, SORT, SEARCH, CLEAR, INVALID,
		FILTER, DEFAULT
	}

	// Otherwise known as the Passer. Determines from the user input: command
	// and content, if any.
	protected static void determineUserInput(String userInput) {

		Arrays.fill(SparkMoVare.refinedUserInput, null); 
													
		String[] userInputArray = userInput.split(";");
		SparkMoVare.refinedUserInput[0] = userInputArray[0];

		switch (getRefinementType(userInput)) {
		case ADD:
			UserRefinementInput.userInputAdd(userInputArray);
			break;

		case EDIT:
			UserRefinementInput.userInputEdit(userInputArray);
			break;

		case DELETE:
			UserRefinementInput.userInputDelete(userInputArray);
			break;

		case TENTATIVE:
			UserRefinementInput.userInputTentative(userInputArray);
			break;

		case CONFIRM:
			UserRefinementInput.userInputConfirm(userInputArray);
			break;

		case CLEAR:
			UserRefinementInput.userInputclear(userInputArray);
			break;

		case SORT:
			UserRefinementInput.userInputSort(userInputArray);
			break;

		case SEARCH:
			UserRefinementInput.userInputSearch(userInputArray);
			break;

		case INVALID:
			SparkMoVare.refinedUserInput[0] = "invalid";
			break;
		
		case FILTER:
			UserRefinementInput.userInputFilter(userInputArray);
			break;
			
		default: // does nothing
		}
	}

	// Checks validity of the user input command
	protected static RefinementType getRefinementType(String userInput) {

		String refinement;
		String[] userInputArray = userInput.split(";");
		refinement = userInputArray[0];

		if (refinement.equalsIgnoreCase("add")) {
			if (userInputArray.length < 4) {
				return RefinementType.INVALID;
			}
			return RefinementType.ADD;

		} else if(refinement.equalsIgnoreCase("tentative")) {
			if(userInputArray.length < 3) {
				return RefinementType.INVALID;
			}
			return RefinementType.TENTATIVE;
			
		} else if (refinement.equalsIgnoreCase("confirm")) {
			if (userInputArray.length < 6) {
				return RefinementType.INVALID;
			}
			return RefinementType.CONFIRM;

		} else if (refinement.equalsIgnoreCase("delete")) {
			if (userInputArray.length < 2) {
				return RefinementType.INVALID;
			}
			return RefinementType.DELETE;

		} else if (refinement.equalsIgnoreCase("search")) {
			if (userInputArray.length < 2) {
				return RefinementType.INVALID;
			}
			return RefinementType.SEARCH;

		} else if (refinement.equalsIgnoreCase("edit")) {
			if (userInputArray.length < 4) {
				return RefinementType.INVALID;
			}
			return RefinementType.EDIT;

		} else if (refinement.equalsIgnoreCase("clear")) {
			return RefinementType.CLEAR;
		} else if (refinement.equalsIgnoreCase("sort")) {
			if(userInputArray.length < 2) {
				return RefinementType.INVALID;
			}
			return RefinementType.SORT;
		} else if(refinement.equalsIgnoreCase("filter")) {
			if(userInputArray.length < 2) {
				return RefinementType.INVALID;
			}
			return RefinementType.FILTER;
		}
		return RefinementType.DEFAULT;
	}
}
