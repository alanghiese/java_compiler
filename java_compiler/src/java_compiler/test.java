package java_compiler;

import java.util.ArrayList;
import java.util.Set;

public class test {
	
	
	
	public static void main(String[] args) {
		FileManager fm = new FileManager();
		ArrayList<String> code = fm.loadAndGetSourceCode(Constants.PATH + Constants.INPUT);
		for(String line: code) {
			System.out.println(line);
		}
		
		fm.writeFile(Constants.PATH + Constants.OUTPUT, code);
		
	
		SymbolTable st = new SymbolTable();
		Set<String> symbols = st.getAll();
		/*for (String s: symbols) {
			System.out.println("simbolo: " + s + " , codigo: " + st.getToken(s));
		}*/
	
		
		TransitionTable tt = new TransitionTable();
	}
	
	
}
