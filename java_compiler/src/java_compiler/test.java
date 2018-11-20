package java_compiler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import java.io.IOException;

import utilities.Constants;
import utilities.Generator;

public class test {
	

	public static void main(String[] args) throws IOException {
		if(args.length==4) {
			long startTime = System.currentTimeMillis();
			Parser p = new Parser(args[0]);
			List<String> commonCode = new LinkedList<>();
			List<String> functionCode = new LinkedList<>();
			//p.yydebug = true;
			p.yyparse();
			if (!Parser.macrigato) {
				Generator gen = new Generator();
				gen.genAssembler(p.getTriples(),commonCode,functionCode);
				p.getTable(args[1]);
				gen.genTriplesFile(args[3], p.getTriples());
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
