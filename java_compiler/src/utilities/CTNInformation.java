package utilities;

public class CTNInformation extends SymbolInformation {

	public String type;

	public CTNInformation() {
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
