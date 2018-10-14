package java_compiler;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import utilities.Constants;
import utilities.IDInformation;

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
		
		LinkedList<Integer> l=new LinkedList<Integer>();
		l.getLast();
		
	}

}
