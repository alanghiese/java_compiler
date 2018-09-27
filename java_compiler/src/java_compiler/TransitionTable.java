package java_compiler;

import semantic_actions.*;
import utilities.Constants;

public class TransitionTable {

	// Row => State - Column => Symbol from input
	// Symbol Index :
	// '_' -> 0 '::letter::' -> 1 'u' -> 2 'i' -> 3
	// 'l' -> 4 '::digit:: -> 5 '+' -> 6 '-' -> 7
	// '*' -> 8 '/' -> 9 ':' -> 10 '=' -> 11
	// '<' -> 12 '>' -> 13 '!' -> 14 '#' -> 15
	// '\' -> 16 '(' -> 17 ')' -> 18 '{' -> 19
	// '}' -> 20 ',' -> 21 ';' -> 22 '\n' -> 23
	// ' ' -> 24 '::universe::' -> 25'\t' -> 26 '$' -> 27

	static final int NUM_COL = 28;
	static final int NUM_ROW = 14;
	public static final int FINAL_ST = NUM_ROW;

	private int[][] mte;
	private SemanticAction[][] mas;

	public TransitionTable() {

		mte = new int[NUM_ROW][NUM_COL];
		mas = new SemanticAction[NUM_ROW][NUM_COL];

		// Fila 0 matriz transicion estados

		mte[0][0] = 1;
		mte[0][1] = 6;
		mte[0][2] = 6;
		mte[0][3] = 6;
		mte[0][4] = 6;
		mte[0][5] = 3;
		mte[0][6] = FINAL_ST;
		mte[0][7] = FINAL_ST;
		mte[0][8] = FINAL_ST;
		mte[0][9] = FINAL_ST;
		mte[0][10] = 12;
		mte[0][11] = FINAL_ST;
		mte[0][12] = 10;
		mte[0][13] = 8;
		mte[0][14] = 11;
		mte[0][15] = 7;
		mte[0][16] = 13;
		mte[0][17] = FINAL_ST;
		mte[0][18] = FINAL_ST;
		mte[0][19] = FINAL_ST;
		mte[0][20] = FINAL_ST;
		mte[0][21] = FINAL_ST;
		mte[0][22] = FINAL_ST;
		mte[0][23] = 0;
		mte[0][24] = 0;
		mte[0][25] = Constants.ERR_STATE;
		mte[0][26] = 0;
		mte[0][27] = FINAL_ST;

		// Fila 1 matriz transicion estados

		mte[1][0] = Constants.ERR_STATE;
		mte[1][1] = 2;
		mte[1][2] = 2;
		mte[1][3] = 2;
		mte[1][4] = 2;
		mte[1][5] = 2;
		mte[1][6] = Constants.ERR_STATE;
		mte[1][7] = Constants.ERR_STATE;
		mte[1][8] = Constants.ERR_STATE;
		mte[1][9] = Constants.ERR_STATE;
		mte[1][10] = Constants.ERR_STATE;
		mte[1][11] = Constants.ERR_STATE;
		mte[1][12] = Constants.ERR_STATE;
		mte[1][13] = Constants.ERR_STATE;
		mte[1][14] = Constants.ERR_STATE;
		mte[1][15] = Constants.ERR_STATE;
		mte[1][16] = Constants.ERR_STATE;
		mte[1][17] = Constants.ERR_STATE;
		mte[1][18] = Constants.ERR_STATE;
		mte[1][19] = Constants.ERR_STATE;
		mte[1][20] = Constants.ERR_STATE;
		mte[1][21] = Constants.ERR_STATE;
		mte[1][22] = Constants.ERR_STATE;
		mte[1][23] = Constants.ERR_STATE;
		mte[1][24] = Constants.ERR_STATE;
		mte[1][25] = Constants.ERR_STATE;
		mte[1][26] = Constants.ERR_STATE;
		mte[1][27] = Constants.ERR_STATE;

		// Fila 2 matriz transicion estados

		mte[2][0] = Constants.ERR_STATE;
		mte[2][1] = 2;
		mte[2][2] = 2;
		mte[2][3] = 2;
		mte[2][4] = 2;
		mte[2][5] = 2;
		mte[2][6] = FINAL_ST;
		mte[2][7] = FINAL_ST;
		mte[2][8] = FINAL_ST;
		mte[2][9] = FINAL_ST;
		mte[2][10] = FINAL_ST;
		mte[2][11] = FINAL_ST;
		mte[2][12] = FINAL_ST;
		mte[2][13] = FINAL_ST;
		mte[2][14] = FINAL_ST;
		mte[2][15] = FINAL_ST;
		mte[2][16] = FINAL_ST;
		mte[2][17] = FINAL_ST;
		mte[2][18] = FINAL_ST;
		mte[2][19] = FINAL_ST;
		mte[2][20] = FINAL_ST;
		mte[2][21] = FINAL_ST;
		mte[2][22] = FINAL_ST;
		mte[2][23] = FINAL_ST;
		mte[2][24] = FINAL_ST;
		mte[2][25] = FINAL_ST;
		mte[2][26] = FINAL_ST;
		mte[2][27] = FINAL_ST;

		// Fila 3 matriz transicion estados

		mte[3][0] = 4;
		mte[3][1] = Constants.ERR_STATE;
		mte[3][2] = Constants.ERR_STATE;
		mte[3][3] = Constants.ERR_STATE;
		mte[3][4] = Constants.ERR_STATE;
		mte[3][5] = 3;
		mte[3][6] = Constants.ERR_STATE;
		mte[3][7] = Constants.ERR_STATE;
		mte[3][8] = Constants.ERR_STATE;
		mte[3][9] = Constants.ERR_STATE;
		mte[3][10] = Constants.ERR_STATE;
		mte[3][11] = Constants.ERR_STATE;
		mte[3][12] = Constants.ERR_STATE;
		mte[3][13] = Constants.ERR_STATE;
		mte[3][14] = Constants.ERR_STATE;
		mte[3][15] = Constants.ERR_STATE;
		mte[3][16] = Constants.ERR_STATE;
		mte[3][17] = Constants.ERR_STATE;
		mte[3][18] = Constants.ERR_STATE;
		mte[3][19] = Constants.ERR_STATE;
		mte[3][20] = Constants.ERR_STATE;
		mte[3][21] = Constants.ERR_STATE;
		mte[3][22] = Constants.ERR_STATE;
		mte[3][23] = Constants.ERR_STATE;
		mte[3][24] = Constants.ERR_STATE;
		mte[3][25] = Constants.ERR_STATE;
		mte[3][26] = Constants.ERR_STATE;
		mte[3][27] = Constants.ERR_STATE;

		// Fila 4 matriz transicion estados

		mte[4][0] = Constants.ERR_STATE;
		mte[4][1] = Constants.ERR_STATE;
		mte[4][2] = 5;
		mte[4][3] = Constants.ERR_STATE;
		mte[4][4] = FINAL_ST;
		mte[4][5] = Constants.ERR_STATE;
		mte[4][6] = Constants.ERR_STATE;
		mte[4][7] = Constants.ERR_STATE;
		mte[4][8] = Constants.ERR_STATE;
		mte[4][9] = Constants.ERR_STATE;
		mte[4][10] = Constants.ERR_STATE;
		mte[4][11] = Constants.ERR_STATE;
		mte[4][12] = Constants.ERR_STATE;
		mte[4][13] = Constants.ERR_STATE;
		mte[4][14] = Constants.ERR_STATE;
		mte[4][15] = Constants.ERR_STATE;
		mte[4][16] = Constants.ERR_STATE;
		mte[4][17] = Constants.ERR_STATE;
		mte[4][18] = Constants.ERR_STATE;
		mte[4][19] = Constants.ERR_STATE;
		mte[4][20] = Constants.ERR_STATE;
		mte[4][21] = Constants.ERR_STATE;
		mte[4][22] = Constants.ERR_STATE;
		mte[4][23] = Constants.ERR_STATE;
		mte[4][24] = Constants.ERR_STATE;
		mte[4][25] = Constants.ERR_STATE;
		mte[4][26] = Constants.ERR_STATE;
		mte[4][27] = Constants.ERR_STATE;

		// Fila 5 matriz transicion estados

		mte[5][0] = Constants.ERR_STATE;
		mte[5][1] = Constants.ERR_STATE;
		mte[5][2] = Constants.ERR_STATE;
		mte[5][3] = FINAL_ST;
		mte[5][4] = Constants.ERR_STATE;
		mte[5][5] = Constants.ERR_STATE;
		mte[5][6] = Constants.ERR_STATE;
		mte[5][7] = Constants.ERR_STATE;
		mte[5][8] = Constants.ERR_STATE;
		mte[5][9] = Constants.ERR_STATE;
		mte[5][10] = Constants.ERR_STATE;
		mte[5][11] = Constants.ERR_STATE;
		mte[5][12] = Constants.ERR_STATE;
		mte[5][13] = Constants.ERR_STATE;
		mte[5][14] = Constants.ERR_STATE;
		mte[5][15] = Constants.ERR_STATE;
		mte[5][16] = Constants.ERR_STATE;
		mte[5][17] = Constants.ERR_STATE;
		mte[5][18] = Constants.ERR_STATE;
		mte[5][19] = Constants.ERR_STATE;
		mte[5][20] = Constants.ERR_STATE;
		mte[5][21] = Constants.ERR_STATE;
		mte[5][22] = Constants.ERR_STATE;
		mte[5][23] = Constants.ERR_STATE;
		mte[5][24] = Constants.ERR_STATE;
		mte[5][25] = Constants.ERR_STATE;
		mte[5][26] = Constants.ERR_STATE;
		mte[5][27] = Constants.ERR_STATE;

		// Fila 6 matriz transicion estados

		mte[6][0] = FINAL_ST;
		mte[6][1] = 6;
		mte[6][2] = 6;
		mte[6][3] = 6;
		mte[6][4] = 6;
		mte[6][5] = FINAL_ST;
		mte[6][6] = FINAL_ST;
		mte[6][7] = FINAL_ST;
		mte[6][8] = FINAL_ST;
		mte[6][9] = FINAL_ST;
		mte[6][10] = FINAL_ST;
		mte[6][11] = FINAL_ST;
		mte[6][12] = FINAL_ST;
		mte[6][13] = FINAL_ST;
		mte[6][14] = FINAL_ST;
		mte[6][15] = FINAL_ST;
		mte[6][16] = FINAL_ST;
		mte[6][17] = FINAL_ST;
		mte[6][18] = FINAL_ST;
		mte[6][19] = FINAL_ST;
		mte[6][20] = FINAL_ST;
		mte[6][21] = FINAL_ST;
		mte[6][22] = FINAL_ST;
		mte[6][23] = FINAL_ST;
		mte[6][24] = FINAL_ST;
		mte[6][25] = FINAL_ST;
		mte[6][26] = FINAL_ST;
		mte[6][27] = FINAL_ST;

		// Fila 7 matriz transicion estados

		mte[7][0] = 7;
		mte[7][1] = 7;
		mte[7][2] = 7;
		mte[7][3] = 7;
		mte[7][4] = 7;
		mte[7][5] = 7;
		mte[7][6] = 7;
		mte[7][7] = 7;
		mte[7][8] = 7;
		mte[7][9] = 7;
		mte[7][10] = 7;
		mte[7][11] = 7;
		mte[7][12] = 7;
		mte[7][13] = 7;
		mte[7][14] = 7;
		mte[7][15] = 0;
		mte[7][16] = 7;
		mte[7][17] = 7;
		mte[7][18] = 7;
		mte[7][19] = 7;
		mte[7][20] = 7;
		mte[7][21] = 7;
		mte[7][22] = 7;
		mte[7][23] = 7;
		mte[7][24] = 7;
		mte[7][25] = 7;
		mte[7][26] = 7;
		mte[7][27] = Constants.ERR_STATE;

		// Fila 8 matriz transicion estados

		mte[8][0] = FINAL_ST;
		mte[8][1] = FINAL_ST;
		mte[8][2] = FINAL_ST;
		mte[8][3] = FINAL_ST;
		mte[8][4] = FINAL_ST;
		mte[8][5] = FINAL_ST;
		mte[8][6] = FINAL_ST;
		mte[8][7] = FINAL_ST;
		mte[8][8] = FINAL_ST;
		mte[8][9] = FINAL_ST;
		mte[8][10] = FINAL_ST;
		mte[8][11] = 9;
		mte[8][12] = FINAL_ST;
		mte[8][13] = FINAL_ST;
		mte[8][14] = FINAL_ST;
		mte[8][15] = FINAL_ST;
		mte[8][16] = FINAL_ST;
		mte[8][17] = FINAL_ST;
		mte[8][18] = FINAL_ST;
		mte[8][19] = FINAL_ST;
		mte[8][20] = FINAL_ST;
		mte[8][21] = FINAL_ST;
		mte[8][22] = FINAL_ST;
		mte[8][23] = FINAL_ST;
		mte[8][24] = FINAL_ST;
		mte[8][25] = FINAL_ST;
		mte[8][26] = FINAL_ST;
		mte[8][27] = FINAL_ST;

		// Fila 9 matriz transicion estados

		mte[9][0] = FINAL_ST;
		mte[9][1] = FINAL_ST;
		mte[9][2] = FINAL_ST;
		mte[9][3] = FINAL_ST;
		mte[9][4] = FINAL_ST;
		mte[9][5] = FINAL_ST;
		mte[9][6] = FINAL_ST;
		mte[9][7] = FINAL_ST;
		mte[9][8] = FINAL_ST;
		mte[9][9] = FINAL_ST;
		mte[9][10] = FINAL_ST;
		mte[9][11] = FINAL_ST;
		mte[9][12] = FINAL_ST;
		mte[9][13] = FINAL_ST;
		mte[9][14] = FINAL_ST;
		mte[9][15] = FINAL_ST;
		mte[9][16] = FINAL_ST;
		mte[9][17] = FINAL_ST;
		mte[9][18] = FINAL_ST;
		mte[9][19] = FINAL_ST;
		mte[9][20] = FINAL_ST;
		mte[9][21] = FINAL_ST;
		mte[9][22] = FINAL_ST;
		mte[9][23] = FINAL_ST;
		mte[9][24] = FINAL_ST;
		mte[9][25] = FINAL_ST;
		mte[9][26] = FINAL_ST;
		mte[9][27] = FINAL_ST;

		// Fila 10 matriz transicion estados

		mte[10][0] = FINAL_ST;
		mte[10][1] = FINAL_ST;
		mte[10][2] = FINAL_ST;
		mte[10][3] = FINAL_ST;
		mte[10][4] = FINAL_ST;
		mte[10][5] = FINAL_ST;
		mte[10][6] = FINAL_ST;
		mte[10][7] = FINAL_ST;
		mte[10][8] = FINAL_ST;
		mte[10][9] = FINAL_ST;
		mte[10][10] = FINAL_ST;
		mte[10][11] = 9;
		mte[10][12] = FINAL_ST;
		mte[10][13] = FINAL_ST;
		mte[10][14] = FINAL_ST;
		mte[10][15] = FINAL_ST;
		mte[10][16] = FINAL_ST;
		mte[10][17] = FINAL_ST;
		mte[10][18] = FINAL_ST;
		mte[10][19] = FINAL_ST;
		mte[10][20] = FINAL_ST;
		mte[10][21] = FINAL_ST;
		mte[10][22] = FINAL_ST;
		mte[10][23] = FINAL_ST;
		mte[10][24] = FINAL_ST;
		mte[10][25] = FINAL_ST;
		mte[10][26] = FINAL_ST;
		mte[10][27] = FINAL_ST;

		// Fila 11 matriz transicion estados

		mte[11][0] = Constants.ERR_STATE;
		mte[11][1] = Constants.ERR_STATE;
		mte[11][2] = Constants.ERR_STATE;
		mte[11][3] = Constants.ERR_STATE;
		mte[11][4] = Constants.ERR_STATE;
		mte[11][5] = Constants.ERR_STATE;
		mte[11][6] = Constants.ERR_STATE;
		mte[11][7] = Constants.ERR_STATE;
		mte[11][8] = Constants.ERR_STATE;
		mte[11][9] = Constants.ERR_STATE;
		mte[11][10] = Constants.ERR_STATE;
		mte[11][11] = 9;
		mte[11][12] = Constants.ERR_STATE;
		mte[11][13] = Constants.ERR_STATE;
		mte[11][14] = Constants.ERR_STATE;
		mte[11][15] = Constants.ERR_STATE;
		mte[11][16] = Constants.ERR_STATE;
		mte[11][17] = Constants.ERR_STATE;
		mte[11][18] = Constants.ERR_STATE;
		mte[11][19] = Constants.ERR_STATE;
		mte[11][20] = Constants.ERR_STATE;
		mte[11][21] = Constants.ERR_STATE;
		mte[11][22] = Constants.ERR_STATE;
		mte[11][23] = Constants.ERR_STATE;
		mte[11][24] = Constants.ERR_STATE;
		mte[11][25] = Constants.ERR_STATE;
		mte[11][26] = Constants.ERR_STATE;
		mte[11][27] = FINAL_ST;

		// Fila 12 matriz transicion estados

		mte[12][0] = Constants.ERR_STATE;
		mte[12][1] = Constants.ERR_STATE;
		mte[12][2] = Constants.ERR_STATE;
		mte[12][3] = Constants.ERR_STATE;
		mte[12][4] = Constants.ERR_STATE;
		mte[12][5] = Constants.ERR_STATE;
		mte[12][6] = Constants.ERR_STATE;
		mte[12][7] = Constants.ERR_STATE;
		mte[12][8] = Constants.ERR_STATE;
		mte[12][9] = Constants.ERR_STATE;
		mte[12][10] = Constants.ERR_STATE;
		mte[12][11] = 9;
		mte[12][12] = Constants.ERR_STATE;
		mte[12][13] = Constants.ERR_STATE;
		mte[12][14] = Constants.ERR_STATE;
		mte[12][15] = Constants.ERR_STATE;
		mte[12][16] = Constants.ERR_STATE;
		mte[12][17] = Constants.ERR_STATE;
		mte[12][18] = Constants.ERR_STATE;
		mte[12][19] = Constants.ERR_STATE;
		mte[12][20] = Constants.ERR_STATE;
		mte[12][21] = Constants.ERR_STATE;
		mte[12][22] = Constants.ERR_STATE;
		mte[12][23] = Constants.ERR_STATE;
		mte[12][24] = Constants.ERR_STATE;
		mte[12][25] = Constants.ERR_STATE;
		mte[12][26] = Constants.ERR_STATE;
		mte[12][27] = FINAL_ST;

		// Fila 13 matriz transicion estados

		mte[13][0] = 13;
		mte[13][1] = 13;
		mte[13][2] = 13;
		mte[13][3] = 13;
		mte[13][4] = 13;
		mte[13][5] = 13;
		mte[13][6] = 13;
		mte[13][7] = 13;
		mte[13][8] = 13;
		mte[13][9] = 13;
		mte[13][10] = 13;
		mte[13][11] = 13;
		mte[13][12] = 13;
		mte[13][13] = 13;
		mte[13][14] = 13;
		mte[13][15] = 13;
		mte[13][16] = FINAL_ST;
		mte[13][17] = 13;
		mte[13][18] = 13;
		mte[13][19] = 13;
		mte[13][20] = 13;
		mte[13][21] = 13;
		mte[13][22] = 13;
		mte[13][23] = Constants.ERR_STATE;
		mte[13][24] = 13;
		mte[13][25] = 13;
		mte[13][26] = 13;
		mte[13][27] = Constants.ERR_STATE;

		// Semantic Action Matrix

		SA0 as0 = new SA0();
		SA1 as1 = new SA1();
		SA2 as2 = new SA2();
		SA3 as3 = new SA3();
		SA4 as4 = new SA4();
		SA5 as5 = new SA5();
		SA6 as6 = new SA6();
		ERR_ID errId = new ERR_ID();
		ERR_CTN errCte = new ERR_CTN();
		ERR_COM errCom = new ERR_COM();
		ERR_CHAR errChar = new ERR_CHAR();

		mas[0][0] = as0;
		mas[0][1] = as0;
		mas[0][2] = as0;
		mas[0][3] = as0;
		mas[0][4] = as0;
		mas[0][5] = as0;
		mas[0][6] = as6;
		mas[0][7] = as6;
		mas[0][8] = as6;
		mas[0][9] = as6;
		mas[0][10] = as0;
		mas[0][11] = as6;
		mas[0][12] = as0;
		mas[0][13] = as0;
		mas[0][14] = as0;
		mas[0][15] = as4;
		mas[0][16] = as4;
		mas[0][17] = as6;
		mas[0][18] = as6;
		mas[0][19] = as6;
		mas[0][20] = as6;
		mas[0][21] = as6;
		mas[0][22] = as6;
		mas[0][23] = as4;
		mas[0][24] = as4;
		mas[0][25] = errChar;
		mas[0][26] = as4;
		mas[0][27] = as6;
		mas[1][0] = errId;
		mas[1][1] = as0;
		mas[1][2] = as0;
		mas[1][3] = as0;
		mas[1][4] = as0;
		mas[1][5] = as0;
		mas[1][6] = errId;
		mas[1][7] = errId;
		mas[1][8] = errId;
		mas[1][9] = errId;
		mas[1][10] = errId;
		mas[1][11] = errId;
		mas[1][12] = errId;
		mas[1][13] = errId;
		mas[1][14] = errId;
		mas[1][15] = errId;
		mas[1][16] = errId;
		mas[1][17] = errId;
		mas[1][18] = errId;
		mas[1][19] = errId;
		mas[1][20] = errId;
		mas[1][21] = errId;
		mas[1][22] = errId;
		mas[1][23] = errId;
		mas[1][24] = errId;
		mas[1][25] = errChar;
		mas[1][26] = errId;
		mas[1][27] = errId;
		mas[2][0] = as1;
		mas[2][1] = as0;
		mas[2][2] = as0;
		mas[2][3] = as0;
		mas[2][4] = as0;
		mas[2][5] = as0;
		mas[2][6] = as1;
		mas[2][7] = as1;
		mas[2][8] = as1;
		mas[2][9] = as1;
		mas[2][10] = as1;
		mas[2][11] = as1;
		mas[2][12] = as1;
		mas[2][13] = as1;
		mas[2][14] = as1;
		mas[2][15] = as1;
		mas[2][16] = as1;
		mas[2][17] = as1;
		mas[2][18] = as1;
		mas[2][19] = as1;
		mas[2][20] = as1;
		mas[2][21] = as1;
		mas[2][22] = as1;
		mas[2][23] = as1;
		mas[2][24] = as1;
		mas[2][25] = as1;
		mas[2][26] = as1;
		mas[2][27] = as1;
		mas[3][0] = as0;
		mas[3][1] = errCte;
		mas[3][2] = errCte;
		mas[3][3] = errCte;
		mas[3][4] = errCte;
		mas[3][5] = as0;
		mas[3][6] = errCte;
		mas[3][7] = errCte;
		mas[3][8] = errCte;
		mas[3][9] = errCte;
		mas[3][10] = errCte;
		mas[3][11] = errCte;
		mas[3][12] = errCte;
		mas[3][13] = errCte;
		mas[3][14] = errCte;
		mas[3][15] = errCte;
		mas[3][16] = errCte;
		mas[3][17] = errCte;
		mas[3][18] = errCte;
		mas[3][19] = errCte;
		mas[3][20] = errCte;
		mas[3][21] = errCte;
		mas[3][22] = errCte;
		mas[3][23] = errCte;
		mas[3][24] = errCte;
		mas[3][25] = errChar;
		mas[3][26] = errCte;
		mas[3][27] = errCte;
		mas[4][0] = errCte;
		mas[4][1] = errCte;
		mas[4][2] = as0;
		mas[4][3] = errCte;
		mas[4][4] = as2;
		mas[4][5] = errCte;
		mas[4][6] = errCte;
		mas[4][7] = errCte;
		mas[4][8] = errCte;
		mas[4][9] = errCte;
		mas[4][10] = errCte;
		mas[4][11] = errCte;
		mas[4][12] = errCte;
		mas[4][13] = errCte;
		mas[4][14] = errCte;
		mas[4][15] = errCte;
		mas[4][16] = errCte;
		mas[4][17] = errCte;
		mas[4][18] = errCte;
		mas[4][19] = errCte;
		mas[4][20] = errCte;
		mas[4][21] = errCte;
		mas[4][22] = errCte;
		mas[4][23] = errCte;
		mas[4][24] = errCte;
		mas[4][25] = errChar;
		mas[4][26] = errCte;
		mas[4][27] = errCte;
		mas[5][0] = errCte;
		mas[5][1] = errCte;
		mas[5][2] = errCte;
		mas[5][3] = as2;
		mas[5][4] = errCte;
		mas[5][5] = errCte;
		mas[5][6] = errCte;
		mas[5][7] = errCte;
		mas[5][8] = errCte;
		mas[5][9] = errCte;
		mas[5][10] = errCte;
		mas[5][11] = errCte;
		mas[5][12] = errCte;
		mas[5][13] = errCte;
		mas[5][14] = errCte;
		mas[5][15] = errCte;
		mas[5][16] = errCte;
		mas[5][17] = errCte;
		mas[5][18] = errCte;
		mas[5][19] = errCte;
		mas[5][20] = errCte;
		mas[5][21] = errCte;
		mas[5][22] = errCte;
		mas[5][23] = errCte;
		mas[5][24] = errCte;
		mas[5][25] = errChar;
		mas[5][26] = errCte;
		mas[5][27] = errCte;
		mas[6][0] = as3;
		mas[6][1] = as0;
		mas[6][2] = as0;
		mas[6][3] = as0;
		mas[6][4] = as0;
		mas[6][5] = as3;
		mas[6][6] = as3;
		mas[6][7] = as3;
		mas[6][8] = as3;
		mas[6][9] = as3;
		mas[6][10] = as3;
		mas[6][11] = as3;
		mas[6][12] = as3;
		mas[6][13] = as3;
		mas[6][14] = as3;
		mas[6][15] = as3;
		mas[6][16] = as3;
		mas[6][17] = as3;
		mas[6][18] = as3;
		mas[6][19] = as3;
		mas[6][20] = as3;
		mas[6][21] = as3;
		mas[6][22] = as3;
		mas[6][23] = as3;
		mas[6][24] = as3;
		mas[6][25] = as3;
		mas[6][26] = as3;
		mas[6][27] = as3;
		mas[7][0] = as4;
		mas[7][1] = as4;
		mas[7][2] = as4;
		mas[7][3] = as4;
		mas[7][4] = as4;
		mas[7][5] = as4;
		mas[7][6] = as4;
		mas[7][7] = as4;
		mas[7][8] = as4;
		mas[7][9] = as4;
		mas[7][10] = as4;
		mas[7][11] = as4;
		mas[7][12] = as4;
		mas[7][13] = as4;
		mas[7][14] = as4;
		mas[7][15] = as4;
		mas[7][16] = as4;
		mas[7][17] = as4;
		mas[7][18] = as4;
		mas[7][19] = as4;
		mas[7][20] = as4;
		mas[7][21] = as4;
		mas[7][22] = as4;
		mas[7][23] = as4;
		mas[7][24] = as4;
		mas[7][25] = as4;
		mas[7][26] = as4;
		mas[7][27] = errCom;
		mas[8][0] = as3;
		mas[8][1] = as3;
		mas[8][2] = as3;
		mas[8][3] = as3;
		mas[8][4] = as3;
		mas[8][5] = as3;
		mas[8][6] = as3;
		mas[8][7] = as3;
		mas[8][8] = as3;
		mas[8][9] = as3;
		mas[8][10] = as3;
		mas[8][11] = as0;
		mas[8][12] = as3;
		mas[8][13] = as3;
		mas[8][14] = as3;
		mas[8][15] = as3;
		mas[8][16] = as3;
		mas[8][17] = as3;
		mas[8][18] = as3;
		mas[8][19] = as3;
		mas[8][20] = as3;
		mas[8][21] = as3;
		mas[8][22] = as3;
		mas[8][23] = as3;
		mas[8][24] = as3;
		mas[8][25] = as3;
		mas[8][26] = as3;
		mas[8][27] = as3;
		mas[9][0] = as3;
		mas[9][1] = as3;
		mas[9][2] = as3;
		mas[9][3] = as3;
		mas[9][4] = as3;
		mas[9][5] = as3;
		mas[9][6] = as3;
		mas[9][7] = as3;
		mas[9][8] = as3;
		mas[9][9] = as3;
		mas[9][10] = as3;
		mas[9][11] = as0;
		mas[9][12] = as3;
		mas[9][13] = as3;
		mas[9][14] = as3;
		mas[9][15] = as3;
		mas[9][16] = as3;
		mas[9][17] = as3;
		mas[9][18] = as3;
		mas[9][19] = as3;
		mas[9][20] = as3;
		mas[9][21] = as3;
		mas[9][22] = as3;
		mas[9][23] = as3;
		mas[9][24] = as3;
		mas[9][25] = as3;
		mas[9][26] = as3;
		mas[9][27] = as3;
		mas[10][0] = as3;
		mas[10][1] = as3;
		mas[10][2] = as3;
		mas[10][3] = as3;
		mas[10][4] = as3;
		mas[10][5] = as3;
		mas[10][6] = as3;
		mas[10][7] = as3;
		mas[10][8] = as3;
		mas[10][9] = as3;
		mas[10][10] = as3;
		mas[10][11] = as0;
		mas[10][12] = as3;
		mas[10][13] = as3;
		mas[10][14] = as3;
		mas[10][15] = as3;
		mas[10][16] = as3;
		mas[10][17] = as3;
		mas[10][18] = as3;
		mas[10][19] = as3;
		mas[10][20] = as3;
		mas[10][21] = as3;
		mas[10][22] = as3;
		mas[10][23] = as3;
		mas[10][24] = as3;
		mas[10][25] = as3;
		mas[10][26] = as3;
		mas[10][27] = as3;
		mas[11][0] = errChar;
		mas[11][1] = errChar;
		mas[11][2] = errChar;
		mas[11][3] = errChar;
		mas[11][4] = errChar;
		mas[11][5] = errChar;
		mas[11][6] = errChar;
		mas[11][7] = errChar;
		mas[11][8] = errChar;
		mas[11][9] = errChar;
		mas[11][10] = errChar;
		mas[11][11] = as0;
		mas[11][12] = errChar;
		mas[11][13] = errChar;
		mas[11][14] = errChar;
		mas[11][15] = errChar;
		mas[11][16] = errChar;
		mas[11][17] = errChar;
		mas[11][18] = errChar;
		mas[11][19] = errChar;
		mas[11][20] = errChar;
		mas[11][21] = errChar;
		mas[11][22] = errChar;
		mas[11][23] = errChar;
		mas[11][24] = errChar;
		mas[11][25] = errChar;
		mas[11][26] = errChar;
		mas[11][27] = errChar;
		mas[12][0] = errChar;
		mas[12][1] = errChar;
		mas[12][2] = errChar;
		mas[12][3] = errChar;
		mas[12][4] = errChar;
		mas[12][5] = errChar;
		mas[12][6] = errChar;
		mas[12][7] = errChar;
		mas[12][8] = errChar;
		mas[12][9] = errChar;
		mas[12][10] = errChar;
		mas[12][11] = as0;
		mas[12][12] = errChar;
		mas[12][13] = errChar;
		mas[12][14] = errChar;
		mas[12][15] = errChar;
		mas[12][16] = errChar;
		mas[12][17] = errChar;
		mas[12][18] = errChar;
		mas[12][19] = errChar;
		mas[12][20] = errChar;
		mas[12][21] = errChar;
		mas[12][22] = errChar;
		mas[12][23] = errChar;
		mas[12][24] = errChar;
		mas[12][25] = errChar;
		mas[12][26] = errChar;
		mas[12][27] = errChar;
		mas[13][0] = as0;
		mas[13][1] = as0;
		mas[13][2] = as0;
		mas[13][3] = as0;
		mas[13][4] = as0;
		mas[13][5] = as0;
		mas[13][6] = as0;
		mas[13][7] = as0;
		mas[13][8] = as0;
		mas[13][9] = as0;
		mas[13][10] = as0;
		mas[13][11] = as0;
		mas[13][12] = as0;
		mas[13][13] = as0;
		mas[13][14] = as0;
		mas[13][15] = as0;
		mas[13][16] = as5;
		mas[13][17] = as0;
		mas[13][18] = as0;
		mas[13][19] = as0;
		mas[13][20] = as0;
		mas[13][21] = as0;
		mas[13][22] = as0;
		mas[13][23] = errCte;
		mas[13][24] = as0;
		mas[13][25] = as0;
		mas[13][26] = as0;
		mas[13][27] = errCte;

	}

	// Public Method: Returns the next state from the mte(State transition table),
	// it takes the actual state and *
	// the character read from the input
	// file.***********************************************

	public int getNextState(int state, char c) {

		return mte[state][getColumnVal(c)];
	}

	// Public method: Returns the action needed to aply given the actual state of
	// the automata and the char readed from file

	public SemanticAction getAction(int state, char c) {
		// System.out.println(state + "," +getColumnVal(c));
		return mas[state][getColumnVal(c)];
	}

	// Private method: Returns the column value associated to the character coming
	// from the input**********************

	private int getColumnVal(char c) {

		// Utilizar Case ¿?

		if (c == '_')
			return 0;
		else if ((c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') && (c != 'u' && c != 'i' && c != 'l'))
			// Podria ser string y utilizar funcion upperCase() ¿?
			return 1;
		else if (c == 'u')
			return 2;
		else if (c == 'i')
			return 3;
		else if (c == 'l')
			return 4;
		else if (c >= '0' && c <= '9')
			return 5;
		else if (c == '+')
			return 6;
		else if (c == '-')
			return 7;
		else if (c == '*')
			return 8;
		else if (c == '/')
			return 9;
		else if (c == ':')
			return 10;
		else if (c == '=')
			return 11;
		else if (c == '<')
			return 12;
		else if (c == '>')
			return 13;
		else if (c == '!')
			return 14;
		else if (c == '#')
			return 15;
		else if (c == '\'')
			return 16;
		else if (c == '(')
			return 17;
		else if (c == ')')
			return 18;
		else if (c == '{')
			return 19;
		else if (c == '}')
			return 20;
		else if (c == ',')
			return 21;
		else if (c == ';')
			return 22;
		else if (c == '\n')
			return 23;
		else if (c == ' ')
			return 24;
		else if (c == '\t')
			return 26;
		else if (c == '陰')
			return 27;
		else
			return 25;

	}
	
	public int[][] getMTE(){
		return this.mte;
	}

}
