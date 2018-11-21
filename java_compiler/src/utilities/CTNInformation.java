package utilities;

public class CTNInformation extends SymbolInformation {

	
	private int counter = 0;
	public static int id = 0;
	private int myId;

	public CTNInformation() {
		this.myId = id;
		id++;
	}
	
	public int getMyId() {
		return this.myId;
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
		return "Constante tipo: " + super.type;
	}
        
	
        

}
