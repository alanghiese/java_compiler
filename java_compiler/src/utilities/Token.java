package utilities;

public class Token {

	Integer token = -2;
	String aditionalInfo = "";
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
}
