package semantic_actions;


import java.io.IOException;

import java_compiler.SymbolTable;

public class SemanticAction {

	
	public void execute(StringBuilder buffer, StringBuilder line, Integer token, SymbolTable st) throws IOException {
		buffer = buffer.append(line.charAt(0));
		line.deleteCharAt(0);
	}
	
}
