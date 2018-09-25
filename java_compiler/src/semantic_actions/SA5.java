package semantic_actions;

import java.io.IOException;

import java_compiler.SymbolTable;
import utilities.CTNInformation;
import utilities.Constants;
import utilities.Decoder;
import utilities.Token;

public class SA5 extends SemanticAction{
	
	
	//clear the buffer, get the token, save in symbol table and add the next symbol
		@Override
		public void execute(StringBuilder buffer, StringBuilder line, Token token, SymbolTable st) throws IOException {
			//super.execute(buffer, br); this SA doesn't move
			token.setToken(Decoder.get(Constants.STRING));
			buffer.deleteCharAt(0);
			buffer.deleteCharAt(buffer.length()-1);
			CTNInformation ctni = new CTNInformation();
			ctni.setType(Constants.STRING);
			st.addCTN(buffer.toString(), ctni);
			buffer.setLength(0);
			//System.out.println("as5");
		}
}
