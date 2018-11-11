package java_compiler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import java.io.IOException;

import codigo_intermedio.Triples;
import utilities.Constants;
import utilities.Generator;

public class test {
	

	public static void main(String[] args) throws IOException {
		if(args.length>1) {
			long startTime = System.currentTimeMillis();
			Parser p = new Parser(args[0]);
			List<String> commonCode = new LinkedList<>();
			List<String> functionCode = new LinkedList<>();
			//p.yydebug = true;
			p.yyparse();
			if (!p.macrigato) {
				Generator gen = new Generator();
				
				//show triples
				//System.out.println(gen.genTriples(p.getTriples()));
				//generate assembler code and divide it in two lists
				gen.genAssembler(p.getTriples(),commonCode,functionCode);
				
				
				//show the assembler code
				//functions
				/*System.out.println("ZONA DE FUNCIONES" + '\n');
				for(String s: functionCode) {
					System.out.println(s);
				}*/
				//!functions
				/*System.out.println('\n' +"LO DEMAS :)" + '\n');
				for(String s: commonCode) {
					System.out.println(s);
				}*/
	
				p.getTable(args[1]);
	
	
				//System.out.println('\n' + "VARIABLES" + '\n');
				//show variables
				//System.out.println(gen.genVariables());
				List<String> vars = new ArrayList<>();
				vars.add(gen.genVariables());
				gen.genOutput(vars, functionCode, commonCode, args[2]);
				long endTime   = System.currentTimeMillis();
				long totalTime = endTime - startTime;
				System.out.println("Compilacion exitosa, tiempo: " + (float)totalTime/1000 + " segundos");
			}
			else {
				long endTime   = System.currentTimeMillis();
				long totalTime = endTime - startTime;
				System.out.println("Compilacion para nada exitosa, tiempo: " + (float)totalTime/1000 + " segundos");
			}
			
		}
		else {
			System.out.println(Constants.ERROR_ARG);
		}
		
		
		
		
	}

}
