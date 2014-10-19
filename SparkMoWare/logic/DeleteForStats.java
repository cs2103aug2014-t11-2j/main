package logic;

public class DeleteForStats {
	
	/************** Data members **********************/
	
	private int deleteNumberOfTotalAssignment;
	private int deleteNumberOfTotalCompleted;
	private int deleteNumberOfTotalOnTime;
	
	/************** Constants **********************/
	
	/************** Constructors **********************/
	
	public DeleteForStats() {
	}

	/**************** Accessors ***********************/
	
	public int getDeletedTotalAssignment() {
		return this.deleteNumberOfTotalAssignment;
	}
	
	public int getDeletedTotalCompleted() {
		return this.deleteNumberOfTotalCompleted;
	}
	
	public int getDeletedTotalOnTime() {
		return this.deleteNumberOfTotalOnTime;
	}
	/**************** Mutators ************************/
	
	public void increaseDeleteTotalAssignment() {
		this.deleteNumberOfTotalAssignment++;
	}
	
	public void increaseDeleteTotalCompleted() {
		this.deleteNumberOfTotalCompleted++;
	}
	
	public void increaseDeleteTotalOnTime() {
		this.deleteNumberOfTotalOnTime++;
	}
}