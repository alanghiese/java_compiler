package java_compiler;

import java.util.List;
import java.util.Stack;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import codigo_intermedio.Triples;
import utilities.Constants;
import utilities.IDInformation;

public class test {
	
	
	public String showTriples(List<Triples> lt) {
		String list =  new String();
		int cont = 0;
		for( Triples t:lt ) {
			list = cont + "." + t.toString() + '\n';
			cont++;
			
		}
		return list;
		
	}

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
		LinkedList<Integer> l = new LinkedList<>();
		
		
	}

}
