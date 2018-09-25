package semantic_actions;


import java.io.IOException;

import java_compiler.SymbolTable;
import utilities.Token;

public class SemanticAction {
	
	
	
	public void execute(StringBuilder buffer, StringBuilder line, Token token, SymbolTable st) throws IOException {
		
		if (line.length()>0) {
			buffer = buffer.append(line.charAt(0));
			line.deleteCharAt(0);
		}
		else
			buffer.append('\n');
		//System.out.println("as");
	}
	
}
