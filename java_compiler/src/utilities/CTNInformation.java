package utilities;

public class CTNInformation extends SymbolInformation{

	
	public String type;
	
	public CTNInformation(int id) {
		super(id);
	}
	
	public void setType(String t) {
		this.type = t;
	}
	
	public String getType() {
		return this.type;
	}

}
