package parser;

public class UserInputSort extends UserInput {
	
	/************** Data members **********************/
	
	private String content;
	
	/************** Constructors **********************/
	
	public UserInputSort() {
		
	}
	
	public UserInputSort(String content) {
		setCommandType(CommandType.SORT);
		setContent(content);
	}
	
	/**************** Accessors ***********************/
	
	protected String getContent() {
		return this.content;
	}
	
	/**************** Mutators ************************/

	private void setContent(String content) {
		this.content = content;
	}
	
	/**************** Overriding ************************/
	
	public String toString() {
		return getCommandType() + "~" + getContent();
	}
}
