package logic;

public class Sort {
	private static String sortClassify(String userInput) {

		String[] sortDetails = userInput.split(" ");
		
		switch(sortDetails[1]){
		
			case "deadline":
				
				return sort(sortDetails[2], sortDetails[3]);			
			
			case "tasks":

				return sort(sortDetails[2], sortDetails[3]);

			case "appointments":

				return sort(sortDetails[2], sortDetails[3]);

			case "alphabetical":

				return radixSort(sortDetails[2], sortDetails[3]);

			case "duration":

				return sortDuration(sortDetails[2], sortDetails[3]);

			default:
				
				//check for just "sort" input by user, then print out current linked list as it is chronological already
				//sort according to a particular title or ID. parse for int? or directly search for those first, then use  

		}
	}
			private static Assignment sortClassify(String userInput) {

				String[] sortDetails = userInput.split(" ");
				
				switch(sortDetails[1]){
				
					case "deadline":
						
						return sort(sortDetails[2], sortDetails[3]);			
					
					case "tasks":

						return sort(sortDetails[2], sortDetails[3]);

					case "appointments":

						return sort(sortDetails[2], sortDetails[3]);

					case "alphabetical":

						return radixSort(sortDetails[2], sortDetails[3]);

					case "duration":

						return sortDuration(sortDetails[2], sortDetails[3]);

					default:
						
						//check for just "sort" input by user, then print out current linked list as it is chronological already
						//sort according to a particular title or ID. parse for int? or directly search for those first, then use  

			}
				

				Collections.sort(buffer);
					return "To do list has been sorted";
		
			Collections.sort(buffer);
			return "To do list has been sorted";
		}
		

		

		private static Assignment sortDuration(String string, String string2) {
			// TODO Auto-generated method stub
			return null;
		}

		private static Assignment radixSort(String string, String string2) {
			Collections.sort(buffer);
			return null;
		}

		private static LinkedList<Assignment> sort(String end, String start) {
			
			String timeEnd;
			String timeStart;
			String dateEnd;
			String dateStart;
			LinkedList<Assignment> sortedList = new LinkedList<Assignment>();
			
			
			if(end.length() == 4){
				timeEnd = end;
				if(start == null){
					buffer.getFirst().getStartTime();
				}else{
					timeStart = start;
				}
			}else{
				dateEnd = end;
				if(start == null){
					buffer.getFirst().getStartDate();
				}else{
					dateStart = start;
				}
			}
			
			String[] endDate = dateEnd.split("(?<=\\G.{2})");

			while(!dateEnd.equals(dateStart)){  
				
				//insertion sort algo
				for(int i=1; i<sortedList.size(); i++){
					int next = sortedList.get(i).getEndDate();
					int j;
					for(j=i-1; j>=0 && sortedList.get(j).getEndDate()>next; j--){
						sortedList.get(j+1).getEndDate() = sortedList.get(j).getEndDate();
						sortedList.get(j+1).getEndDate() = next;
					}
				}
				
				//decrementing end date down to start date
				int[] intEndDate = Integer.parseInt(endDate); //if not use a for-loop to convert strings to int n store into the int array
				String updatedDate;

				if((intEndDate-1) == 0){
					if((intEndMonth-1)<0){
						intEndDate[2]--;
						intEndDate[1] = 12;
						intEndDate[0] = 31;	
					}
					else{
						intEndDate[2]--;
						intEndDate[1] = updateMonth(intEndMonth,intEndYear);
					}							
				}
				else{
					intEndDate[0]--;
				}
				
				updatedDate = String.valueOf(Arrays.toString(intEndDate));
				dateEnd = updatedDate.toString(); 	
			}
			
			return sortedList;
		}




}
