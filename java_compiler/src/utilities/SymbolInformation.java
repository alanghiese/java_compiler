package utilities;

public class SymbolInformation {

	
	private String type = "Default type";
	
	
	@Override
	public String toString() {
		return "Symbol";
	}

	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	

	public boolean isFunction() {
		return false;
	}
	
}
