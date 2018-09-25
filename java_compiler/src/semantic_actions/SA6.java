package semantic_actions;

import java.io.IOException;

import java_compiler.SymbolTable;
import utilities.Decoder;
import utilities.Token;

public class SA6   extends SemanticAction{

	
	
	
	@Override
	public void execute(StringBuilder buffer, StringBuilder line, Token token, SymbolTable st) throws IOException {
		//System.out.println("as6");
            if (token.getToken()==-1)
                token.setInfo("ERROR: Token no reconocido (Error Lexico) en la linea: " );

            token.setToken(Decoder.get(buffer.toString()));
            buffer.setLength(0);
	}
}
