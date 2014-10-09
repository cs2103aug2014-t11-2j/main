//Super class for the various user inputs

package parser;

public class UserInput {

	/************** Data members **********************/
	
	private EnumGroup.CommandType command;
	
	/************** Constructors **********************/
	
		public UserInput() {
		// TODO Auto-generated constructor stub
	}
	
	/**************** Accessors ***********************/
	
	protected EnumGroup.CommandType getCommandType() {
		return this.command;
	}
	
	/**************** Mutators ************************/

	protected void setCommandType(EnumGroup.CommandType command) {
		this.command = command;
	}
	
	/**************** Overriding ************************/
	
	
}
