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
public final static short MEN_IG=276;
public final static short MAY_IG=277;
public final static short DIST=278;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    1,    2,    2,    4,    7,    7,
    5,    3,    3,    3,    3,    3,    3,    9,    9,   11,
   12,   14,   10,   10,   13,    8,    8,    8,    8,   17,
   17,   17,   16,   16,   16,   16,    6,    6,   18,   18,
   15,   15,   15,   15,   15,   15,
};
final static short yylen[] = {                            2,
    1,    3,    3,    2,    2,    1,    1,    3,    3,    2,
   13,    1,    1,    1,    1,    1,    1,    8,    6,    5,
    4,    3,    1,    3,    7,    3,    3,    3,    1,    3,
    3,    1,    1,    1,    1,    3,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,   37,   38,    0,   40,    0,    0,    0,
    0,    0,    6,    7,    0,    0,   12,   13,   14,   15,
   16,    0,   32,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    4,    5,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   24,    2,    3,
    0,    0,    8,    0,    0,   39,   30,   31,   41,   42,
   43,   44,   45,   46,    0,    0,   21,    0,    0,    0,
    9,    0,    0,    0,   13,   33,    0,   35,    0,    0,
   19,    0,    0,    0,    0,    0,   36,   25,    0,   18,
    0,    0,    0,    0,    0,   11,
};
final static short yydgoto[] = {                          9,
   30,   11,   12,   13,   14,   15,   36,   16,   17,   18,
   19,   20,   21,   43,   65,   79,   22,   23,
};
final static short yysindex[] = {                       -76,
  -24,  -18,  -12,    0,    0,  -22,    0,  -76,    0,  -76,
   -7,    2,    0,    0, -202,   30,    0,    0,    0,    0,
    0,    3,    0, -223, -198, -223, -189, -223,  -85,  -76,
   43,   52,    0,    0,    4,   53, -223, -223, -218, -218,
 -177,   -2,   58,   59,   60,   44,   30,    0,    0,    0,
 -169, -187,    0,   30,   30,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -223,  -76,    0,  -76, -179,   47,
    0, -164,   30, -170,    0,    0,   49,    0,   68,   69,
    0,  -76, -159,   70,  -11, -147,    0,    0,  -76,    0,
 -111,   76, -223,   29,   -8,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,   41,    0,    0,    0,   27,
    0,    0,    0,    0,    0,   74,    0,    0,    0,    0,
    0,  -36,    0,    0,    0,    0,    0,    0,   75,  -33,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -41,    0,    0,    0,    0,    0,  -31,    0,    0,    0,
   77,    0,    0,  -27,   -5,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   79,   78,    0,    0,   83,    0,    0,    0,
    0,    0,    0,    0,    0,   78,    0,    0,    0,    0,
   75,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   15,   22,   32,    0,    0,   73,   80,   40,    0,   -1,
    0,    0,    0,  100,    0,    0,    0,   55,
};
final static int YYTABLESIZE=276;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         39,
   39,   39,   39,   39,   29,   39,   29,   29,   29,   26,
   23,    8,   26,   27,   10,   24,   27,   27,   39,   39,
   39,   25,   29,   29,   29,   29,    1,   26,   26,   26,
   26,   31,   27,   27,   27,   28,   33,    8,   28,   48,
   37,   32,   38,   52,   39,   34,    8,   41,    7,   40,
   31,   31,   56,    7,   28,   28,   28,   60,   59,   61,
   32,   32,   51,   42,   74,   42,   75,   47,   35,   95,
   23,   37,   37,   38,   38,   44,   54,   55,    4,    5,
   86,   46,   39,   39,   39,   39,   49,   39,   76,   77,
   78,   81,   82,   57,   58,   50,   53,   28,   66,   67,
   68,   70,   69,   91,   73,   51,   80,   83,   84,   85,
   87,   89,   31,   88,   90,   93,   96,   17,   23,   22,
   10,   13,   32,   34,   72,   45,    0,    0,    0,    0,
   71,    0,   94,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,
    0,    0,    2,    3,    4,    5,    0,    0,    0,    6,
    7,   92,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    1,    0,    0,    2,    3,
    4,    5,    0,    0,    1,    6,    7,    2,    3,    4,
    5,    0,    0,    0,    6,    7,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   23,   23,
    0,    0,    0,    0,   39,   39,   39,    0,    0,   29,
   29,   29,    0,    0,   26,   26,   26,    0,   27,   27,
   27,    0,   28,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   28,   28,   28,   62,   63,   64,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
   42,   43,   44,   45,   41,   47,   43,   44,   45,   41,
   44,  123,   44,   41,    0,   40,   44,   40,   60,   61,
   62,   40,    8,   60,   61,   62,    0,   40,   60,   61,
   62,   10,   60,   61,   62,   41,   44,  123,   44,  125,
   43,   10,   45,   40,   42,   44,  123,  271,  272,   47,
   29,   30,  271,  272,   60,   61,   62,   60,   61,   62,
   29,   30,   59,   24,   66,   26,   68,   28,  271,   41,
   44,   43,   43,   45,   45,  274,   37,   38,  266,  267,
   82,  271,   42,   43,   44,   45,   44,   47,  268,  269,
  270,  262,  263,   39,   40,   44,   44,  275,   41,   41,
   41,  271,   59,   89,   65,   59,  271,   59,   41,   41,
  270,  123,   91,   44,  262,   40,  125,   44,   44,   41,
   44,   44,   91,   41,   52,   26,   -1,   -1,   -1,   -1,
   51,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  261,
   -1,   -1,  264,  265,  266,  267,   -1,   -1,   -1,  271,
  272,  273,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  261,   -1,   -1,  264,  265,
  266,  267,   -1,   -1,  261,  271,  272,  264,  265,  266,
  267,   -1,   -1,   -1,  271,  272,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  262,  263,
   -1,   -1,   -1,   -1,  276,  277,  278,   -1,   -1,  276,
  277,  278,   -1,   -1,  276,  277,  278,   -1,  276,  277,
  278,   -1,  275,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  276,  277,  278,  276,  277,  278,
};
}
final static short YYFINAL=9;
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
"sent : decl ','",
"sent : exec ','",
"decl : var",
"decl : func",
"var : type lst_var ','",
"lst_var : ID ';' lst_var",
"lst_var : ID ';'",
"func : type ID '(' type ID ')' '{' sent RETURN '(' expr ')' '}'",
"exec : s_cond",
"exec : block",
"exec : ctrl",
"exec : prt",
"exec : inv_fun",
"exec : expr",
"s_cond : IF '(' cond ')' block ELSE block END_IF",
"s_cond : IF '(' cond ')' block END_IF",
"ctrl : WHILE '(' cond ')' block",
"prt : PRINT '(' STRING ')'",
"cond : expr comp expr",
"block : sent",
"block : '{' sent '}'",
"inv_fun : ID '(' ID ';' lst_perm ')' ','",
"expr : ID \" ASIG \" expr",
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
        yychar = yylex();  //get next token
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
        yychar = yylex();        //get next character
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
public Parser()
{
  //nothing to do
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