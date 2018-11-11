package java_compiler;

import java.util.List;

import java.io.IOException;

import codigo_intermedio.Triples;
import utilities.Constants;

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
	
	public String showAssembler(List<Triples> lt) {
		String list =  new String();
		int cont = 0;
		for( Triples t:lt ) {
			System.out.println(t.getId() +". "+'\n'+t.generateAssembler());
			list = cont + "." + t.generateAssembler() + '\n';
			
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
			t.showAssembler(p.getTriples());
		}
		else {
			System.out.println(Constants.ERROR_ARG);
		}
		
		
		
		
	}

}
