package parser;

public class UserInputSearch extends UserInput {
	
	/************** Data members **********************/
	
	private String content;
	
	/************** Constructors **********************/
	
	public UserInputSearch() {
		
	}
	
	public UserInputSearch(String content) {
		setCommandType(EnumGroup.CommandType.SEARCH);
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
