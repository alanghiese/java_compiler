package java_compiler;

import java.util.HashMap;
import java.util.Set;

public class SymbolTable {

	HashMap<String,SymbolInformation> symbolTable;
	
	public SymbolTable() {
		this.symbolTable = new HashMap<>();
		this.symbolTable.put("+", new SymbolInformation(1));
		this.symbolTable.put("-", new SymbolInformation(2));
		this.symbolTable.put("*", new SymbolInformation(3));
		this.symbolTable.put("/", new SymbolInformation(4));
		this.symbolTable.put("<", new SymbolInformation(5));
		this.symbolTable.put(">", new SymbolInformation(6));
		this.symbolTable.put("<=", new SymbolInformation(7));
		this.symbolTable.put(">=", new SymbolInformation(8));
		this.symbolTable.put("!=", new SymbolInformation(9));
		this.symbolTable.put("{", new SymbolInformation(10));
		this.symbolTable.put("}", new SymbolInformation(11));
		this.symbolTable.put("(", new SymbolInformation(12));
		this.symbolTable.put(")", new SymbolInformation(13));
		this.symbolTable.put(",", new SymbolInformation(14));
		this.symbolTable.put(";", new SymbolInformation(15));
		this.symbolTable.put(":=", new SymbolInformation(16));
		this.symbolTable.put("if", new SymbolInformation(17));
		this.symbolTable.put("else", new SymbolInformation(18));
		this.symbolTable.put("end_if", new SymbolInformation(19));
		this.symbolTable.put("print", new SymbolInformation(20));
		this.symbolTable.put("while", new SymbolInformation(21));
		this.symbolTable.put("void", new SymbolInformation(22));
		this.symbolTable.put("ID", new SymbolInformation(23));
		this.symbolTable.put("usinteger", new SymbolInformation(25));
		this.symbolTable.put("linteger", new SymbolInformation(26));
		this.symbolTable.put("=", new SymbolInformation(27));
		
	}
	
	public void addSymbol(String symbol, int code){
		SymbolInformation si = new SymbolInformation(code);
		this.symbolTable.put(symbol, si);
	}
	
	public Integer getCode(String symbol) {
		return this.symbolTable.get(symbol).getId();
	}
	
	public Set<String> getAll() {
		return this.symbolTable.keySet();
	}
	
}
