package utilities;

public class CTNInformation extends SymbolInformation {

	private String type;
	private int counter = 0;

	public CTNInformation() {
	}

	public void increaseCounter() {
		this.counter++;
	}
	
	public void decreaseCounter() {
		this.counter--;
	}
	
	public int getCounter() {
		return this.counter;
	}
	
	
	public void setType(String t) {
		this.type = t;
	}

	public String getType() {
		return this.type;
	}

	@Override
	public String toString() {
		return "Constant - Type: " + type;
	}

}
