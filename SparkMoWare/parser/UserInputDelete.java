package parser;

public class UserInputDelete extends UserInput {
	
	/************** Data members **********************/
	
	private String id;
	
	/************** Constructors **********************/
	
	public UserInputDelete() {
		
	}
	
	public UserInputDelete(String id) {
		setCommandType(CommandType.DELETE);
		setId(id);
	}
	
	/**************** Accessors ***********************/
	
	protected String getId() {
		return this.id;
	}
	
	/**************** Mutators ************************/

	private void setId(String id) {
		this.id = id;
	}
	
	/**************** Overriding ************************/
	
	public String toString() {
		return getCommandType() + "~" + getId();
	}
}
