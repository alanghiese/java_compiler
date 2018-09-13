package java_compiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class LexicalAnalizer {
    BufferedReader fileBuffer;
    TransitionTable transitions= new TransitionTable();

    public LexicalAnalizer(String path) {
        try {
            fileBuffer= new BufferedReader(new FileReader(new File(path)));
        } catch (FileNotFoundException ex) {
            System.err.println("No se pudo leer el archivo");
        }
    }
    
    public int yylex(){
        int status=0;
        char nextChar;
        Integer token=null;
        
        StringBuilder readed= new StringBuilder();
        
        try {
            nextChar=(char) fileBuffer.read();
            readed.append(nextChar);
            
            while (status != TransitionTable.FINAL_ST){
            
            try {
                transitions.getAction(status, nextChar).execute(readed,fileBuffer,token);
            }
            catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
                
            status= transitions.getNextState(status, nextChar);
            nextChar=readed.charAt(readed.length()-1);
            }
        
        } catch (IOException ex) {
            System.err.println("No se pudo leer caracter");
        }
            
        return token;
    }
    
}

