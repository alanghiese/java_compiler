package java_compiler;

public class IDInformation extends SymbolInformation{

	private String type;
	public IDInformation(int id) {
		super(id);
	}
	
	public void setType(String t) {
		this.type = t;
	}
	
	public String getType() {
		return this.type;
	}

}
