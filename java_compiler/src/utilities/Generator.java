package utilities;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import codigo_intermedio.Triples;
import java_compiler.LexicalAnalizer;

public class Generator {

	public Generator() {
		// TODO Auto-generated constructor stub
	}
	
	public String genTriples(List<Triples> lt) {
		StringBuilder list = new StringBuilder();
		for( Triples t:lt ) {
			list.append(t.getId() +". "+t.toString() + '\n');
		}
		return list.toString();
		
	}
	
	
	public void genAssembler(List<Triples> lt, List<String> commonCode, List<String> functionCode) {
		/*for( Triples t:lt ) {
			System.out.println(t.getId() +". "+'\n'+t.generateAssembler());		
		}*/
		for( Triples t:lt ) {
			if (t.isFunction)
				functionCode.add(t.generateAssembler());
			else
				commonCode.add(t.generateAssembler());
		}
	}
	
	
	public String genVariables() {
		StringBuilder solution = new StringBuilder();
		Set<String> vars = LexicalAnalizer.symbolTable.getAll();
		for(String st : vars) {
			if (LexicalAnalizer.symbolTable.getLexeme(st).getType().equals(Constants.STRING))
				solution.append(st.replace(" ", "_") + LexicalAnalizer.symbolTable.getLexeme(st).getCode() + " \"" + st + "\"" + '\n');
			else
				solution.append(st+ LexicalAnalizer.symbolTable.getLexeme(st).getCode() + '\n');
			
		}
		solution.deleteCharAt(solution.length()-1);
		return solution.toString();
	}
	
	public void genOutput(List<String> vars,List<String> funcs,List<String> lines, String path) {
		
		List<String> code = new ArrayList<>();
		code.add(".MODEL small");
		code.add(".STACK 200h");
		code.add(".DATA");
		code.addAll(vars);
		code.add(".CODE");
		code.addAll(funcs);
		code.add("START");
		code.addAll(lines);
		code.add("END START");
		
		
		Path file = Paths.get(path);
		try {
			Files.write(file, code, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
