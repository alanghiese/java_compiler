package semantic_actions;

import java_compiler.ParserVal;
import java_compiler.SymbolTable;
import utilities.CTNInformation;
import utilities.Constants;
import utilities.Decoder;
import utilities.Token;

public class SA5 implements SemanticAction {

	@Override
	public void execute(StringBuilder buffer, StringBuilder line, Token token, SymbolTable st, ParserVal yylval) {
		line.deleteCharAt(0);
		token.setToken(Decoder.get(Constants.STRING));
		token.setLex(buffer.toString());
		token.setMsg(Constants.STRING);
		CTNInformation ctni = new CTNInformation();
		ctni.setType(Constants.STRING);
		st.addCTN(buffer.toString(), ctni);
	}

}
