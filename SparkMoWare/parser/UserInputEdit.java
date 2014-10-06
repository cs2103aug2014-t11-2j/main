package parser;

public class UserInputEdit extends UserInput {
	
	/************** Data members **********************/
	
	private String id;
	private EditType target;
	private String content;
	
	/************** Constructors **********************/
	
	public UserInputEdit()  {
		
	}
	
	public UserInputEdit(String id,  String target, String content) {
		setCommandType(CommandType.EDIT);
		setId(id);
		setTarget(target);
		setContent(content);
	}
	
	/**************** Accessors ***********************/
	
	protected String getId() {
		return this.id;
	}
	
	protected  EditType getTarget() {
		return this.target;
	}
	
	protected  String getContent() {
		return this.content;
	}
	
	/**************** Mutators ************************/

	private void setId(String id) {
		this.id = id;
	}
	
	private void setTarget(String target) {
		this.target = ParserEditLocal.determineEditType(target);
	}
	
	private void setContent(String content) {
		this.content = content;
	}
	
	/**************** Overriding ************************/
	
	public String toString() {
		return getCommandType() + "~" + getId() + "~" + getTarget() + "~" + getContent();
	}		
}
