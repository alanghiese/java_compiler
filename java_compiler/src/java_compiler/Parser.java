package java_compiler;//### This file created by BYACC 1.8(/Java extension  1.15)
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










public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short MAY_IG=257;
public final static short MEN_IG=258;
public final static short DIST=259;
public final static short ASIG=260;
public final static short IF=261;
public final static short END_IF=262;
public final static short ELSE=263;
public final static short PRINT=264;
public final static short WHILE=265;
public final static short USINTEGER=266;
public final static short LINTEGER=267;
public final static short READONLY=268;
public final static short WRITE=269;
public final static short PASS=270;
public final static short ID=271;
public final static short CTE=272;
public final static short RETURN=273;
public final static short STRING=274;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    1,    1,    1,    2,    2,    5,
    8,    8,    6,    3,    3,    3,    3,    3,   10,   10,
   11,   12,   14,   15,   15,   15,   15,   13,    4,    9,
    9,    9,   18,   18,   18,   17,   17,   17,   17,    7,
    7,   19,   19,   16,   16,   16,   16,   16,   16,
};
final static short yylen[] = {                            2,
    1,    3,    3,    3,    2,    2,    2,    1,    1,    3,
    3,    2,   13,    1,    1,    1,    1,    1,    8,    6,
    5,    4,    3,    1,    1,    1,    3,    7,    3,    3,
    3,    1,    3,    3,    1,    1,    1,    1,    3,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,   40,   41,    0,   43,    0,    0,    0,
    0,    0,    8,    9,    0,    0,   14,   15,   16,   17,
    0,   35,    0,    0,    0,    0,    0,    0,    0,    0,
    5,    6,    7,    0,    0,    0,    0,    0,    0,   42,
    0,    0,    0,    0,    0,    0,    2,    3,    4,    0,
    0,   10,   30,   31,   33,   34,   44,   45,   46,   47,
   48,   49,    0,    0,   22,    0,    0,    0,   11,    0,
    0,    0,   24,   25,   26,    0,   21,   36,    0,   38,
    0,    0,    0,   20,    0,    0,    0,    0,   27,    0,
   39,   28,    0,   19,    0,    0,    0,    0,    0,   13,
};
final static short yydgoto[] = {                          8,
    9,   10,   11,   12,   13,   14,   15,   35,   16,   17,
   18,   19,   20,   42,   76,   63,   81,   21,   22,
};
final static short yysindex[] = {                      -207,
  -15,    2,    9,    0,    0,  -34,    0,    0, -207,   19,
   34,   35,    0,    0, -191,  -10,    0,    0,    0,    0,
    1,    0, -216, -193, -216, -189, -216,   39,   41,   43,
    0,    0,    0,  -22,   44, -216, -216, -216, -216,    0,
  -38,   52,   53,   54,   37,  -10,    0,    0,    0, -174,
 -205,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -216, -110,    0, -110, -201,   40,    0, -173,
  -10, -207,    0,    0,    0, -188,    0,    0,   45,    0,
   65,   66,  -98,    0, -110, -162,   67,  -14,    0, -152,
    0,    0, -207,    0, -220,   72, -216,   -9,  -12,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,  -33,    0,    0,  114,    0,
    0,    0,    0,    0,    0,  -43,    0,    0,    0,    0,
  -41,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -36,    0,    0,    0,   73,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   75,    0,    0,    0,    0,    0,    0,    0,   77,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  -55,    6,    7,   20,    0,    0,   68,   70,    3,    0,
    0,    0,    0,   96,  -35,    0,    0,    0,   38,
};
final static int YYTABLESIZE=241;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         32,
   18,   32,   32,   32,   36,   26,   37,   29,   42,   42,
   42,   42,   72,   42,   28,   29,   83,   51,   32,   32,
   32,   58,   57,   59,   23,   41,   89,   41,   30,   46,
   77,   99,   36,   36,   37,   37,   50,   95,   53,   54,
    1,   24,   38,    2,    3,    4,    5,   39,   25,   90,
    6,    7,   96,    1,   40,    7,    2,    3,    4,    5,
    4,    5,   31,    6,    7,   71,   78,   79,   80,   73,
   74,   73,   74,   84,   85,   55,   56,   32,   33,   34,
   43,   45,   47,   75,   48,   75,   49,   52,   28,   29,
   73,   74,   64,   65,   66,   67,   68,   82,   50,   98,
   28,   29,   30,   86,   75,   87,   88,   91,   93,   94,
   92,   97,  100,    1,   30,   23,   12,   37,   70,   69,
   44,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    1,    0,    0,    2,    3,    4,    5,    0,    0,    0,
    6,    7,    1,    0,    0,    2,    3,    4,    5,    0,
    0,    0,    6,    7,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   18,   18,
   32,   32,    0,    0,    0,   29,   29,    0,   42,   42,
    0,    0,    0,    0,   32,   32,   32,   60,   61,   62,
   27,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
   44,   43,   44,   45,   43,   40,   45,   44,   42,   43,
   44,   45,  123,   47,    9,    9,   72,   40,   60,   61,
   62,   60,   61,   62,   40,   23,  125,   25,    9,   27,
   66,   41,   43,   43,   45,   45,   59,   93,   36,   37,
  261,   40,   42,  264,  265,  266,  267,   47,   40,   85,
  271,  272,  273,  261,  271,  272,  264,  265,  266,  267,
  266,  267,   44,  271,  272,   63,  268,  269,  270,   64,
   64,   66,   66,  262,  263,   38,   39,   44,   44,  271,
  274,  271,   44,   64,   44,   66,   44,   44,   83,   83,
   85,   85,   41,   41,   41,   59,  271,  271,   59,   97,
   95,   95,   83,   59,   85,   41,   41,  270,  123,  262,
   44,   40,  125,    0,   95,   41,   44,   41,   51,   50,
   25,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  261,   -1,   -1,  264,  265,  266,  267,   -1,   -1,   -1,
  271,  272,  261,   -1,   -1,  264,  265,  266,  267,   -1,
   -1,   -1,  271,  272,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  262,  263,
  262,  263,   -1,   -1,   -1,  262,  263,   -1,  262,  263,
   -1,   -1,   -1,   -1,  276,  277,  278,  276,  277,  278,
  275,
};
}
final static short YYFINAL=8;
final static short YYMAXTOKEN=278;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,"';'",
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"MAY_IG","MEN_IG","DIST","ASIG","IF",
"END_IF","ELSE","PRINT","WHILE","USINTEGER","LINTEGER","READONLY","WRITE",
"PASS","ID","CTE","RETURN","STRING","\" ASIG \"","\"MEN_IG\"","\"MAY_IG\"",
"\"DIST\"",
};
final static String yyrule[] = {
"$accept : prog",
"prog : sent",
"sent : sent decl ','",
"sent : sent exec ','",
"sent : sent asig ','",
"sent : decl ','",
"sent : exec ','",
"sent : asig ','",
"decl : var",
"decl : func",
"var : type lst_var ','",
"lst_var : ID ';' lst_var",
"lst_var : ID ';'",
"func : type ID '(' type ID ')' '{' sent RETURN '(' expr ')' '}'",
"exec : s_cond",
"exec : ctrl",
"exec : prt",
"exec : inv_fun",
"exec : expr",
"s_cond : IF '(' cond ')' block ELSE block END_IF",
"s_cond : IF '(' cond ')' block END_IF",
"ctrl : WHILE '(' cond ')' block",
"prt : PRINT '(' STRING ')'",
"cond : expr comp expr",
"block : decl",
"block : exec",
"block : asig",
"block : '{' sent '}'",
"inv_fun : ID '(' ID ';' lst_perm ')' ','",
"asig : ID \" ASIG \" expr",
"expr : expr '+' expr",
"expr : expr '-' expr",
"expr : term",
"term : term '*' fact",
"term : term '/' fact",
"term : fact",
"lst_perm : READONLY",
"lst_perm : WRITE",
"lst_perm : PASS",
"lst_perm : WRITE ';' PASS",
"type : USINTEGER",
"type : LINTEGER",
"fact : ID",
"fact : CTE",
"comp : '='",
"comp : '<'",
"comp : '>'",
"comp : \"MEN_IG\"",
"comp : \"MAY_IG\"",
"comp : \"DIST\"",
};

//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}


/**
 * Lexical analizer
 */
LexicalAnalizer la;


//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = this.la.yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = this.la.yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */

/**
 * error function 
 */
public void yyerror(String s) {
	System.out.println(s);
}
public Parser(String path)
{
  this.la = new LexicalAnalizer(path);
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
