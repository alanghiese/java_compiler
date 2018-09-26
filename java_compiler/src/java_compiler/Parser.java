package java_compiler;
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

public class Parser {

	LexicalAnalizer la;

	boolean yydebug; // do I want debug output?
	int yynerrs; // number of errors so far
	int yyerrflag; // was there an error?
	int yychar; // the current working character

	// ########## MESSAGES ##########
	// ###############################################################
	// method: debug
	// ###############################################################
	void debug(String msg) {
		if (yydebug)
			System.out.println(msg);
	}

	// ########## STATE STACK ##########
	final static int YYSTACKSIZE = 500; // maximum stack size
	int statestk[] = new int[YYSTACKSIZE]; // state stack
	int stateptr;
	int stateptrmax; // highest index of stackptr
	int statemax; // state when highest index reached
	// ###############################################################
	// methods: state stack push,pop,drop,peek
	// ###############################################################

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

	// ###############################################################
	// method: init_stacks : allocate and prepare stacks
	// ###############################################################
	final boolean init_stacks() {
		stateptr = -1;
		val_init();
		return true;
	}

	// ###############################################################
	// method: dump_stacks : show n levels of the stacks
	// ###############################################################
	void dump_stacks(int count) {
		int i;
		System.out.println("=index==state====value=     s:" + stateptr + "  v:" + valptr);
		for (i = 0; i < count; i++)
			System.out.println(" " + i + "    " + statestk[i] + "      " + valstk[i]);
		System.out.println("======================");
	}

	// ########## SEMANTIC VALUES ##########
	// public class ParserVal is defined in ParserVal.java

	String yytext;// user variable to return contextual strings
	ParserVal yyval; // used to return semantic vals from action routines
	ParserVal yylval;// the 'lval' (result) I got from yylex()
	ParserVal valstk[];
	int valptr;

	// ###############################################################
	// methods: value stack push,pop,drop,peek.
	// ###############################################################
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

	// #### end semantic value section ####
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
	final static short yylhs[] = { -1, 0, 1, 1, 1, 1, 1, 1, 2, 2, 5, 8, 8, 8, 6, 9, 9, 10, 10, 11, 11, 3, 3, 3, 3, 3,
			13, 13, 14, 15, 17, 17, 18, 18, 18, 18, 16, 16, 4, 12, 12, 12, 12, 21, 21, 21, 20, 20, 20, 20, 7, 7, 22, 22,
			19, 19, 19, 19, 19, 19, };
	final static short yylen[] = { 2, 1, 3, 3, 3, 2, 2, 2, 1, 1, 2, 3, 1, 2, 4, 4, 2, 4, 2, 5, 2, 1, 1, 1, 1, 1, 10, 7,
			5, 4, 3, 2, 1, 1, 1, 3, 6, 2, 3, 3, 3, 2, 1, 3, 3, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, };
	final static short yydefred[] = { 0, 0, 0, 0, 0, 50, 51, 0, 53, 0, 0, 0, 0, 0, 0, 8, 9, 0, 0, 21, 22, 23, 24, 0, 45,
			37, 0, 0, 0, 0, 0, 52, 41, 0, 0, 0, 5, 6, 7, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 4, 13, 0, 0,
			0, 0, 39, 40, 43, 44, 31, 54, 55, 56, 57, 58, 59, 0, 0, 29, 0, 0, 16, 0, 11, 0, 0, 0, 14, 0, 0, 32, 33, 34,
			0, 28, 46, 0, 48, 0, 0, 18, 0, 0, 0, 0, 36, 15, 0, 0, 0, 35, 27, 0, 49, 0, 0, 17, 0, 0, 0, 0, 26, 19, };
	final static short yydgoto[] = { 10, 11, 12, 13, 14, 15, 16, 17, 41, 60, 83, 105, 18, 19, 20, 21, 22, 48, 89, 72,
			94, 23, 24, };
	final static short yysindex[] = { 8, -26, -14, 2, 11, 0, 0, -29, 0, -39, 0, 8, 13, 25, 26, 0, 0, -228, 15, 0, 0, 0,
			0, -3, 0, 0, -30, -215, -30, -39, -200, 0, 0, 28, 29, 30, 0, 0, 0, 31, -32, 0, -39, -39, -210, -210, 33,
			-38, 38, 40, 41, 15, 24, 0, 0, 0, 0, 42, -222, -203, -114, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -39, -16, 0,
			-16, -214, 0, 35, 0, -187, 44, 8, 0, 15, 8, 0, 0, 0, 46, 0, 0, 37, 0, 51, 56, 0, -44, -4, -197, -185, 0, 0,
			54, 59, -25, 0, 0, -16, 0, 0, -39, 0, 57, 7, -158, 61, 0, 0, };
	final static short yyrindex[] = { 0, 0, 0, 0, 0, 0, 0, -7, 0, 0, 0, 106, 0, 0, 0, 0, 0, 0, -26, 0, 0, 0, 0, -41, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 26, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 64, 0, 0, 0, 0, 0, 68, 0, 0, 0, 0, 0, 0, 0, 69, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -34, 0, 0, 0, 0, 0, 0, 0, 0, };
	final static short yygindex[] = { 0, -68, 5, 14, 20, 0, 0, 55, 58, 0, 0, 0, 4, 0, 0, 0, 0, 91, -63, 0, 0, 0, 23, };
	final static int YYTABLESIZE = 280;
	static short yytable[];
	static {
		yytable();
	}

	static void yytable() {
		yytable = new short[] { 42, 9, 42, 42, 42, 42, 9, 43, 59, 82, 37, 30, 90, 32, 97, 9, 33, 98, 25, 42, 42, 42, 67,
				66, 68, 34, 26, 58, 39, 9, 47, 35, 47, 51, 39, 52, 52, 52, 52, 44, 52, 9, 27, 40, 45, 113, 61, 62, 116,
				78, 42, 28, 43, 9, 91, 92, 93, 36, 42, 49, 43, 31, 8, 5, 6, 107, 108, 63, 64, 37, 38, 52, 53, 54, 55,
				56, 84, 65, 86, 73, 86, 74, 75, 76, 95, 109, 77, 87, 96, 87, 99, 20, 101, 88, 58, 88, 100, 102, 110,
				111, 112, 115, 33, 33, 117, 118, 1, 85, 12, 30, 47, 34, 34, 86, 80, 114, 79, 35, 35, 50, 0, 106, 87, 0,
				0, 0, 0, 0, 88, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 81, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 103, 0, 0, 0, 0, 2, 0, 0, 3, 4, 5, 6, 57, 0,
				46, 7, 8, 104, 0, 29, 31, 8, 42, 42, 42, 69, 70, 71, 1, 31, 8, 0, 0, 2, 0, 0, 3, 4, 5, 6, 1, 0, 0, 7, 8,
				2, 0, 0, 3, 4, 5, 6, 1, 0, 0, 7, 8, 2, 0, 0, 3, 4, 5, 6, 0, 0, 0, 7, 8, };
	}

	static short yycheck[];
	static {
		yycheck();
	}

	static void yycheck() {
		yycheck = new short[] { 41, 45, 43, 44, 45, 43, 45, 45, 40, 123, 44, 40, 75, 9, 82, 45, 11, 85, 44, 60, 61, 62,
				60, 61, 62, 11, 40, 59, 256, 45, 26, 11, 28, 29, 256, 42, 43, 44, 45, 42, 47, 45, 40, 271, 47, 108, 42,
				43, 41, 271, 43, 40, 45, 45, 268, 269, 270, 44, 43, 274, 45, 271, 272, 266, 267, 262, 263, 44, 45, 44,
				44, 271, 44, 44, 44, 44, 72, 44, 73, 41, 75, 41, 41, 59, 271, 270, 44, 73, 44, 75, 44, 125, 41, 73, 59,
				75, 59, 41, 44, 40, 125, 44, 97, 98, 262, 44, 0, 123, 44, 41, 41, 97, 98, 108, 59, 111, 58, 97, 98, 28,
				-1, 125, 108, -1, -1, -1, -1, -1, 108, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 256, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 256, -1, -1, -1, -1, 261, -1, -1, 264, 265,
				266, 267, 256, -1, 256, 271, 272, 273, -1, 260, 271, 272, 275, 276, 277, 275, 276, 277, 256, 271, 272,
				-1, -1, 261, -1, -1, 264, 265, 266, 267, 256, -1, -1, 271, 272, 261, -1, -1, 264, 265, 266, 267, 256,
				-1, -1, 271, 272, 261, -1, -1, 264, 265, 266, 267, -1, -1, -1, 271, 272, };
	}

	final static short YYFINAL = 10;
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
	final static String yyrule[] = { "$accept : prog", "prog : sent", "sent : sent decl ','", "sent : sent exec ','",
			"sent : sent asig ','", "sent : decl ','", "sent : exec ','", "sent : asig ','", "decl : var",
			"decl : func", "var : type lst_var", "lst_var : ID ';' lst_var", "lst_var : ID", "lst_var : error ','",
			"func : type ID param func_body", "param : '(' type ID ')'", "param : error ','",
			"func_body : '{' sent ret '}'", "func_body : error ','", "ret : RETURN '(' expr ')' ','", "ret : error ','",
			"exec : s_cond", "exec : ctrl", "exec : prt", "exec : inv_fun", "exec : expr",
			"s_cond : IF '(' cond ')' block ',' ELSE block ',' ENDIF", "s_cond : IF '(' cond ')' block ',' ENDIF",
			"ctrl : WHILE '(' cond ')' block", "prt : PRINT '(' STRING ')'", "cond : expr comp expr",
			"cond : error ','", "block : decl", "block : exec", "block : asig", "block : '{' sent '}'",
			"inv_fun : ID '(' ID ';' lst_perm ')'", "inv_fun : error ','", "asig : ID ASIG expr",
			"expr : expr '+' expr", "expr : expr '-' expr", "expr : '-' expr", "expr : term", "term : term '*' fact",
			"term : term '/' fact", "term : fact", "lst_perm : READONLY", "lst_perm : WRITE", "lst_perm : PASS",
			"lst_perm : WRITE ';' PASS", "type : USINTEGER", "type : LINTEGER", "fact : ID", "fact : CTE", "comp : '='",
			"comp : '<'", "comp : '>'", "comp : \"MEN_IG\"", "comp : \"MAY_IG\"", "comp : \"DIST\"", };

	// ###############################################################
	// method: yylexdebug : check lexer state
	// ###############################################################
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

	// The following are now global, to aid in error reporting
	int yyn; // next next thing to do
	int yym; //
	int yystate; // current parsing state from state table
	String yys; // current token string

	// ###############################################################
	// method: yyparse : parse input and execute indicated items
	// ###############################################################
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
					yychar = la.yylex(); // get next token
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
			// ########## USER-SUPPLIED ACTIONS ##########
			case 1:
			// #line 7 "gramatica.txt"
			{
				System.out.println("Sintaxis: Codigo reconocido correctamente");
			}
				break;
			case 10:
			// #line 22 "gramatica.txt"
			{
				System.out.println("Sintaxis: Declaracion de variable");
			}
				break;
			case 13:
			// #line 28 "gramatica.txt"
			{
				System.out.println("Error sintactico: lista de variables mal construida");
			}
				break;
			case 14:
			// #line 31 "gramatica.txt"
			{
				System.out.println("Sintaxis: Declaracion de funcion");
			}
				break;
			case 16:
			// #line 36 "gramatica.txt"
			{
				System.out.println("Error sintactico: Parametro de funcion mal escrito");
			}
				break;
			case 18:
			// #line 40 "gramatica.txt"
			{
				System.out.println("Error sintactico: cuerpo de funcion mal construida");
			}
				break;
			case 20:
			// #line 44 "gramatica.txt"
			{
				System.out.println("Error sintactico: expresion de retorno erronea");
			}
				break;
			case 26:
			// #line 54 "gramatica.txt"
			{
				System.out.println("Sintaxis: Sentencia IF");
			}
				break;
			case 27:
			// #line 55 "gramatica.txt"
			{
				System.out.println("Sintaxis: Sentencia IF");
			}
				break;
			case 28:
			// #line 59 "gramatica.txt"
			{
				System.out.println("Sintaxis: Sentencia While");
			}
				break;
			case 29:
			// #line 63 "gramatica.txt"
			{
				System.out.println("Sintaxis: Sentencia print");
			}
				break;
			case 31:
			// #line 68 "gramatica.txt"
			{
				System.out.println("Error sintactico: Condicion mal expresada");
			}
				break;
			case 36:
			// #line 77 "gramatica.txt"
			{
				System.out.println("Sintaxis: Invocacion a funcion");
			}
				break;
			case 37:
			// #line 78 "gramatica.txt"
			{
				System.out.println("Error sintactico: Invocacion a funcion erronea");
			}
				break;
			case 38:
			// #line 81 "gramatica.txt"
			{
				System.out.println("Sintaxis: Asignacion");
			}
				break;
			// #line 538 "Parser.java"
			// ########## END OF USER-SUPPLIED ACTIONS ##########
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
					yychar = la.yylex(); // get next character
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
	// ## end of method parse() ######################################

	// ## run() --- for Thread #######################################
	/**
	 * A default run method, used for operating this parser object in the
	 * background. It is intended for extending Thread or implementing Runnable.
	 * Turn off with -Jnorun .
	 */
	public void run() {
		yyparse();
	}
	// ## end of method run() ########################################

	public void yyerror(String s) {
		System.out.println(s);
	}

//## Constructors ###############################################
	/**
	 * Default constructor. Turn off with -Jnoconstruct .
	 * 
	 */
	public Parser(String path) {
		la = new LexicalAnalizer(path, this.yylval);
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
