package semantic_actions;

import java.io.IOException;

import java_compiler.SymbolTable;
import utilities.Decoder;

public class SA4 extends SemanticAction{
	
		//consumes a char
		@Override
		public void execute(StringBuilder buffer, StringBuilder line, Integer token, SymbolTable st) throws IOException {
			line.deleteCharAt(0);			
		}
}
