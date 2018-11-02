package utilities;

public class FNCInformation extends SymbolInformation {

	private Permissions permissions = new Permissions(false,false);
	private String paramType = "Undefined";
	
	
	public FNCInformation() {
		// TODO Auto-generated constructor stub
	}
	
	public void setWrite(boolean b) {
		this.permissions.setWrite(b);
	}
	
	public void setPass(boolean b) {
		this.permissions.setPass(b);
	}
		
	public boolean isWrite() {
		return this.permissions.isWrite();
	}

	public boolean isPass() {
		return this.permissions.isPass();
	}
	
	public boolean isWritePass() {
		return this.permissions.isWrite() && this.permissions.isPass();
	}

	public boolean isReadonly() {
		return !this.permissions.isPass() && !this.permissions.isWrite();
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	@Override
	public String toString() {
		return "Function";
	}
	
	
	@Override
	public boolean isFunction() {
		return true;
	}
	


}
