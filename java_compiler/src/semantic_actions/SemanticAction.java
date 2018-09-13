package semantic_actions;

public abstract class SemanticAction {
	private String buffer = "";
	private char actualChar;
	private int counter = 0;
	
	
	public void execute() {
		this.actualChar = this.buffer.charAt(counter);
		this.counter++;
	}
	
	public char getActualChar() {
		return this.actualChar;
	}
	
	public void decreaseCounter() {
		this.counter--;
	}
}
