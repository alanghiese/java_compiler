package java_compiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import utilities.Constants;
import utilities.FileManager;

public class test {
	
	
	
	public static void main(String[] args) throws IOException {
		/*FileManager fm = new FileManager();
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
		
		StringBuilder readed= new StringBuilder();
	    BufferedReader fileBuffer = null;
	    
	    try {
            fileBuffer= new BufferedReader(new FileReader(new File(Constants.PATH + Constants.INPUT)));
        } catch (FileNotFoundException ex) {
            System.err.println("No se pudo leer el archivo");
        }
		
		char nextChar=(char) fileBuffer.read();
        readed.append(nextChar);
        
        System.out.println(readed);
	}
	
	
}
