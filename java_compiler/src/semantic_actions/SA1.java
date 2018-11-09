package semantic_actions;

import java_compiler.ParserVal;
import java_compiler.SymbolTable;
import utilities.Constants;
import utilities.Decoder;
import utilities.IDInformation;
import utilities.Token;

public class SA1 implements SemanticAction {

	@Override
	public void execute(StringBuilder buffer, StringBuilder line, Token token, SymbolTable st, ParserVal yylval) {

		if (buffer.length() > Constants.SIZE_MAX_ID) {
			System.out.println("-----------------" + "WARNING LEXICO: ID muy largo");
			buffer.setLength(Constants.SIZE_MAX_ID);
			
		}
			
			
			
		
		token.setToken(Decoder.get(Constants.ID));
		token.setMsg(Constants.ID);
		token.setLex(buffer.toString());
		IDInformation idi = new IDInformation();
		st.addID(buffer.toString(), idi);
		//yylval = new ParserVal(new String(buffer.toString()),token);
		yylval.obj = token;
		yylval.sval = new String(buffer.toString());
		
	}

}
