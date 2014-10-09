package parser;

public class UserInputFinish extends UserInput {

	/************** Data members **********************/
	
	private String content;
	
	/************** Constructors **********************/

	public UserInputFinish() {
		
	}
	
	public UserInputFinish(String content) {
		setCommandType(EnumGroup.CommandType.FINISH);
		setContent(content);
	}

	/**************** Accessors ***********************/

	protected String getContent() {
		return this.content;
	}

	/**************** Mutators ************************/

	protected void setContent(String content) {
		this.content = content;
	}

	/**************** Overriding ************************/

	public String toString() {
		return getCommandType() + "~" + getContent();
	}

}
