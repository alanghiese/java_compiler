package java_compiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.Decoder;


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
        int status=0;
        char nextChar;
        Integer token=null;
        StringBuilder readed= new StringBuilder();
        
        String lineReaded;
        
        if (codeLine!=null && codeLine.length()==0){
            currentLine++;
            String lineReaded = fileReader.readLine();
            codeLine=(lineReaded==null?null:new StringBuilder(lineReaded));
        }
        
        if(codeLine==null){
            return Decoder.get("$");
        }
        
        if (codeLine.length()==0){
            codeLine.append('\n');
        }
        
        nextChar= codeLine.charAt(0);
        readed.append(nextChar);
        
        while (status != TransitionTable.FINAL_ST){
            
            try {
                transitions.getAction(status, nextChar).execute(readed,codeLine,token, symbolTable);
            }
            catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
                
            status= transitions.getNextState(status, nextChar);
            nextChar=readed.charAt(readed.length()-1);
        }
            
        return token;
    }
    
}

