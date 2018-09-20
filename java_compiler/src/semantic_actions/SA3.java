package semantic_actions;

import java.io.IOException;

import java_compiler.SymbolTable;
import utilities.Decoder;

public class SA3  extends SemanticAction{

	//clear the buffer and get the token
	@Override
	public void execute(StringBuilder buffer, StringBuilder line, Integer token, SymbolTable st) throws IOException {
		//super.execute(buffer, br); this SA doesn't move
		token = Decoder.get(buffer.toString());
		buffer.setLength(0);
	}
}
