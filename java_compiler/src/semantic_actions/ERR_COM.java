package semantic_actions;

import java_compiler.ParserVal;
import java_compiler.SymbolTable;
import utilities.Constants;
import utilities.Token;

public class ERR_COM implements SemanticAction {

	@Override
	public void execute(StringBuilder buffer, StringBuilder line, Token token, SymbolTable st, ParserVal yylval) {
		token.setMsg("ERROR LEXICO: Comentario sin cerrar ");
		token.setLex("##");
		token.setToken(Constants.ERR_TOKEN);
		buffer.setLength(0);

	}

}
