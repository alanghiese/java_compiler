package java_compiler;


import java.io.IOException;
import java.util.Set;

import utilities.Constants;
import utilities.FileManager;

public class test {
	
	
	
	public static void main(String[] args) throws IOException {
		LexicalAnalizer la = new LexicalAnalizer(Constants.PATH + Constants.INPUT);
		System.out.println("MATEEEEEEEE");
		Integer i = new Integer(-1);
		do {
			i = la.yylex();
		}
		while (i != 36);
	}
	
	
}
