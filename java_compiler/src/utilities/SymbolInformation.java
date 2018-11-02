package utilities;

public class SymbolInformation {
	public final static String _DEFAULT_TYPE = "Default type";

	protected String type = _DEFAULT_TYPE;

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

	public boolean isVar() {
		return false;
	}
}
