package utilities;

import java.util.HashMap;

public class Decoder {
	HashMap<String,Integer> codes;
	
	public Decoder() {
		this.codes = new HashMap<>();
		this.codes.put("+", (int)'+');
		this.codes.put("-", (int)'-');
		this.codes.put("*", (int)'*');
		this.codes.put("/", (int)'/');
		this.codes.put("<", (int)'<');
		this.codes.put(">", (int)'>');
		this.codes.put("=", (int)'=');
		this.codes.put("(", (int)'(');
		this.codes.put(")", (int)')');
		this.codes.put("{", (int)'{');
		this.codes.put("}", (int)'}');
		this.codes.put(",", (int)',');
		this.codes.put(";", (int)';');
		this.codes.put(">=", 257);
		this.codes.put("<=", 258);
		this.codes.put("!=", 259);
		this.codes.put(":=", 260);
		this.codes.put("if", 261);
		this.codes.put("end_if", 262);
		this.codes.put("else", 263);
		this.codes.put("print", 264);
		this.codes.put("while", 265);
		this.codes.put("usinteger", 266);
		this.codes.put("linteger", 267);
		this.codes.put("readonly", 268);
		this.codes.put("write", 269);
		this.codes.put("pass", 270);
		this.codes.put("ID", 270);
		this.codes.put("CTE", 271);
		
	}
	
}
