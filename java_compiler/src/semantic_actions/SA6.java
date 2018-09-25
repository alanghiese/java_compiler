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
		buffer.setLength(0);
	}

}
