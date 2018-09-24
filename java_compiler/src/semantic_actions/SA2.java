package semantic_actions;

import utilities.CTNInformation;
import utilities.Constants;
import utilities.Decoder;

import java.io.IOException;

import java_compiler.SymbolTable;

public class SA2 extends SemanticAction{
	
	//verify constants  and add the ctninformation in the symbol table
	@Override
	public void execute(StringBuilder buffer, StringBuilder line, Integer token, SymbolTable st) throws IOException {
		//super.execute(buffer, br);
		StringBuilder sAux = new StringBuilder();
		StringBuilder type = null; 
 
		if (buffer.charAt(buffer.length()-1) == 'i') {
			type = new StringBuilder("usinteger");
			for (int i = 0; i < buffer.length()-3; i++)
				sAux.append(buffer.charAt(i));
			Integer number = Integer.parseInt(sAux.toString());
			if (number < 0 && number > Constants.MAX_UN)
				sAux = new StringBuilder(Constants.MAX_UN);
				
				
		}
		else if (buffer.charAt(buffer.length()-1) == 'l') {
			type = new StringBuilder("usinteger");
			for (int i = 0; i < buffer.length()-2; i++)
				sAux.append(buffer.charAt(i));
			Double number = (double) Integer.parseInt(sAux.toString());
			
			if (number < 0 && number > Integer.MAX_VALUE+1)
				sAux = new StringBuilder(Integer.MAX_VALUE+1);;
		}
		
		Decoder.get("CTE");
		CTNInformation ctni = new CTNInformation();
		ctni.setType(type.toString());
		st.addCTN(sAux.toString(), ctni);
		
				
	}

}
