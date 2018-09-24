package semantic_actions;

import java.io.IOException;

import java_compiler.SymbolTable;
import utilities.Constants;
import utilities.IDInformation;

public class SA1 extends SA3{
	
	//clears the buffer and add the id in the symbol table 
	@Override
	public void execute(StringBuilder buffer, StringBuilder line, Integer token, SymbolTable st) throws IOException {
		if (buffer.length()<=25) {
			super.execute(new StringBuilder("ID"), line, token, st); 
			st.addID(buffer.toString(), new IDInformation());
		}
		else {
			token = new Integer(Constants.ERR_TOKEN);
			buffer.setLength(0);
		}
			
		
		
	}
	

}
