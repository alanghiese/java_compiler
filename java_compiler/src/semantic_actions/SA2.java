package semantic_actions;

import java.io.BufferedReader;
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
			Integer integer = Integer.parseInt(sAux.toString());
			double MAX_U = Math.pow(2, 16); //max unsigned integer
			if (integer >= 0 && integer <= MAX_U)
				return;
			else
				token = new Integer(-1);
		}
				
	}

}
