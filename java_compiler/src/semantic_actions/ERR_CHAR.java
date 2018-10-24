package semantic_actions;

import java_compiler.ParserVal;
import java_compiler.SymbolTable;
import utilities.Constants;
import utilities.Token;

public class ERR_CHAR implements SemanticAction {

	@Override
	public void execute(StringBuilder buffer, StringBuilder line, Token token, SymbolTable st, ParserVal yylval) {
		token.setMsg("ERROR LEXICO: apareció CARACTER desconocido ");
		String lex = new String();
		lex = lex + line.charAt(0);
		token.setLex(buffer + lex);
		token.setToken(Constants.ERR_TOKEN);
		buffer.setLength(0);
		line.deleteCharAt(0);

	}

}
