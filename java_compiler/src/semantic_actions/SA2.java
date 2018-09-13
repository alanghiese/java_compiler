package semantic_actions;

import java.io.BufferedReader;
import utilities.Constants;
import java.io.IOException;

public class SA2 extends SemanticAction{
	
	//verify constants
	@Override
	public void execute(StringBuilder buffer, BufferedReader br, Integer token) throws IOException {
		//super.execute(buffer, br);
		if (buffer.charAt(buffer.length()-1) == 'i') {
			StringBuilder sAux = new StringBuilder();
			for (int i = 0; i < buffer.length()-3; i++)
				sAux.append(buffer.charAt(i));
			Integer number = Integer.parseInt(sAux.toString());
			double MAX_U = Math.pow(2, 16); //max unsigned integer
			if (number >= 0 && number <= MAX_U)
				return;
			else
				token = new Integer(Constants.ERR_TOKEN);
		}
		else if (buffer.charAt(buffer.length()-1) == 'l') {
			StringBuilder sAux = new StringBuilder();
			for (int i = 0; i < buffer.length()-2; i++)
				sAux.append(buffer.charAt(i));
			Double number = (double) Integer.parseInt(sAux.toString());
			
			if (number >= 0 && number <= Integer.MAX_VALUE+1)
				return;
			else
				token = new Integer(Constants.ERR_TOKEN);
		}
				
	}

}
