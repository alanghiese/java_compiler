package semantic_actions;

import java_compiler.ParserVal;
import java_compiler.SymbolTable;
import utilities.Constants;
import utilities.Decoder;
import utilities.Token;

public class SA3 implements SemanticAction {

	@Override
	public void execute(StringBuilder buffer, StringBuilder line, Token token, SymbolTable st, ParserVal yylval) {

		token.setToken(Decoder.get(buffer.toString()));

		if (token.getToken() == Constants.ERR_TOKEN)
			token.setMsg("ERROR LEXICO: Lexema no reconocido");
		else if (token.getToken() <= 260)
			token.setMsg(Constants.SYMBOL);
		else
			token.setMsg(Constants.RES_WORD);

		//yylval = new ParserVal(new String(buffer.toString()),token);
		//yylval = new ParserVal();
		yylval.obj = token;

		token.setLex(buffer.toString());

	}

}
