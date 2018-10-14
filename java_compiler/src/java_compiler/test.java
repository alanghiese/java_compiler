package java_compiler;

import java.io.IOException;
import java.util.ArrayList;

import utilities.Constants;

public class test {

	public static void main(String[] args) throws IOException {
		if(args.length>1) {
			Parser p = new Parser(args[0]);
			//p.yydebug = true;
			p.yyparse();
			p.getTable(args[1]);
		}
		else {
			System.out.println(Constants.ERROR_ARG);
		}
		

	}

}
