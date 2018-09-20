package java_compiler;


import java.io.IOException;
import java.util.Set;

import utilities.Constants;
import utilities.FileManager;

public class test {
	
	
	
	public static void main(String[] args) throws IOException {
		FileManager fm = new FileManager(Constants.PATH + Constants.INPUT);
		StringBuilder code = fm.getLine();
		while (!code.toString().equals("$")) {
			System.out.println(code);
			code = fm.getLine();
		}
		System.out.println(code);
		
		//FileManager.writeFile(Constants.PATH + Constants.OUTPUT, code);
		
	
		SymbolTable st = new SymbolTable();
		Set<String> symbols = st.getAll();
		/*for (String s: symbols) {
			System.out.println("simbolo: " + s + " , codigo: " + st.getToken(s));
		}*/
	
		
		StringBuilder s = new StringBuilder("Hola");
		System.out.println(s);
		s.setLength(0);
		s.append("asd");
		System.out.println(s);
		
	}
	
	
}
