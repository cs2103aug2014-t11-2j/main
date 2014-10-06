package parser;

public class UserInputConfirm extends UserInput {
	
	/************** Data members **********************/
	
	private String id;
	private String date;
	private String time;
	
	/************** Constructors **********************/
	
	UserInputConfirm() {
		
	}
	
	UserInputConfirm(String id, String date, String time) {
		setCommandType(CommandType.CONFIRM);
		setId(id);
		setDate(date);
		setTime(time);
	}
	
	/**************** Accessors ***********************/
	
	protected String getId() {
		return this.id;
	}
	
	protected String getDate() {
		return this.date;
	}
	
	protected String getTime() {
		return this.time;
	}
	
	/**************** Mutators ************************/

	private void setId(String id) {
		this.id = id;
	}
	
	private void setDate(String date) {
		this.date = date;
	}
	
	private void setTime(String time) {
		this.time = time;
	}
	
	/**************** Overriding ************************/
	
	public String toString() {
		return getCommandType() + "~" + getId() + "~" + getDate() + "~" + getTime();
	}	
}
