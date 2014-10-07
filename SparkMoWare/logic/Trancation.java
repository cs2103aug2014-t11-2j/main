package logic;

import logic.Assignment;

import java.util.LinkedList;

public class Trancation {

	// overloading trancateList method for 2 sets of parameters
	public static LinkedList<Assignment> trancateList(LinkedList<Assignment> list, String endDate,
			String startDate) {

		LinkedList<Assignment> trancatedList = new LinkedList<Assignment>();
		trancatedList = list;

		int lowerLimit = searchLimit(trancatedList, startDate, "lower");
		for (int i = 0; i < lowerLimit; i++) {
			trancatedList.remove(i);
		}

		int upperLimit = searchLimit(trancatedList, endDate, "upper");
		if(upperLimit == trancatedList.size()){
			return trancatedList;
		}else{
			for (int j = trancatedList.size(); j > upperLimit; j--) {
				trancatedList.remove(j);
			}
		}
		return trancatedList;
	}

	public static LinkedList<Assignment> trancateList(String endDate,
			String startDate) {

		LinkedList<Assignment> trancatedList = new LinkedList<Assignment>();
		trancatedList = SparkMoVare.buffer;

		int lowerLimit = searchLimit(trancatedList, startDate, "lower");
		for (int i = 0; i < lowerLimit; i++) {
			trancatedList.remove(i);
		}

		int upperLimit = searchLimit(trancatedList, endDate, "upper");
		if(upperLimit == trancatedList.size()){
			return trancatedList;
		}else{
			for (int j = trancatedList.size(); j > upperLimit; j--) {
				trancatedList.remove(j);
			}
		}
		return trancatedList;
	}

	private static int searchLimit(LinkedList<Assignment> trancatedList,
			String date, String limitType) {
		
		int limitIndex = 0;
		LinkedList<Assignment> possibleIndexes = new LinkedList<Assignment>(); 
		possibleIndexes = SearchAll.searchAll(date);
		
		if(limitType.equals("lower")){
			for(int i = 0; i < possibleIndexes.size(); i++){
				if(possibleIndexes.get(i).getStartDate().equals(date)){
					if(i > limitIndex){
						return i;
					}
				}
			}
		} else {
			for(int i = 0; i < possibleIndexes.size(); i++){
				if(possibleIndexes.get(i).getEndDate().equals(date)){
					if(i > limitIndex){
						limitIndex = i;
					}
				}
			}
		}
		return limitIndex;
	}
}