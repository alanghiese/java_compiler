package semantic_actions;

import java.io.IOException;

import java_compiler.SymbolTable;
import utilities.IDInformation;

public class SA1 extends SA3{
	
	//clears the buffer
	@Override
	public void execute(StringBuilder buffer, StringBuilder line, Integer token, SymbolTable st) throws IOException {
		super.execute(new StringBuilder("ID"), line, token, st); 
		st.addID(buffer.toString(), new IDInformation(token));
		
		
	}
	

}
