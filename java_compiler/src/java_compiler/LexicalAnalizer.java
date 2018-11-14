package java_compiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;



import utilities.Constants;
import utilities.Token;

public class LexicalAnalizer {
	BufferedReader fileReader;
	TransitionTable transitions = new TransitionTable();
	static public SymbolTable symbolTable = new SymbolTable();
	static public boolean FINISH = false;
	int currentLine = 0;
	StringBuilder codeLine = new StringBuilder(0);
	//ParserVal yylval;

	public LexicalAnalizer(String path) {
		//this.yylval = yylval;
		try {
			fileReader = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException ex) {
			Logger.getLogger(LexicalAnalizer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public int yylex(AtomicReference<ParserVal> reference) {
		ParserVal yylval = new ParserVal();
		//((Token)yylval.obj).setLine(2);
		int status = 0;
		Token token = new Token();

		
		while ((token.getToken() == Constants.DEFAULT_TOKEN || token.getToken() == Constants.ERR_TOKEN) && !FINISH) {

			char nextChar = ' ';
			StringBuilder buffer = new StringBuilder();
			String lineReaded;
					
			if (codeLine != null && codeLine.length() == 0) {

				try {
					lineReaded = fileReader.readLine();
					codeLine = (lineReaded == null ? null : new StringBuilder(lineReaded));
					if (codeLine != null) {
						codeLine.append('\n');
						this.currentLine++;
					} else {
						codeLine = new StringBuilder(Constants.EOF);
						FINISH = true;
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			token.setLine(currentLine);
			while (status != TransitionTable.FINAL_ST && codeLine.length() != 0) {

				nextChar = codeLine.charAt(0);
				transitions.getAction(status, nextChar).execute(buffer, codeLine, token, symbolTable, yylval);
				
				status = transitions.getNextState(status, nextChar);
				
				
				if (token.getToken()==Constants.ERR_TOKEN) {
					token.setLine(this.currentLine);
					System.out.println("-----------------" + token.getMsg());
					
					token = new Token();
					status = 0;
					Parser.macrigato = true;
					
				
				}
				
				

			}
			//System.out.println(codeLine);
			if (token.getToken() != Constants.DEFAULT_TOKEN) {
				token.setLine(this.currentLine);
				//System.out.println("-----------------" + token.getMsg());
			};
			
			if (token.getToken()==Constants.ERR_TOKEN) {
				//System.out.println("-----------------" + token.getMsg());
				token = new Token();
				status = 0;
				Parser.macrigato = true;
				
			}
			

		}
		
		reference.set(yylval);
		//System.out.println(token.getLex() + " " + ((Token)yylval.obj).getLex());
		
		return token.getToken();
	}

}
