/*Super class for the various user inputs
 */

package parser;

public class UserInput {

	/************** Data members **********************/
	
	private CommandType command;
	
	/************** Constructors **********************/
	
		public UserInput() {
		
	}
	
	/**************** Accessors ***********************/
	
	protected CommandType getCommandType() {
		return this.command;
	}
	
	/**************** Mutators ************************/

	protected void setCommandType(CommandType command) {
		this.command = command;
	}
	
	/**************** Overriding ************************/
	
	public String toString() {
		return "" + getCommandType();
	}
}
