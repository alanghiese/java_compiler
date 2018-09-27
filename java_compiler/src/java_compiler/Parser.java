//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";

//#line 2 "gramatica"
package java_compiler;

import utilities.Token;
//#line 20 "Parser.java"

public class Parser {

	boolean yydebug; // do I want debug output?
	int yynerrs; // number of errors so far
	int yyerrflag; // was there an error?
	int yychar; // the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
	void debug(String msg) {
		if (yydebug)
			System.out.println(msg);
	}

//########## STATE STACK ##########
	final static int YYSTACKSIZE = 500; // maximum stack size
	int statestk[] = new int[YYSTACKSIZE]; // state stack
	int stateptr;
	int stateptrmax; // highest index of stackptr
	int statemax; // state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################

	final void state_push(int state) {
		try {
			stateptr++;
			statestk[stateptr] = state;
		} catch (ArrayIndexOutOfBoundsException e) {
			int oldsize = statestk.length;
			int newsize = oldsize * 2;
			int[] newstack = new int[newsize];
			System.arraycopy(statestk, 0, newstack, 0, oldsize);
			statestk = newstack;
			statestk[stateptr] = state;
		}
	}

	final int state_pop() {
		return statestk[stateptr--];
	}

	final void state_drop(int cnt) {
		stateptr -= cnt;
	}

	final int state_peek(int relative) {
		return statestk[stateptr - relative];
	}

//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
	final boolean init_stacks() {
		stateptr = -1;
		val_init();
		return true;
	}

//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
	void dump_stacks(int count) {
		int i;
		System.out.println("=index==state====value=     s:" + stateptr + "  v:" + valptr);
		for (i = 0; i < count; i++)
			System.out.println(" " + i + "    " + statestk[i] + "      " + valstk[i]);
		System.out.println("======================");
	}

//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java

	String yytext;// user variable to return contextual strings
	ParserVal yyval; // used to return semantic vals from action routines
	ParserVal yylval;// the 'lval' (result) I got from yylex()
	ParserVal valstk[];
	int valptr;

//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
	void val_init() {
		valstk = new ParserVal[YYSTACKSIZE];
		yyval = new ParserVal();
		yylval = new ParserVal();
		valptr = -1;
	}

	void val_push(ParserVal val) {
		if (valptr >= YYSTACKSIZE)
			return;
		valstk[++valptr] = val;
	}

	ParserVal val_pop() {
		if (valptr < 0)
			return new ParserVal();
		return valstk[valptr--];
	}

	void val_drop(int cnt) {
		int ptr;
		ptr = valptr - cnt;
		if (ptr < 0)
			return;
		valptr = ptr;
	}

	ParserVal val_peek(int relative) {
		int ptr;
		ptr = valptr - relative;
		if (ptr < 0)
			return new ParserVal();
		return valstk[ptr];
	}

	final ParserVal dup_yyval(ParserVal val) {
		ParserVal dup = new ParserVal();
		dup.ival = val.ival;
		dup.dval = val.dval;
		dup.sval = val.sval;
		dup.obj = val.obj;
		return dup;
	}

//#### end semantic value section ####
	public final static short MAY_IG = 257;
	public final static short MEN_IG = 258;
	public final static short DIST = 259;
	public final static short ASIG = 260;
	public final static short IF = 261;
	public final static short ENDIF = 262;
	public final static short ELSE = 263;
	public final static short PRINT = 264;
	public final static short WHILE = 265;
	public final static short USINTEGER = 266;
	public final static short LINTEGER = 267;
	public final static short READONLY = 268;
	public final static short WRITE = 269;
	public final static short PASS = 270;
	public final static short ID = 271;
	public final static short CTE = 272;
	public final static short RETURN = 273;
	public final static short STRING = 274;
	public final static short YYERRCODE = 256;
	final static short yylhs[] = { -1, 0, 1, 1, 1, 1, 1, 1, 2, 2, 5, 8, 8, 8, 6, 9, 9, 10, 10, 11, 11, 3, 3, 3, 3, 13,
			13, 14, 15, 17, 17, 18, 18, 18, 18, 16, 16, 4, 4, 4, 4, 4, 12, 12, 12, 12, 21, 21, 21, 20, 20, 20, 20, 7, 7,
			22, 22, 19, 19, 19, 19, 19, 19, };
	final static short yylen[] = { 2, 1, 2, 2, 2, 1, 1, 1, 1, 1, 3, 3, 1, 2, 5, 4, 2, 4, 2, 5, 2, 1, 1, 1, 1, 9, 7, 5,
			5, 3, 2, 1, 1, 1, 3, 7, 2, 4, 3, 3, 3, 3, 3, 3, 2, 1, 3, 3, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, };
	final static short yydefred[] = { 0, 0, 0, 0, 0, 0, 53, 54, 0, 0, 0, 5, 6, 7, 8, 9, 0, 21, 22, 23, 24, 36, 55, 56,
			0, 0, 0, 48, 0, 0, 0, 0, 0, 0, 2, 3, 4, 0, 0, 0, 44, 0, 0, 38, 0, 0, 0, 0, 0, 0, 0, 40, 0, 0, 39, 13, 0, 0,
			0, 0, 10, 42, 43, 46, 47, 30, 57, 58, 59, 60, 61, 62, 0, 0, 0, 0, 37, 0, 16, 0, 11, 0, 0, 0, 0, 0, 0, 31,
			32, 33, 0, 28, 27, 49, 0, 51, 0, 0, 18, 0, 14, 0, 0, 0, 0, 0, 15, 0, 0, 0, 34, 26, 0, 52, 35, 0, 0, 17, 0,
			0, 25, 0, 19, };
	final static short yydgoto[] = { 9, 10, 11, 12, 13, 14, 15, 16, 39, 59, 84, 109, 47, 17, 18, 19, 20, 48, 90, 72, 96,
			26, 27, };
	final static short yysindex[] = { -166, 15, -41, -10, 45, 51, 0, 0, -38, 0, -166, 0, 0, 0, 0, 0, -242, 0, 0, 0, 0,
			0, 0, 0, -41, 11, -14, 0, -45, -227, -45, -33, -178, 29, 0, 0, 0, 48, -32, 52, 0, -41, -41, 0, -255, -255,
			53, -40, 62, 66, 67, 0, 37, 50, 0, 0, 74, -230, -209, -113, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -41, -105,
			75, -105, 0, -218, 0, 61, 0, -161, 77, -166, 78, -9, -166, 0, 0, 0, -179, 0, 0, 0, 65, 0, 70, 84, 0, -196,
			0, -93, 83, -105, -142, 85, 0, 86, 91, 8, 0, 0, -130, 0, 0, 0, -41, 0, 90, -6, 0, 92, 0, };
	final static short yyrindex[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 135, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 94, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 94, 0, 0, 0, 0, 0, 98, 0, 0, 0, 0, 0, 0, 0, 0, 99, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -119, 0, 0, 0, 0, 0, 0, 0, };
	final static short yygindex[] = { 0, -43, 3, 13, 14, 0, 0, 95, 93, 0, 0, 0, 7, 0, 0, 0, 0, 119, -50, 0, 0, 0, 22, };
	final static int YYTABLESIZE = 292;
	static short yytable[];
	static {
		yytable();
	}

	static void yytable() {
		yytable = new short[] { 24, 45, 32, 41, 24, 42, 20, 24, 58, 25, 83, 51, 24, 34, 37, 33, 22, 23, 86, 41, 67, 66,
				68, 35, 36, 92, 37, 57, 44, 38, 28, 40, 110, 45, 41, 121, 42, 41, 52, 42, 99, 79, 45, 101, 45, 45, 45,
				49, 61, 62, 93, 94, 95, 112, 41, 43, 42, 6, 7, 21, 107, 45, 45, 45, 2, 3, 63, 64, 4, 5, 6, 7, 41, 54,
				42, 8, 87, 108, 87, 85, 41, 76, 42, 102, 103, 29, 88, 89, 88, 89, 1, 30, 55, 53, 2, 3, 60, 65, 4, 5, 6,
				7, 34, 73, 34, 8, 87, 74, 75, 77, 97, 105, 35, 36, 35, 36, 88, 89, 78, 91, 57, 98, 100, 119, 104, 106,
				45, 111, 113, 114, 115, 116, 118, 117, 120, 1, 122, 36, 12, 29, 50, 36, 36, 82, 41, 36, 36, 36, 36, 50,
				80, 1, 36, 81, 36, 2, 3, 0, 0, 4, 5, 6, 7, 1, 0, 0, 8, 2, 3, 0, 0, 4, 5, 6, 7, 0, 0, 0, 8, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 46, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 31, 0, 56, 0, 22, 23, 0, 0, 22, 23, 0, 22, 23, 69, 70, 71, 22, 23, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 45, 0, 0, 0, 45, 45, 45, 45, 45, 45, 45, 45, 0, 0, 0, 45, 0, 45, 41, 45,
				45, 45, 41, 41, 41, 41, 41, 41, 41, 41, 0, 0, 0, 41, 0, 41, };
	}

	static short yycheck[];
	static {
		yycheck();
	}

	static void yycheck() {
		yycheck = new short[] { 45, 0, 40, 43, 45, 45, 125, 45, 40, 2, 123, 44, 45, 10, 256, 8, 271, 272, 123, 0, 60,
				61, 62, 10, 10, 75, 256, 59, 42, 271, 40, 24, 125, 47, 43, 41, 45, 43, 31, 45, 83, 271, 41, 86, 43, 44,
				45, 274, 41, 42, 268, 269, 270, 103, 43, 44, 45, 266, 267, 44, 256, 60, 61, 62, 260, 261, 44, 45, 264,
				265, 266, 267, 43, 44, 45, 271, 73, 273, 75, 72, 43, 44, 45, 262, 263, 40, 73, 73, 75, 75, 256, 40, 44,
				271, 260, 261, 44, 44, 264, 265, 266, 267, 99, 41, 101, 271, 103, 41, 41, 59, 271, 41, 99, 99, 101, 101,
				103, 103, 44, 44, 59, 44, 44, 116, 59, 41, 125, 44, 270, 44, 44, 40, 262, 125, 44, 0, 44, 256, 44, 41,
				41, 260, 261, 256, 125, 264, 265, 266, 267, 30, 57, 256, 271, 58, 273, 260, 261, -1, -1, 264, 265, 266,
				267, 256, -1, -1, 271, 260, 261, -1, -1, 264, 265, 266, 267, -1, -1, -1, 271, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				256, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 260, -1, 256, -1, 271, 272, -1, -1, 271, 272, -1, 271, 272,
				275, 276, 277, 271, 272, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 256, -1,
				-1, -1, 260, 261, 262, 263, 264, 265, 266, 267, -1, -1, -1, 271, -1, 273, 256, 275, 276, 277, 260, 261,
				262, 263, 264, 265, 266, 267, -1, -1, -1, 271, -1, 273, };
	}

	final static short YYFINAL = 9;
	final static short YYMAXTOKEN = 277;
	final static String yyname[] = { "end-of-file", null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, "'('", "')'", "'*'", "'+'", "','", "'-'", null,
			"'/'", null, null, null, null, null, null, null, null, null, null, null, "';'", "'<'", "'='", "'>'", null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, "'{'", null, "'}'", null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, "MAY_IG", "MEN_IG",
			"DIST", "ASIG", "IF", "ENDIF", "ELSE", "PRINT", "WHILE", "USINTEGER", "LINTEGER", "READONLY", "WRITE",
			"PASS", "ID", "CTE", "RETURN", "STRING", "\"MEN_IG\"", "\"MAY_IG\"", "\"DIST\"", };
	final static String yyrule[] = { "$accept : prog", "prog : sent", "sent : sent decl", "sent : sent exec",
			"sent : sent asig", "sent : decl", "sent : exec", "sent : asig", "decl : var", "decl : func",
			"var : type lst_var ','", "lst_var : ID ';' lst_var", "lst_var : ID", "lst_var : error ','",
			"func : type ID param func_body ','", "param : '(' type ID ')'", "param : error ','",
			"func_body : '{' sent ret '}'", "func_body : error ','", "ret : RETURN '(' expr ')' ','", "ret : error ','",
			"exec : s_cond", "exec : ctrl", "exec : prt", "exec : inv_fun",
			"s_cond : IF '(' cond ')' block ELSE block ENDIF ','", "s_cond : IF '(' cond ')' block ENDIF ','",
			"ctrl : WHILE '(' cond ')' block", "prt : PRINT '(' STRING ')' ','", "cond : expr comp expr",
			"cond : error ','", "block : decl", "block : exec", "block : asig", "block : '{' sent '}'",
			"inv_fun : ID '(' ID ';' lst_perm ')' ','", "inv_fun : error ','", "asig : ID ASIG expr ','",
			"asig : ASIG expr ','", "asig : ID expr ','", "asig : ID ASIG ','", "asig : ID ASIG expr",
			"expr : expr '+' expr", "expr : expr '-' expr", "expr : '-' expr", "expr : term", "term : term '*' fact",
			"term : term '/' fact", "term : fact", "lst_perm : READONLY", "lst_perm : WRITE", "lst_perm : PASS",
			"lst_perm : WRITE ';' PASS", "type : USINTEGER", "type : LINTEGER", "fact : ID", "fact : CTE", "comp : '='",
			"comp : '<'", "comp : '>'", "comp : \"MEN_IG\"", "comp : \"MAY_IG\"", "comp : \"DIST\"", };

//#line 127 "gramatica"

	LexicalAnalizer la;

	public Parser(String path) {
		la = new LexicalAnalizer(path);
	}

	public void yyerror(int l, String s) {

		System.out.println("Sintax Error: Line " + l + " - " + s);

	}

	public void yyerror(String s) {

		System.out.println(s);

	}

	public void print(String s) {
		System.out.println(s);
	}

//#line 371 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
	void yylexdebug(int state, int ch) {
		String s = null;
		if (ch < 0)
			ch = 0;
		if (ch <= YYMAXTOKEN) // check index bounds
			s = yyname[ch]; // now get it
		if (s == null)
			s = "illegal-symbol";
		debug("state " + state + ", reading " + ch + " (" + s + ")");
	}

//The following are now global, to aid in error reporting
	int yyn; // next next thing to do
	int yym; //
	int yystate; // current parsing state from state table
	String yys; // current token string

//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
	int yyparse() {
		boolean doaction;
		init_stacks();
		yynerrs = 0;
		yyerrflag = 0;
		yychar = -1; // impossible char forces a read
		yystate = 0; // initial state
		state_push(yystate); // save it
		val_push(yylval); // save empty value
		while (true) // until parsing is done, either correctly, or w/error
		{
			doaction = true;
			if (yydebug)
				debug("loop");
			// #### NEXT ACTION (from reduction table)
			for (yyn = yydefred[yystate]; yyn == 0; yyn = yydefred[yystate]) {
				if (yydebug)
					debug("yyn:" + yyn + "  state:" + yystate + "  yychar:" + yychar);
				if (yychar < 0) // we want a char?
				{
					yylval = new ParserVal();
					yychar = la.yylex(yylval); // get next token
					if (yydebug)
						debug(" next yychar:" + yychar);
					// #### ERROR CHECK ####
					if (yychar < 0) // it it didn't work/error
					{
						yychar = 0; // change it to default string (no -1!)
						if (yydebug)
							yylexdebug(yystate, yychar);
					}
				} // yychar<0
				yyn = yysindex[yystate]; // get amount to shift by (shift index)
				if ((yyn != 0) && (yyn += yychar) >= 0 && yyn <= YYTABLESIZE && yycheck[yyn] == yychar) {
					if (yydebug)
						debug("state " + yystate + ", shifting to state " + yytable[yyn]);
					// #### NEXT STATE ####
					yystate = yytable[yyn];// we are in a new state
					state_push(yystate); // save it
					val_push(yylval); // push our lval as the input for next rule
					yychar = -1; // since we have 'eaten' a token, say we need another
					if (yyerrflag > 0) // have we recovered an error?
						--yyerrflag; // give ourselves credit
					doaction = false; // but don't process yet
					break; // quit the yyn=0 loop
				}

				yyn = yyrindex[yystate]; // reduce
				if ((yyn != 0) && (yyn += yychar) >= 0 && yyn <= YYTABLESIZE && yycheck[yyn] == yychar) { // we reduced!
					if (yydebug)
						debug("reduce");
					yyn = yytable[yyn];
					doaction = true; // get ready to execute
					break; // drop down to actions
				} else // ERROR RECOVERY
				{
					if (yyerrflag == 0) {
						yyerror("syntax error");
						yynerrs++;
					}
					if (yyerrflag < 3) // low error count?
					{
						yyerrflag = 3;
						while (true) // do until break
						{
							if (stateptr < 0) // check for under & overflow here
							{
								yyerror("stack underflow. aborting..."); // note lower case 's'
								return 1;
							}
							yyn = yysindex[state_peek(0)];
							if ((yyn != 0) && (yyn += YYERRCODE) >= 0 && yyn <= YYTABLESIZE
									&& yycheck[yyn] == YYERRCODE) {
								if (yydebug)
									debug("state " + state_peek(0) + ", error recovery shifting to state "
											+ yytable[yyn] + " ");
								yystate = yytable[yyn];
								state_push(yystate);
								val_push(yylval);
								doaction = false;
								break;
							} else {
								if (yydebug)
									debug("error recovery discarding state " + state_peek(0) + " ");
								if (stateptr < 0) // check for under & overflow here
								{
									yyerror("Stack underflow. aborting..."); // capital 'S'
									return 1;
								}
								state_pop();
								val_pop();
							}
						}
					} else // discard this token
					{
						if (yychar == 0)
							return 1; // yyabort
						if (yydebug) {
							yys = null;
							if (yychar <= YYMAXTOKEN)
								yys = yyname[yychar];
							if (yys == null)
								yys = "illegal-symbol";
							debug("state " + yystate + ", error recovery discards token " + yychar + " (" + yys + ")");
						}
						yychar = -1; // read another
					}
				} // end error recovery
			} // yyn=0 loop
			if (!doaction) // any reason not to proceed?
				continue; // skip action
			yym = yylen[yyn]; // get count of terminals on rhs
			if (yydebug)
				debug("state " + yystate + ", reducing " + yym + " by rule " + yyn + " (" + yyrule[yyn] + ")");
			if (yym > 0) // if count of rhs not 'nil'
				yyval = val_peek(yym - 1); // get current semantic value
			yyval = dup_yyval(yyval); // duplicate yyval if ParserVal is used as semantic value
			switch (yyn) {
//########## USER-SUPPLIED ACTIONS ##########
			case 1:
//#line 12 "gramatica"
			{
				System.out.println("Sintaxis: Codigo reconocido correctamente");
			}
				break;
			case 10:
//#line 27 "gramatica"
			{
				System.out.println("Sintaxis: Declaracion de variable");
			}
				break;
			case 13:
//#line 33 "gramatica"
			{
				System.out.println("Error sintactico: lista de variables mal construida");
			}
				break;
			case 14:
//#line 36 "gramatica"
			{
				System.out.println("Sintaxis: Declaracion de funcion");
			}
				break;
			case 16:
//#line 41 "gramatica"
			{
				System.out.println("Error sintactico: Parametro de funcion mal escrito");
			}
				break;
			case 18:
//#line 45 "gramatica"
			{
				System.out.println("Error sintactico: cuerpo de funcion mal construida");
			}
				break;
			case 20:
//#line 49 "gramatica"
			{
				System.out.println("Error sintactico: expresion de retorno erronea");
			}
				break;
			case 25:
//#line 58 "gramatica"
			{
				System.out.println("Sintaxis: Sentencia IF");
			}
				break;
			case 26:
//#line 59 "gramatica"
			{
				System.out.println("Sintaxis: Sentencia IF");
			}
				break;
			case 27:
//#line 63 "gramatica"
			{
				System.out.println("Sintaxis: Sentencia While");
			}
				break;
			case 28:
//#line 67 "gramatica"
			{
				System.out.println("Sintaxis: Sentencia print");
			}
				break;
			case 30:
//#line 72 "gramatica"
			{
				System.out.println("Error sintactico: Condicion mal expresada");
			}
				break;
			case 35:
//#line 81 "gramatica"
			{
				print("Sintaxis: Invocacion a funcion");
			}
				break;
			case 36:
//#line 82 "gramatica"
			{
				print("Error sintactico: Invocacion a funcion erronea");
			}
				break;
			case 37:
//#line 85 "gramatica"
			{
				print("Sintaxis: Asignacion");
			}
				break;
			case 38:
//#line 86 "gramatica"
			{
				yyerror(((Token) val_peek(2).obj).getLine(), "Asignacion sin id del lado izq");
			}
				break;
			case 39:
//#line 87 "gramatica"
			{
				yyerror(((Token) val_peek(2).obj).getLine(), "Asignacion sin :=");
			}
				break;
			case 40:
//#line 88 "gramatica"
			{
				yyerror(((Token) val_peek(2).obj).getLine(), "Falta expresion del lado derecho del :=");
			}
				break;
			case 41:
//#line 89 "gramatica"
			{
				yyerror(((Token) val_peek(2).obj).getLine(), "Falta ','");
			}
				break;
//#line 596 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
			}// switch
				// #### Now let's reduce... ####
			if (yydebug)
				debug("reduce");
			state_drop(yym); // we just reduced yylen states
			yystate = state_peek(0); // get new state
			val_drop(yym); // corresponding value drop
			yym = yylhs[yyn]; // select next TERMINAL(on lhs)
			if (yystate == 0 && yym == 0)// done? 'rest' state and at first TERMINAL
			{
				if (yydebug)
					debug("After reduction, shifting from state 0 to state " + YYFINAL + "");
				yystate = YYFINAL; // explicitly say we're done
				state_push(YYFINAL); // and save it
				val_push(yyval); // also save the semantic value of parsing
				if (yychar < 0) // we want another character?
				{
					yylval = new ParserVal();
					yychar = la.yylex(yylval); // get next character
					if (yychar < 0)
						yychar = 0; // clean, if necessary
					if (yydebug)
						yylexdebug(yystate, yychar);
				}
				if (yychar == 0) // Good exit (if lex returns 0 ;-)
					break; // quit the loop--all DONE
			} // if yystate
			else // else not done yet
			{ // get next state and push, for next yydefred[]
				yyn = yygindex[yym]; // find out where to go
				if ((yyn != 0) && (yyn += yystate) >= 0 && yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
					yystate = yytable[yyn]; // get new state
				else
					yystate = yydgoto[yym]; // else go to new defred
				if (yydebug)
					debug("after reduction, shifting from state " + state_peek(0) + " to state " + yystate + "");
				state_push(yystate); // going again, so push state & val...
				val_push(yyval); // for next action
			}
		} // main loop
		return 0;// yyaccept!!
	}
//## end of method parse() ######################################

//## run() --- for Thread #######################################
	/**
	 * A default run method, used for operating this parser object in the
	 * background. It is intended for extending Thread or implementing Runnable.
	 * Turn off with -Jnorun .
	 */
	public void run() {
		yyparse();
	}
//## end of method run() ########################################

//## Constructors ###############################################
	/**
	 * Default constructor. Turn off with -Jnoconstruct .
	 * 
	 */
	public Parser() {
		// nothing to do
	}

	/**
	 * Create a parser, setting the debug to true or false.
	 * 
	 * @param debugMe true for debugging, false for no debug.
	 */
	public Parser(boolean debugMe) {
		yydebug = debugMe;
	}
//###############################################################

}
//################### END OF CLASS ##############################
