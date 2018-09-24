package semantic_actions;

import java.io.IOException;

import java_compiler.SymbolTable;

public class SA_ERR extends SA4{
	
	
	@Override
	public void execute(StringBuilder buffer, StringBuilder line, Integer token, SymbolTable st) throws IOException {
		super.execute(buffer, line, token, st);
		token = -1;	
	}

}
