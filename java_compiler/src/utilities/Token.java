package utilities;

import codigo_intermedio.Operand;
import java_compiler.LexicalAnalizer;

public class Token extends Operand{

	Integer token = Constants.DEFAULT_TOKEN;

	String msg = "Default msg";
	String lexeme = "";
	Integer myline = 0;

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
		return this.msg + " (" + this.lexeme + ")" + " en la linea: " + this.myline + " (Token: [ "+ token +" ])";
	}

	public void setLex(String s) {
		this.lexeme = s;
	}

	public String getLex() {
		return this.lexeme;
	}

	public void setLine(Integer l) {
		this.myline = new Integer(l);
	}

	public Integer getLine() {
		return this.myline;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return LexicalAnalizer.symbolTable.getType(this.lexeme);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Token";
	}

	@Override
	public String getRef() {
		// TODO Auto-generated method stub
		return this.lexeme;
	}

	@Override
	public String getMemRef() {
		// TODO Auto-generated method stub
		return this.getLex();
	}
}
