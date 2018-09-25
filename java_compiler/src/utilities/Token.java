package utilities;

public class Token {

	Integer token = Constants.DEFAULT_TOKEN;
	
        String msg = "";
        String lexeme = "";
        
	public Integer getToken() {
		return this.token;
	}
	public void setToken(Integer t) {
		this.token = t;
	}
	
	public void setMsg(String s) {
		this.msg = s;
	}
	public String getMsg() {
		return this.msg;
	}
      
    public void setLex(String s) {
		this.lexeme = s;
	}
	public String getLex() {
		return this.lexeme;
	}

}
