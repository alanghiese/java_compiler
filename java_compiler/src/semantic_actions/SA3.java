package semantic_actions;

import java.io.IOException;

import java_compiler.SymbolTable;
import utilities.Constants;
import utilities.Decoder;
import utilities.Token;

public class SA3  extends SemanticAction{

	
	//clear the buffer and get the token
	@Override
	public void execute(StringBuilder buffer, StringBuilder line, Token token, SymbolTable st) throws IOException {
		StringBuilder c = new StringBuilder();
		//super.execute(buffer, line, token, st);
		char charcin = buffer.charAt(buffer.length()-1);
		c.append(charcin);
		buffer.deleteCharAt(buffer.length()-1);
		token.setToken(Decoder.get(buffer.toString()));
		/*System.out.println("en SA3");
		System.out.println(buffer);
		System.out.println(token);*/
		buffer.setLength(0);
		c.append(line);
		line.setLength(0);
		line.append(c);
		//System.out.println("as3");
	}
}
