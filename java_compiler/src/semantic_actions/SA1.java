package semantic_actions;

import java.io.IOException;

import java_compiler.SymbolTable;
import utilities.Constants;
import utilities.Decoder;
import utilities.IDInformation;
import utilities.Token;

public class SA1 extends SemanticAction{
	final int maxChars = 25;
	
	//clears the buffer and add the id in the symbol table 
	@Override
	public void execute(StringBuilder buffer, StringBuilder line, Token token, SymbolTable st) throws IOException {
		char charcin = buffer.charAt(buffer.length()-1);
		StringBuilder c = new StringBuilder();
		c.append(charcin);
		buffer.deleteCharAt(buffer.length()-1);
		if (buffer.length() <= maxChars) {
			token.setToken(Decoder.get(Constants.ID));
                        token.setInfo("ID");
                        token.setLex(buffer.toString());
			st.addID(buffer.toString(), new IDInformation());
		}
		else {
			token.setErr("ERROR: Nombre de variable demasiado largo. Linea: ");
			token.setToken(Constants.ERR_TOKEN);
		}
		buffer.setLength(0);

		c.append(line);
		line.setLength(0);
		line.append(c);
		//System.out.println("as1");
			
		
		
	}
	

}
