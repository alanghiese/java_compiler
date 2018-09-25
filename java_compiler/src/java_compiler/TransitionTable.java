package java_compiler;
import semantic_actions.*;
import utilities.Constants;

public class TransitionTable {
	
	// Row => State - Column => Symbol from input 
	// Symbol Index :
	//                  '_' -> 0    '::letter::' -> 1   'u' -> 2    'i' -> 3
	//                  'l' -> 4    '::digit:: -> 5     '+' -> 6    '-' -> 7
        //                  '*' -> 8    '/' -> 9            ':' -> 10   '=' -> 11
        //                  '<' -> 12   '>' -> 13           '!' -> 14   '#' -> 15
        //                  '\' -> 16   '(' -> 17           ')' -> 18   '{' -> 19
        //                  '}' -> 20   ',' -> 21           ';' -> 22   '$' -> 23
	
	static final int NUM_COL = 27;
	static final int NUM_ROW = 14;
	static final int FINAL_ST = NUM_ROW;
	
	
	private int [][] mte;
	private SemanticAction [][] mas;

	public TransitionTable(){
	
		mte = new int[NUM_ROW][NUM_COL];
                mas = new SemanticAction[NUM_ROW][NUM_COL];
		
		// Fila 0 matriz transicion estados
		
		mte[0][0] = 1; mte[0][1] = 6; mte[0][2] = 6; mte[0][3] = 6; mte[0][4] = 6; mte[0][5] = 3;
		mte[0][6] = FINAL_ST; mte[0][7] = FINAL_ST; mte[0][8] = FINAL_ST; mte[0][9] = FINAL_ST; mte[0][10] = 12; mte[0][11] = FINAL_ST;
		mte[0][12] = 10; mte[0][13] = 8; mte[0][14] = 11; mte[0][15] = 7; mte[0][16] = 13; mte[0][17] = FINAL_ST;
		mte[0][18] = FINAL_ST; mte[0][19] = FINAL_ST; mte[0][20] = FINAL_ST; mte[0][21] = FINAL_ST; mte[0][22] = FINAL_ST; mte[0][23] = 0;
		mte[0][24] = 0; mte[0][25] = Constants.ERR_TOKEN; mte[0][26]= 0;
		
		// Fila 1 matriz transicion estados
		
		mte[1][0] = Constants.ERR_TOKEN; mte[1][1] = 2; mte[1][2] = 2; mte[1][3] = 2; mte[1][4] = 2; mte[1][5] = 2;
		mte[1][6] = Constants.ERR_TOKEN; mte[1][7] = Constants.ERR_TOKEN; mte[1][8] = Constants.ERR_TOKEN; mte[1][9] = Constants.ERR_TOKEN; mte[1][10] = Constants.ERR_TOKEN; mte[1][11] = Constants.ERR_TOKEN;
		mte[1][12] = Constants.ERR_TOKEN; mte[1][13] = Constants.ERR_TOKEN; mte[1][14] = Constants.ERR_TOKEN; mte[1][15] = Constants.ERR_TOKEN; mte[1][16] = Constants.ERR_TOKEN; mte[1][17] = Constants.ERR_TOKEN;
		mte[1][18] = Constants.ERR_TOKEN; mte[1][19] = Constants.ERR_TOKEN; mte[1][20] = Constants.ERR_TOKEN; mte[1][21] = Constants.ERR_TOKEN; mte[1][22] = Constants.ERR_TOKEN; mte[1][23] = Constants.ERR_TOKEN;
		mte[1][24] = Constants.ERR_TOKEN; mte[1][25] = Constants.ERR_TOKEN; mte[1][26]= Constants.ERR_TOKEN;
		
		// Fila 2 matriz transicion estados
		
		mte[2][0] = Constants.ERR_TOKEN; mte[2][1] = 2; mte[2][2] = 2; 	mte[2][3] = 2; 	mte[2][4] = 2; 	mte[2][5] = 2;
		mte[2][6] = FINAL_ST; mte[2][7] = FINAL_ST; mte[2][8] = FINAL_ST; mte[2][9] = FINAL_ST; mte[2][10] = FINAL_ST; mte[2][11] = FINAL_ST;
		mte[2][12] = FINAL_ST; mte[2][13] = FINAL_ST; mte[2][14] = FINAL_ST; mte[2][15] = FINAL_ST; mte[2][16] = FINAL_ST; mte[2][17] = FINAL_ST;
		mte[2][18] = FINAL_ST; mte[2][19] = FINAL_ST; mte[2][20] = FINAL_ST; mte[2][21] = FINAL_ST; mte[2][22] = FINAL_ST; mte[2][23] = FINAL_ST;
		mte[2][24] = FINAL_ST; mte[2][25] = FINAL_ST; mte[2][26]= FINAL_ST;
		
		// Fila 3 matriz transicion estados
		
		mte[3][0] = 4; mte[3][1] = Constants.ERR_TOKEN; mte[3][2] = Constants.ERR_TOKEN; mte[3][3] = Constants.ERR_TOKEN; mte[3][4] = Constants.ERR_TOKEN; mte[3][5] = 3;
		mte[3][6] = Constants.ERR_TOKEN; mte[3][7] = Constants.ERR_TOKEN; mte[3][8] = Constants.ERR_TOKEN; mte[3][9] = Constants.ERR_TOKEN; mte[3][10] = Constants.ERR_TOKEN; mte[3][11] = Constants.ERR_TOKEN;
		mte[3][12] = Constants.ERR_TOKEN; mte[3][13] = Constants.ERR_TOKEN; mte[3][14] = Constants.ERR_TOKEN; mte[3][15] = Constants.ERR_TOKEN; mte[3][16] = Constants.ERR_TOKEN; mte[3][17] = Constants.ERR_TOKEN;
		mte[3][18] = Constants.ERR_TOKEN; mte[3][19] = Constants.ERR_TOKEN; mte[3][20] = Constants.ERR_TOKEN; mte[3][21] = Constants.ERR_TOKEN; mte[3][22] = Constants.ERR_TOKEN; mte[3][23] = Constants.ERR_TOKEN;
		mte[3][24] = Constants.ERR_TOKEN; mte[3][25] = Constants.ERR_TOKEN; mte[3][26]= Constants.ERR_TOKEN;

		// Fila 4 matriz transicion estados
		
		mte[4][0] = Constants.ERR_TOKEN; mte[4][1] = Constants.ERR_TOKEN; mte[4][2] = 5; mte[4][3] = Constants.ERR_TOKEN; mte[4][4] = FINAL_ST; mte[4][5] = Constants.ERR_TOKEN;
		mte[4][6] = Constants.ERR_TOKEN; mte[4][7] = Constants.ERR_TOKEN; mte[4][8] = Constants.ERR_TOKEN; mte[4][9] = Constants.ERR_TOKEN; mte[4][10] = Constants.ERR_TOKEN; mte[4][11] = Constants.ERR_TOKEN;
		mte[4][12] = Constants.ERR_TOKEN; mte[4][13] = Constants.ERR_TOKEN; mte[4][14] = Constants.ERR_TOKEN; mte[4][15] = Constants.ERR_TOKEN; mte[4][16] = Constants.ERR_TOKEN; mte[4][17] = Constants.ERR_TOKEN;
		mte[4][18] = Constants.ERR_TOKEN; mte[4][19] = Constants.ERR_TOKEN; mte[4][20] = Constants.ERR_TOKEN; mte[4][21] = Constants.ERR_TOKEN; mte[4][22] = Constants.ERR_TOKEN; mte[4][23] = Constants.ERR_TOKEN;
		mte[4][24] = Constants.ERR_TOKEN; mte[4][25] = Constants.ERR_TOKEN; mte[4][26]= Constants.ERR_TOKEN;
		
		// Fila 5 matriz transicion estados
		
		mte[5][0] = Constants.ERR_TOKEN; mte[5][1] = Constants.ERR_TOKEN; mte[5][2] = Constants.ERR_TOKEN; mte[5][3] = FINAL_ST; 	mte[5][4] = Constants.ERR_TOKEN; mte[5][5] = Constants.ERR_TOKEN;
		mte[5][6] = Constants.ERR_TOKEN; mte[5][7] = Constants.ERR_TOKEN; mte[5][8] = Constants.ERR_TOKEN; mte[5][9] = Constants.ERR_TOKEN; mte[5][10] = Constants.ERR_TOKEN; mte[5][11] = Constants.ERR_TOKEN;
		mte[5][12] = Constants.ERR_TOKEN; mte[5][13] = Constants.ERR_TOKEN; mte[5][14] = Constants.ERR_TOKEN; mte[5][15] = Constants.ERR_TOKEN; mte[5][16] = Constants.ERR_TOKEN; mte[5][17] = Constants.ERR_TOKEN;
		mte[5][18] = Constants.ERR_TOKEN; mte[5][19] = Constants.ERR_TOKEN; mte[5][20] = Constants.ERR_TOKEN; mte[5][21] = Constants.ERR_TOKEN; mte[5][22] = Constants.ERR_TOKEN; mte[5][23] = Constants.ERR_TOKEN;
		mte[5][24] = Constants.ERR_TOKEN; mte[5][25] = Constants.ERR_TOKEN; mte[5][26]= Constants.ERR_TOKEN;
		
		// Fila 6 matriz transicion estados
		
		mte[6][0] = FINAL_ST; mte[6][1] = 6; mte[6][2] = 6; mte[6][3] = 6; mte[6][4] = 6; mte[6][5] = FINAL_ST;
		mte[6][6] = FINAL_ST; mte[6][7] = FINAL_ST; mte[6][8] = FINAL_ST; mte[6][9] = FINAL_ST; mte[6][10] = FINAL_ST; mte[6][11] = FINAL_ST;
		mte[6][12] = FINAL_ST; mte[6][13] = FINAL_ST; mte[6][14] = FINAL_ST; mte[6][15] = FINAL_ST; mte[6][16] = FINAL_ST; mte[6][17] = FINAL_ST;
		mte[6][18] = FINAL_ST; mte[6][19] = FINAL_ST; mte[6][20] = FINAL_ST; mte[6][21] = FINAL_ST; mte[6][22] = FINAL_ST; mte[6][23] = FINAL_ST;
		mte[6][24] = FINAL_ST; mte[6][25] = FINAL_ST; mte[6][26]= FINAL_ST;
						
		// Fila 7 matriz transicion estados
	
		mte[7][0] = 7; mte[7][1] = 7; mte[7][2] = 7; mte[7][3] = 7; mte[7][4] = 7; mte[7][5] = 7;
		mte[7][6] = 7;	mte[7][7] = 7; mte[7][8] = 7; mte[7][9] = 7; mte[7][10] = 7; mte[7][11] = 7;
		mte[7][12] = 7; mte[7][13] = 7; mte[7][14] = 7; mte[7][15] = 0; mte[7][16] = 7; mte[7][17] = 7;
		mte[7][18] = 7; mte[7][19] = 7; mte[7][20] = 7; mte[7][21] = 7; mte[7][22] = 7; mte[7][23] = 7;
		mte[7][24] = 7; mte[7][25] = 7; mte[7][26]= 7;
		
		// Fila 8 matriz transicion estados
		
		mte[8][0] = FINAL_ST; mte[8][1] = FINAL_ST; mte[8][2] = FINAL_ST; mte[8][3] = FINAL_ST; mte[8][4] = FINAL_ST; mte[8][5] = FINAL_ST;
		mte[8][6] = FINAL_ST; mte[8][7] = FINAL_ST; mte[8][8] = FINAL_ST; mte[8][9] = FINAL_ST; mte[8][10] = FINAL_ST; mte[8][11] = 9;
		mte[8][12] = FINAL_ST; mte[8][13] = FINAL_ST; mte[8][14] = FINAL_ST; mte[8][15] = FINAL_ST; mte[8][16] = FINAL_ST; mte[8][17] = FINAL_ST;
		mte[8][18] = FINAL_ST; mte[8][19] = FINAL_ST; mte[8][20] = FINAL_ST; mte[8][21] = FINAL_ST; mte[8][22] = FINAL_ST; mte[8][23] = FINAL_ST;
		mte[8][24] = FINAL_ST; mte[8][25] = FINAL_ST; mte[8][26]= FINAL_ST;

		// Fila 9 matriz transicion estados
		
		mte[9][0] = FINAL_ST; mte[9][1] = FINAL_ST; mte[9][2] = FINAL_ST; mte[9][3] = FINAL_ST; mte[9][4] = FINAL_ST; mte[9][5] = FINAL_ST;
		mte[9][6] = FINAL_ST; mte[9][7] = FINAL_ST; mte[9][8] = FINAL_ST; mte[9][9] = FINAL_ST; mte[9][10] = FINAL_ST; mte[9][11] = FINAL_ST;
		mte[9][12] = FINAL_ST; mte[9][13] = FINAL_ST; mte[9][14] = FINAL_ST; mte[9][15] = FINAL_ST; mte[9][16] = FINAL_ST; mte[9][17] = FINAL_ST;
		mte[9][18] = FINAL_ST; mte[9][19] = FINAL_ST; mte[9][20] = FINAL_ST; mte[9][21] = FINAL_ST; mte[9][22] = FINAL_ST; mte[9][23] = FINAL_ST;
		mte[9][24] = FINAL_ST; mte[9][25] = FINAL_ST; mte[9][26]= FINAL_ST;
	
		// Fila 10 matriz transicion estados
		
		mte[10][0] = FINAL_ST; mte[10][1] = FINAL_ST; mte[10][2] = FINAL_ST; mte[10][3] = FINAL_ST; mte[10][4] = FINAL_ST; mte[10][5] = FINAL_ST;
		mte[10][6] = FINAL_ST; mte[10][7] = FINAL_ST; mte[10][8] = FINAL_ST; mte[10][9] = FINAL_ST; mte[10][10] = FINAL_ST; mte[10][11] = 9;
		mte[10][12] = FINAL_ST; mte[10][13] = FINAL_ST; mte[10][14] = FINAL_ST; mte[10][15] = FINAL_ST; mte[10][16] = FINAL_ST; mte[10][17] = FINAL_ST;
		mte[10][18] = FINAL_ST; mte[10][19] = FINAL_ST; mte[10][20] = FINAL_ST; mte[10][21] = FINAL_ST; mte[10][22] = FINAL_ST; mte[10][23] = FINAL_ST;
		mte[10][24] = FINAL_ST; mte[10][25] = FINAL_ST; mte[10][26]= FINAL_ST;
		
		// Fila 11 matriz transicion estados
		
		mte[11][0] = Constants.ERR_TOKEN; mte[11][1] = Constants.ERR_TOKEN; mte[11][2] = Constants.ERR_TOKEN; mte[11][3] = Constants.ERR_TOKEN; mte[11][4] = Constants.ERR_TOKEN; mte[11][5] = Constants.ERR_TOKEN;
		mte[11][6] = Constants.ERR_TOKEN; mte[11][7] = Constants.ERR_TOKEN; mte[11][8] = Constants.ERR_TOKEN; mte[11][9] = Constants.ERR_TOKEN; mte[11][10] = Constants.ERR_TOKEN; mte[11][11] = 9;
		mte[11][12] = Constants.ERR_TOKEN; mte[11][13] = Constants.ERR_TOKEN; mte[11][14] = Constants.ERR_TOKEN; mte[11][15] = Constants.ERR_TOKEN; mte[11][16] = Constants.ERR_TOKEN; mte[11][17] = Constants.ERR_TOKEN;
		mte[11][18] = Constants.ERR_TOKEN; mte[11][19] = Constants.ERR_TOKEN; mte[11][20] = Constants.ERR_TOKEN; mte[11][21] = Constants.ERR_TOKEN; mte[11][22] = Constants.ERR_TOKEN; mte[11][23] = Constants.ERR_TOKEN;
		mte[11][24] = Constants.ERR_TOKEN; mte[11][25] = Constants.ERR_TOKEN; mte[11][26]= Constants.ERR_TOKEN;
		
		// Fila 12 matriz transicion estados
		
		mte[12][0] = Constants.ERR_TOKEN; mte[12][1] = Constants.ERR_TOKEN; mte[12][2] = Constants.ERR_TOKEN; mte[12][3] = Constants.ERR_TOKEN; mte[12][4] = Constants.ERR_TOKEN; mte[12][5] = Constants.ERR_TOKEN;
		mte[12][6] = Constants.ERR_TOKEN; mte[12][7] = Constants.ERR_TOKEN; mte[12][8] = Constants.ERR_TOKEN; mte[12][9] = Constants.ERR_TOKEN; mte[12][10] = Constants.ERR_TOKEN; mte[12][11] = 9;
		mte[12][12] = Constants.ERR_TOKEN; mte[12][13] = Constants.ERR_TOKEN; mte[12][14] = Constants.ERR_TOKEN; mte[12][15] = Constants.ERR_TOKEN; mte[12][16] = Constants.ERR_TOKEN; mte[12][17] = Constants.ERR_TOKEN;
		mte[12][18] = Constants.ERR_TOKEN; mte[12][19] = Constants.ERR_TOKEN; mte[12][20] = Constants.ERR_TOKEN; mte[12][21] = Constants.ERR_TOKEN; mte[12][22] = Constants.ERR_TOKEN;
                mte[12][23] = Constants.ERR_TOKEN; mte[12][24] = Constants.ERR_TOKEN; mte[12][25] = Constants.ERR_TOKEN; mte[12][26]= Constants.ERR_TOKEN;
		
		// Fila 13 matriz transicion estados
		
		mte[13][0] = 13; mte[13][1] = 13; mte[13][2] = 13; mte[13][3] = 13; mte[13][4] = 13; mte[13][5] = 13;
		mte[13][6] = 13; mte[13][7] = 13; mte[13][8] = 13; mte[13][9] = 13; mte[13][10] = 13; mte[13][11] = 13;
		mte[13][12] = 13; mte[13][13] = 13; mte[13][14] = 13; mte[13][15] = 13; mte[13][16] = FINAL_ST; mte[13][17] = 13;
		mte[13][18] = 13; mte[13][19] = 13; mte[13][20] = 13; mte[13][21] = 13; mte[13][22] = 13; mte[13][23] = Constants.ERR_TOKEN;
		mte[13][24] = 13; mte[13][25] = 13; mte[13][26]= 13;
		
		//Semantic Action Matrix
                
               
	}	
	
	// Public Method: Returns the next state from the mte(State transition table), it takes the actual state and *
	// 			the character read from the input file.***********************************************
	
	public int getNextState(int state, char c){
		
            return mte[state][getColumnVal(c)];
	}
	
        // Public method: Returns the action needed to aply given the actual state of the automata and the char readed from file
        
	public SemanticAction getAction(int state, char c){
			//System.out.println(state + "," +getColumnVal(c));
            return mas[state][getColumnVal(c)];
        }
	
	// Private method: Returns the column value associated to the character coming from the input**********************
	
	private int getColumnVal(char c){

		// Utilizar Case ¿?
		
		if(c == '_')
			return 0;
		else if((c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') && (c != 'u' && c != 'i' && c != 'l')) 
			// Podria ser string y utilizar funcion upperCase() ¿?
			return 1;
		else if(c == 'u')
			return 2;
		else if(c == 'i')
			return 3;
		else if(c == 'l')
			return 4;
		else if(c >= '0' && c <= '9')
			return 5;
		else if(c == '+')
			return 6;
		else if(c == '-')
			return 7;
		else if(c == '*')
			return 8;
		else if(c == '/')
			return 9;
		else if(c == ':')
			return 10;
		else if(c == '=')
			return 11;
		else if(c == '<')
			return 12;
		else if(c == '>')
			return 13;
		else if(c == '!')
			return 14;
		else if(c == '#')
			return 15;
		else if(c == '\'')
			return 16;
		else if(c == '(')
			return 17;
		else if(c == ')')
			return 18;
		else if(c == '{')
			return 19;
		else if(c == '}')
			return 20;
		else if(c == ',')
			return 21;
		else if(c == ';')
			return 22; 
		else  if(c == '\n')
			return 23;
		else if(c == ' ')
			return 24;
        else if(c == '\t')
            return 26;
		else
			return 25;
	
	}
	
}
