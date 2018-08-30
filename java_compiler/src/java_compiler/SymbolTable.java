package java_compiler;

import java.util.HashMap;

public class SymbolTable {

	HashMap<String,Integer> symbolTable = new HashMap<>();
	
	public void addSymbol(String symbol, Integer code){
		this.symbolTable.put(symbol, code);
	}
	
	public Integer getCode(String symbol) {
		return this.symbolTable.get(symbol);
	}
	
}
