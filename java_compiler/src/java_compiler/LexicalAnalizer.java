package java_compiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import utilities.Constants;
import utilities.Decoder;
import utilities.Token;


public class LexicalAnalizer {
    BufferedReader fileReader;
    TransitionTable transitions= new TransitionTable();
    SymbolTable symbolTable = new SymbolTable();
    int currentLine=0;
    StringBuilder codeLine=new StringBuilder(0);
    
    public LexicalAnalizer(String path) {
        try {
            fileReader= new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LexicalAnalizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int yylex(){

        int count=0;
        Token token = new Token();
    	while (token.getToken() == -2) {
    		//System.out.println("entre");
	        int status = 0;
	        char nextChar;
	        StringBuilder readed= new StringBuilder();
	        
	        String lineReaded;
	        
	        if (codeLine!=null && codeLine.length()==0){
	            currentLine++;
	            try {
					lineReaded = fileReader.readLine();
					codeLine=(lineReaded==null?null:new StringBuilder(lineReaded));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	        }
	        
	        if(codeLine==null){
	            return Decoder.get("$");
	        }
	        //System.out.println(codeLine);
	        
	        if (codeLine.length()==0){
	            codeLine.append('\n');
	        }
	
	        nextChar= codeLine.charAt(0);
	        readed.append(nextChar);
	        codeLine.deleteCharAt(0);
	        count=0;
	        while (status != TransitionTable.FINAL_ST && status != -1 && count==0){
	        	
	            try {
	                transitions.getAction(status, nextChar).execute(readed,codeLine,token, symbolTable);
	            }
	            catch (IOException ex) {
	                System.err.println(ex.getMessage());
	            }
	                
	            status = transitions.getNextState(status, nextChar);
	            if (readed.length()>0)
	            	nextChar=readed.charAt(readed.length()-1);
	            else if (count==0 && status != 7){
	            	nextChar = '\n';
	            	count++;
	            }
	            
	           
	            /*System.out.println("-------");
	            System.out.println(nextChar);
	            System.out.println(status);
	            System.out.println(readed);
	            System.out.println(codeLine);
	            System.out.println("------");*/
	        }
    	}
    	
        if (token.getToken() == Constants.ERR_TOKEN || token.getInfo() != "")
        	System.out.println(token.getErr() + this.currentLine);
        else
            System.out.println(token.getInfo() + ": " + token.getLex());
            
        return token.getToken();
    }
    
}

