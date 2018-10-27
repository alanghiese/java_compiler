package utilities;

public class Permissions {
	
	private boolean write;
	private boolean pass;

	public Permissions(boolean w, boolean p) {
		this.write = w;
		this.pass = p;
	}

	public boolean isWrite() {
		return write;
	}

	public boolean isPass() {
		return pass;
	}

	public void setWrite(boolean write) {
		this.write = write;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}
	
	

}
