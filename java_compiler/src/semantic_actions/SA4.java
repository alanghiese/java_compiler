package semantic_actions;

import java.io.IOException;

import java_compiler.SymbolTable;
import utilities.Token;

public class SA4 extends SemanticAction{
	
	
		
		//consumes a char
		@Override
		public void execute(StringBuilder buffer, StringBuilder line, Token token, SymbolTable st) throws IOException {
			buffer.setLength(0);
			/*if (line.length()>0) {
				line.deleteCharAt(0);
			}*/
			//System.out.println("as4");
		}
}
