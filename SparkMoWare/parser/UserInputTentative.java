package parser;

public class UserInputTentative extends UserInput {
	
	/************** Data members **********************/
	
	private String title;
	private int numberOfTentative;
	
	/************** Constructors **********************/
	
	public UserInputTentative() {
		
	}
	
	public UserInputTentative( String title, int numberOfTentative) {
		setCommandType(EnumGroup.CommandType.TENTATIVE);
		setTitle(title);
		setNumberOfTentative(numberOfTentative);
		
	}
	
	/**************** Accessors ***********************/
	
	protected String getTitle() {
		return this.title;
	}	
	
	protected int getNumberOfTentative() {
		return this.numberOfTentative;
	}
	
	/**************** Mutators ************************/
	
	private void setTitle(String title) {
		this.title = title;
	}
	
	private void setNumberOfTentative(int numberOfTentative) {
		this.numberOfTentative = numberOfTentative;
	}

	/**************** Overriding ************************/
	
	public String toString() {
		return getCommandType() + "~" + getTitle() + "~" + getNumberOfTentative();
	}
}
