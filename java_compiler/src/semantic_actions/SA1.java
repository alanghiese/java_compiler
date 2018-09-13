package semantic_actions;

import java.io.BufferedReader;
import java.io.IOException;

public class SA1 extends SemanticAction{
	
	//clears the buffer
	@Override
	public void execute(StringBuilder buffer, BufferedReader br, Integer token) throws IOException {
		//super.execute(buffer, br); this SA doesn't move
		buffer = new StringBuilder();
		
	}
	

}
