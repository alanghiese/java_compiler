package utilities;

public class CTNInformation extends SymbolInformation {

	
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
	
	@Override
	public String toString() {
		return "Constante";
	}
        
        

}
