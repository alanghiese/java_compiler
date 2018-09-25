package semantic_actions;

import java_compiler.SymbolTable;
import utilities.Token;

public class SA0 implements SemanticAction{

	@Override
	public void execute(StringBuilder buffer, StringBuilder line, Token token, SymbolTable st) {
		buffer.append(line.charAt(0));
		line.deleteCharAt(0);	
		
	}

}
