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
			else if (!LexicalAnalizer.symbolTable.isFunction(st) 
					&& !LexicalAnalizer.symbolTable.isVar(st)
					&& !LexicalAnalizer.symbolTable.isAuxiliary(st)) {
				String withoutSuffix = this.extractSubffix(st);
				String withoutSymbol = this.changeNegSymbol(st);
				//System.out.println(withoutSuffix);
				solution.append("_"+withoutSymbol+LexicalAnalizer.symbolTable.getLexeme(st).getCode() + " " + withoutSuffix + '\n');	
			}
			else
				solution.append(st+ LexicalAnalizer.symbolTable.getLexeme(st).getCode() + " ?" + '\n');
			
		}
		solution.deleteCharAt(solution.length()-1);
		return solution.toString();
	}
	
	private String changeNegSymbol(String name) {
		String s = name.replace("-", "n");
		return s;
	}
	
	
	private String extractSubffix(String name) {
		StringBuilder withoutSuffix = new StringBuilder(); 
		int limit;
		if (name.charAt(name.length()-1)=='l')
			limit = 2;
		else
			limit = 3;
		for (int i = 0; i < name.length() - limit; i++)
			withoutSuffix.append(name.charAt(i));
		return withoutSuffix.toString();
	}
	
	public void genOutput(List<String> vars,List<String> funcs,List<String> lines, String path) {
		
		List<String> code = new ArrayList<>();
		code.add(".386");
		code.add(".MODEL flat,stdcall");
		code.add(".STACK 200h");
		code.add("option casemap :none");
		code.add("include C:\\masm32\\include\\windows.inc \n" + 
				"include C:\\masm32\\include\\kernel32.inc \n" + 
				"include C:\\masm32\\include\\user32.inc \n" + 
				"include C:\\masm32\\include\\masm32.inc \n" + 
				"includelib C:\\masm32\\lib\\kernel32.lib \n" + 
				"includelib C:\\masm32\\lib\\user32.lib \n" + 
				"includelib C:\\masm32\\lib\\masm32.lib");
		code.add(".DATA");
		code.add("error_zero db \"division por zero\"");
		code.add("error_neg db \"usinteger negativo\"");
		code.add("error_ovflw db \"overflow en el producto\"");
		code.addAll(vars);
		code.add(".CODE");
		code.addAll(funcs);
		code.add("START:");
		code.addAll(lines);
		code.add("JMP exc_end");
		code.add("MULOVFLW:");
		code.add("invoke MessageBox, NULL, addr  error_ovflw, addr   error_zero, MB_OK");
		code.add("invoke ExitProcess, 1");
		code.add("DIVZERO:");
		code.add("invoke MessageBox, NULL, addr  error_zero, addr   error_zero, MB_OK");
		code.add("invoke ExitProcess, 1");
		code.add("NEGSUB:");
		code.add("invoke MessageBox, NULL, addr  error_neg, addr   error_zero, MB_OK");
		code.add("invoke ExitProcess, 1");
		code.add("exc_end:");
		code.add("invoke ExitProcess, 0");
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
