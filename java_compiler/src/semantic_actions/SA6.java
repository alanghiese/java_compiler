package semantic_actions;

import java_compiler.ParserVal;
import java_compiler.SymbolTable;
import utilities.Constants;
import utilities.Decoder;
import utilities.Token;

public class SA6 implements SemanticAction {

	@Override
	public void execute(StringBuilder buffer, StringBuilder line, Token token, SymbolTable st, ParserVal yylval) {
		buffer.append(line.charAt(0));
		line.deleteCharAt(0);
		token.setToken(Decoder.get(buffer.toString()));
		token.setLex(buffer.toString());
		token.setMsg(Constants.SYMBOL);

		//yylval = new ParserVal();
		//yylval = new ParserVal(new String(buffer.toString()),token);
		yylval.obj = token;
		yylval.sval = new String(buffer.toString());

		buffer.setLength(0);
	}

}
