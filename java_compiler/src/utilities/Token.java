package utilities;

public class Token {

	Integer token = -2;
	String aditionalInfo = "";
        String errInfo = "";
        String lexeme = "";
        
	public Integer getToken() {
		return this.token;
	}
	public void setToken(Integer t) {
		this.token = t;
	}
	
	public void setInfo(String s) {
		this.aditionalInfo = s;
	}
	public String getInfo() {
		return this.aditionalInfo;
	}
        
        public void setErr(String s) {
		this.errInfo = s;
	}
	public String getErr() {
		return this.errInfo;
	}

        public void setLex(String s) {
		this.lexeme = s;
	}
	public String getLex() {
		return this.lexeme;
	}

}
