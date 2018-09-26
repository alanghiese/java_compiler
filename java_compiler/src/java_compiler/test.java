package java_compiler;

import java.io.IOException;

import utilities.Constants;

public class test {

	public static void main(String[] args) throws IOException {
		
		Parser p = new Parser(Constants.PATH + Constants.INPUT);
		System.out.println("Y YO LE DIGO QUE LE DICE: " + p.yyparse());

	}

}
