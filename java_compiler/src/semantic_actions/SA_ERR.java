package semantic_actions;

import java.io.IOException;

import java_compiler.SymbolTable;
import utilities.Constants;
import utilities.Token;

public class SA_ERR extends SA4{
	
	
	
	@Override
	public void execute(StringBuilder buffer, StringBuilder line, Token token, SymbolTable st) throws IOException {
		super.execute(buffer, line, token, st);
		token.setToken(Constants.ERR_TOKEN);
		token.setErr("ERROR: Token no reconocido (Error Lexico) en la linea: " );
		//System.out.println("err");
	}

}
