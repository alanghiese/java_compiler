package utilities;

public class FNCInformation extends SymbolInformation {

	private Permissions permissions = new Permissions(false,false);
	private String paramType = "FNC Undefined";
	private String paramName = "NoName";
	
	
	public FNCInformation() {
		// TODO Auto-generated constructor stub
	}
	
	public void setWrite(boolean b) {
		this.permissions.setWrite(b);
	}
	
	public void setPass(boolean b) {
		this.permissions.setPass(b);
	}
	
	public void setParamName(String name) {
		this.paramName = name;
	}
	
	public String getParamName() {
		return this.paramName;
	}
	
	public void setPermissions(Permissions p) {
		this.permissions.setPass(p.isPass());
		this.permissions.setWrite(p.isWrite());
	}
	
	public boolean writes() {
		return this.permissions.isWrite();
	}

	public boolean passes() {
		return this.permissions.isPass();
	}
	
	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	@Override
	public String toString() {
		return "Function tipo: " + super.getType() + " tipo de parametro: " + this.getParamType();
	}
	
	
	@Override
	public boolean isFunction() {
		return true;
	}
	


}
