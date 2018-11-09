package java_compiler;

import java.util.List;

import java.util.Stack;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import codigo_intermedio.Triples;
import utilities.Constants;
import utilities.Token;
import utilities.IDInformation;

public class test {
	
	
	public String showTriples(List<Triples> lt) {
		String list =  new String();
		int cont = 0;
		for( Triples t:lt ) {
			System.out.println(t.getId() +". "+t.toString());
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
			test t = new test();
			t.showTriples(p.getTriples());
		}
		else {
			System.out.println(Constants.ERROR_ARG);
		}
		
		
		
		
	}

}
