package java_compiler;

import java.util.HashMap;
import java.util.Set;

public class SymbolTable {

	HashMap<String,SymbolInformation> symbolTable;
	
	public SymbolTable() {
		this.symbolTable = new HashMap<>();
		char c;
		c='+';
		this.symbolTable.put("+", new SymbolInformation((int)c));
		c='-';
		this.symbolTable.put("-", new SymbolInformation((int)c));
		c='*';
		this.symbolTable.put("*", new SymbolInformation((int)c));
		c='/';
		this.symbolTable.put("/", new SymbolInformation((int)c));
		c='<';
		this.symbolTable.put("<", new SymbolInformation((int)c));
		c='>';
		this.symbolTable.put(">", new SymbolInformation((int)c));
		c='=';
		this.symbolTable.put("=", new SymbolInformation((int)c));
		c='{';
		this.symbolTable.put("{", new SymbolInformation((int)c));
		c='}';
		this.symbolTable.put("}", new SymbolInformation((int)c));
		c='(';
		this.symbolTable.put("(", new SymbolInformation((int)c));
		c=')';
		this.symbolTable.put(")", new SymbolInformation((int)c));
		c=',';
		this.symbolTable.put(",", new SymbolInformation((int)c));
		c=';';
		this.symbolTable.put(";", new SymbolInformation((int)c));
		
		this.symbolTable.put("<=", new SymbolInformation(256));
		this.symbolTable.put(">=", new SymbolInformation(257));
		this.symbolTable.put("!=", new SymbolInformation(258));
		this.symbolTable.put(":=", new SymbolInformation(259));
		this.symbolTable.put("if", new SymbolInformation(260));
		this.symbolTable.put("else", new SymbolInformation(261));
		this.symbolTable.put("end_if", new SymbolInformation(262));
		this.symbolTable.put("print", new SymbolInformation(263));
		this.symbolTable.put("while", new SymbolInformation(264));
		this.symbolTable.put("void", new SymbolInformation(265));
		this.symbolTable.put("ID", new SymbolInformation(266));
		this.symbolTable.put("usinteger", new SymbolInformation(267));
		this.symbolTable.put("linteger", new SymbolInformation(268));
		
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
