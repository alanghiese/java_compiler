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
import utilities.Constants;
import utilities.CTNInformation;
//#line 22 "Parser.java"




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
public final static short ASIG=257;
public final static short IF=258;
public final static short ENDIF=259;
public final static short ELSE=260;
public final static short PRINT=261;
public final static short WHILE=262;
public final static short USINTEGER=263;
public final static short LINTEGER=264;
public final static short READONLY=265;
public final static short WRITE=266;
public final static short PASS=267;
public final static short ID=268;
public final static short CTE=269;
public final static short RETURN=270;
public final static short STRING=271;
public final static short MAY_IG=272;
public final static short MEN_IG=273;
public final static short DIST=274;
public final static short MEN_IG=275;
public final static short MAY_IG=276;
public final static short DIST=277;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    1,    1,    1,    1,    2,    2,
    5,    5,    5,    8,    8,    8,    8,    6,    6,    6,
    6,    6,    9,    9,    9,    9,    9,   10,   10,   10,
   10,   10,   11,   11,   11,   11,   11,   11,    3,    3,
    3,    3,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   14,   14,   14,   14,   14,   19,   15,   15,   15,   15,
   15,   15,   17,   17,   17,   18,   18,   18,   21,   21,
   21,   21,   16,   16,   22,   22,   22,   22,   22,    4,
    4,    4,    4,    4,   12,   12,   12,   24,   24,   24,
   23,   23,   23,   23,   23,    7,    7,   25,   25,   25,
   25,   20,   20,   20,   20,   20,   20,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    1,    1,    1,    2,    1,    1,
    3,    2,    3,    3,    2,    3,    1,    4,    3,    3,
    3,    3,    4,    3,    3,    3,    3,    4,    3,    3,
    5,    3,    5,    4,    4,    4,    4,    4,    1,    1,
    1,    2,    9,    7,    8,    6,    8,    8,    9,    8,
    8,    8,    9,    8,    6,    5,    5,    6,    7,    5,
    5,    5,    4,    5,    6,    1,    5,    4,    4,    4,
    4,    4,    3,    2,    2,    1,    1,    3,    2,    2,
    1,    1,    4,    4,    3,    2,    3,    2,    2,    4,
    3,    3,    3,    3,    3,    3,    1,    3,    3,    1,
    1,    1,    1,    3,    4,    1,    1,    1,    1,    2,
    1,    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,   66,  106,  107,    0,    0,    0,
    0,    0,    5,    6,    7,    9,   10,    0,    0,   39,
   40,   41,    0,    0,    8,    0,    0,  109,    0,    0,
  111,    0,  100,    0,  112,  113,  114,  115,  116,  117,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   15,    0,    0,    0,    2,    3,    4,
    0,    0,    0,    0,    0,    0,   12,   42,    0,    0,
    0,    0,  110,    0,    0,   91,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   16,   93,    0,   14,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   22,
   21,   19,   92,    0,    0,   13,    0,    0,    0,   11,
   20,    0,    0,    0,    0,   95,   96,   98,   99,    0,
    0,   76,   77,    0,    0,    0,    0,    0,   69,   70,
    0,   71,   90,   86,    0,  101,    0,  103,    0,   25,
   88,    0,   26,   84,   83,   24,    0,    0,    0,    0,
    0,    0,    0,   68,    0,   18,    0,    0,   63,   62,
    0,    0,    0,   57,    0,    0,   60,    0,    0,   56,
    0,   67,   87,    0,   85,   23,    0,    0,    0,   32,
    0,   29,   30,    0,    0,   64,    0,   61,    0,    0,
   78,    0,   58,    0,    0,    0,    0,    0,    0,    0,
    0,  104,    0,    0,    0,    0,    0,   28,   46,    0,
   65,    0,    0,    0,   59,   44,    0,    0,    0,    0,
  105,   36,   37,    0,   35,   34,   31,    0,   48,    0,
   50,   52,    0,    0,   51,   47,   33,   45,   49,   53,
   43,
};
final static short yydgoto[] = {                         11,
   98,   13,  122,  123,   16,   17,   18,   19,   51,  101,
  151,   41,   20,   21,   22,   23,   56,  124,   24,   43,
  163,   94,  141,   32,   33,
};
final static short yysindex[] = {                       257,
   -7,   55,  -40,  -37,    0,    0,    0,  126,  -55,  149,
    0,  301,    0,    0,    0,    0,    0,  316,   -3,    0,
    0,    0,    6,  -26,    0,  159,   -9,    0, -196,  387,
    0,   54,    0,  128,    0,    0,    0,    0,    0,    0,
   37,   38,   55,   49,  -39,  -55,   27,  -55, -131,  285,
  333,  414,  -53,    0,   63,   76,   75,    0,    0,    0,
  102,  428, -129, -109,  117,  333,    0,    0,  159,  124,
  150, -228,    0,   55,   55,    0,   55,   55,  407,  -33,
   55,  407,   13,  148,   48,  152,    0,    0,  456,    0,
  -21,   53,  -28,  100,  185,  138,  182,  234,  318,    0,
    0,    0,    0,  153,  407,    0,  333,  160,  165,    0,
    0,  -32,  407,  407,  156,    0,    0,    0,    0,  -30,
  205,    0,    0, -242,  407,  358,   13, -132,    0,    0,
  168,    0,    0,    0,  240,    0,  158,    0,  240,    0,
    0,  177,    0,    0,    0,    0,  111,  -13,  149,  214,
  122,  145,  424,    0,  -85,    0,  407,  415,    0,    0,
  257,  257,  154,    0,  407,  -79,    0,  407,  371,    0,
  407,    0,    0, -190,    0,    0,  -16,   95,   14,    0,
 -114,    0,    0,  254,  407,    0,   74,    0,  301,  301,
    0,   43,    0,  407,   62,  118,  278,  389,   83,   84,
  302,    0,  313,  112,  319,  330,  344,    0,    0,   86,
    0,  355,  103,  356,    0,    0,  366,  -80,  368,  372,
    0,    0,    0,  375,    0,    0,    0,  393,    0,  416,
    0,    0,  425,  430,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,  435,    0,    0,
    0,  433,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,
    0,   24,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  435,    0,    0,    0,    0,    0,    0,    0,
    0,  435,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   10,    0,   11,    0,    0,    0,    0,    0,   47,    0,
    0,    0,    0,    0,  435,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   12,    0,    0,    0,
   70,    0,    0,    0,    0,    0,  161,    0,  231,    0,
    0,  123,    0,    0,    0,    0,   60,    0,    0,    0,
    0,    0,  435,    0,    0,    0,    0,    0,    0,    0,
  326,  360,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  361,  363,
    0,    0,    0,    0,    0,    0,   91,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -113,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  108,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
   16,   18,  592,  654,    0,    0,  715,  508,   31,  -12,
  -72,  698,    0,    0,    0,  693,   85,  634,    0,  -34,
    0,    0,   25,    0,  109,
};
final static int YYTABLESIZE=875;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         34,
  108,   86,   45,    9,   29,   48,   81,  126,  158,   72,
  208,   38,  143,   69,   29,   12,  164,  165,   29,   36,
   35,   37,  134,   97,  203,  152,  177,   91,   29,   58,
   72,   29,   26,   36,   35,   37,   25,  135,  102,  115,
   67,  108,  108,  108,  108,  108,   94,  108,   66,   68,
   75,   74,   73,  111,  206,   74,   74,   75,   75,  108,
  108,  108,  108,   97,   97,  201,   97,   97,   97,   72,
   88,   29,   73,   36,   35,   37,  202,  181,   82,   74,
  100,   75,   97,   97,   97,   97,   94,   42,  131,   84,
   55,  130,  107,  140,  156,   77,   36,   35,   37,   29,
   78,  108,  108,  104,  108,   94,  108,   54,   70,   72,
   71,  139,  150,   26,   26,   58,  105,  211,   80,  108,
  108,  108,   27,  108,   91,  108,  170,  171,   72,   66,
   55,    6,    7,    6,    7,  205,   92,   74,  108,   75,
  145,  207,   38,  144,   81,  106,   97,   54,   97,   55,
   72,  140,  224,  112,   74,  223,   75,   26,  109,  173,
  110,  215,   27,  175,  113,   49,   54,   58,   79,   94,
   29,   94,   29,  184,  185,  233,  189,  190,  234,  193,
  194,   27,   29,  107,   48,  118,  119,   36,   35,   37,
  114,  129,   72,   29,   72,  132,  154,   36,   35,   37,
  140,  102,   46,   29,  102,  146,   58,   58,   36,   35,
   37,  172,   53,   55,  139,   55,  174,  176,   36,   35,
   37,  149,  125,  157,   49,  146,   47,   27,   28,   29,
   54,   85,   54,   44,   38,   39,   40,   27,   28,  142,
    9,   27,   28,   48,   10,   27,  182,   27,   38,   39,
   40,   27,   28,  149,   27,   28,  108,  108,  108,  108,
  108,  108,  108,  108,  108,   75,   74,   73,  108,  183,
  108,   89,    9,  149,   89,  108,  108,  108,  191,   97,
   97,   97,   97,   97,   97,   97,   97,   97,   38,   39,
   40,   97,    9,   97,   27,   28,   10,  209,   97,   97,
   97,  212,   94,   94,   94,   94,   94,   94,   94,   94,
   94,   38,   39,   40,   94,    9,   94,  136,  137,  138,
  214,  216,   27,   28,   96,   72,   72,   72,   72,   72,
   72,   72,   72,   72,  108,  108,  108,   72,  180,   72,
   10,  219,  220,    9,  228,  221,   55,   55,   55,   55,
   55,   55,   55,   55,   55,   63,  222,   63,   55,    9,
   55,  230,  225,   54,   54,   54,   54,   54,   54,   54,
   54,   54,   10,  226,    9,   54,    9,   54,   27,   27,
   27,   46,   47,   27,   27,   27,   27,  227,    6,    7,
   27,    9,   27,   27,   28,   27,   28,   10,  229,  231,
    6,    7,   38,   39,   40,  147,   28,   97,   55,  232,
   10,  235,   38,   39,   40,  236,   27,   28,  237,   55,
  136,  137,  138,   38,   39,   40,   27,   28,   10,   74,
   76,   75,    1,   38,   39,   40,  238,    1,    2,    3,
   46,   47,    4,    5,    6,    7,   10,    6,    7,    8,
   81,  148,   27,   28,   10,   97,   74,  103,   75,  239,
   57,    2,    3,   63,  146,    4,    5,   63,  240,   57,
    2,    3,  120,  241,    4,    5,    6,    7,   17,    0,
  121,    8,   48,  148,   82,   79,   48,   80,    0,   57,
    2,    3,    0,  121,    4,    5,    6,    7,   74,  133,
   75,    8,    0,  148,  136,  137,  138,    0,    0,    0,
    0,  121,    1,    2,    3,    0,   54,    4,    5,    6,
    7,    0,    0,    0,    8,   65,    0,    0,    0,  121,
    0,    0,    0,    0,    0,    0,    0,  121,    0,    0,
    1,    2,    3,    0,    0,    4,    5,    6,    7,    0,
    0,    0,   95,   87,    0,   90,   57,    2,    3,    0,
    0,    4,    5,    6,    7,    0,    0,    0,    8,    0,
    0,   61,    0,   61,    0,    0,    0,    0,    6,    7,
    6,    7,    0,   62,    0,  153,    0,    0,    1,    2,
    3,   14,    0,    4,    5,    6,    7,    0,    0,    0,
    8,    0,    0,   59,    0,    0,   65,    0,    0,    0,
    0,    0,    0,   57,    2,    3,  167,  168,    4,    5,
    0,    0,    0,    0,    0,  120,  196,    2,    3,  197,
  198,    4,    5,    0,    0,    0,    0,    0,  120,    0,
    0,   14,   14,    0,   57,    2,    3,  217,    0,    4,
    5,    0,    0,   15,    0,    0,  120,   14,    0,    0,
    0,    0,   57,    2,    3,   60,    0,    4,    5,    0,
  187,    2,    3,    0,  120,    4,    5,    0,    0,   46,
    0,    0,  120,   46,    0,    0,    6,    7,   14,   59,
    6,    7,    0,    0,   31,   31,    0,    0,   14,   30,
   31,    0,   31,   15,   15,   52,    0,    0,    0,    0,
    0,    0,  161,    0,    0,  128,   31,    0,   31,   15,
    0,    0,   50,    0,    0,    0,   31,    0,    0,    0,
    0,    0,   64,    0,    0,   31,    0,    0,  155,   31,
   83,   59,    0,    0,   89,    0,  159,  160,    0,    0,
   15,   60,   14,   14,    0,    0,    0,    0,  166,  169,
   15,   31,    0,   93,   99,    0,   31,   31,    0,   31,
   31,  116,  117,   31,  162,    0,   64,   93,  127,    0,
   59,   59,    0,    0,    0,    0,    0,   31,   31,    0,
  186,  188,   52,    0,    0,    0,    0,    0,  192,    0,
    0,  195,  199,   60,  200,    0,    0,    0,    0,   50,
   93,    0,   31,   64,   15,   15,    0,   52,  210,    0,
    0,    0,    0,    0,    0,    0,    0,  213,    0,    0,
    0,  218,    0,    0,    0,    0,    0,    0,    0,    0,
   31,   31,   60,   60,    0,  178,  179,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   64,    0,   31,
    0,    0,    0,    0,  204,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   41,   40,   59,   45,   59,   41,   41,   41,   40,
  125,  125,   41,   40,   45,    0,  259,  260,   45,   60,
   61,   62,   44,    0,   41,   98,   40,  256,   45,   12,
   40,   45,   40,   60,   61,   62,   44,   59,   51,  268,
   44,   41,   42,   43,   44,   45,    0,   47,   18,   44,
   41,   41,   41,   66,   41,   43,   43,   45,   45,   59,
   60,   61,   62,   40,   41,  256,   43,   44,   45,    0,
   44,   45,  269,   60,   61,   62,  267,  150,   41,   43,
   50,   45,   59,   60,   61,   62,   40,    3,   41,   41,
    0,   44,   62,   41,  107,   42,   60,   61,   62,   45,
   47,   42,   43,   41,   45,   59,   47,    0,   24,   40,
   26,   59,   97,   40,   40,   98,   41,   44,   34,   60,
   61,   62,    0,  123,  256,  125,  259,  260,   59,   99,
   40,  263,  264,  263,  264,   41,  268,   43,  268,   45,
   41,  256,  256,   44,  179,   44,  123,   40,  125,   59,
   40,   41,   41,   69,   43,   44,   45,   40,  268,  135,
   44,   44,   40,  139,   41,   40,   59,  150,   41,  123,
   45,  125,   45,  259,  260,  256,  161,  162,  259,  259,
  260,   59,   45,  153,   59,   77,   78,   60,   61,   62,
   41,   44,  123,   45,  125,   44,   44,   60,   61,   62,
   41,   41,  256,   45,   44,   41,  189,  190,   60,   61,
   62,   44,  268,  123,   59,  125,   59,   41,   60,   61,
   62,   40,  256,  256,   40,   41,  257,  268,  269,   45,
  123,  271,  125,  271,  275,  276,  277,  268,  269,  268,
   59,  268,  269,   59,   40,  123,  125,  125,  275,  276,
  277,  268,  269,   40,  268,  269,  256,  257,  258,  259,
  260,  261,  262,  263,  264,  256,  256,  256,  268,  125,
  270,   41,   59,   40,   44,  275,  276,  277,  125,  256,
  257,  258,  259,  260,  261,  262,  263,  264,  275,  276,
  277,  268,   59,  270,  268,  269,   40,   44,  275,  276,
  277,  259,  256,  257,  258,  259,  260,  261,  262,  263,
  264,  275,  276,  277,  268,   59,  270,  265,  266,  267,
  259,   44,  268,  269,   40,  256,  257,  258,  259,  260,
  261,  262,  263,  264,  275,  276,  277,  268,  125,  270,
   40,  259,  259,   59,  259,   44,  256,  257,  258,  259,
  260,  261,  262,  263,  264,   40,   44,   40,  268,   59,
  270,  259,   44,  256,  257,  258,  259,  260,  261,  262,
  263,  264,   40,   44,   59,  268,   59,  270,  256,  257,
  258,  256,  257,  261,  262,  263,  264,   44,  263,  264,
  268,   59,  270,  268,  269,  268,  269,   40,   44,   44,
  263,  264,  275,  276,  277,  268,  269,  123,  271,   44,
   40,   44,  275,  276,  277,   44,  268,  269,   44,  271,
  265,  266,  267,  275,  276,  277,  268,  269,   40,   43,
   44,   45,    0,  275,  276,  277,   44,  256,  257,  258,
  256,  257,  261,  262,  263,  264,   40,  263,  264,  268,
  125,  270,  268,  269,   40,  123,   43,   44,   45,   44,
  256,  257,  258,   40,   41,  261,  262,   40,   44,  256,
  257,  258,  268,   44,  261,  262,  263,  264,   44,   -1,
  123,  268,   59,  270,  125,  125,   59,  125,   -1,  256,
  257,  258,   -1,  123,  261,  262,  263,  264,   43,   44,
   45,  268,   -1,  270,  265,  266,  267,   -1,   -1,   -1,
   -1,  123,  256,  257,  258,   -1,    9,  261,  262,  263,
  264,   -1,   -1,   -1,  268,   18,   -1,   -1,   -1,  123,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,   -1,
  256,  257,  258,   -1,   -1,  261,  262,  263,  264,   -1,
   -1,   -1,  268,   46,   -1,   48,  256,  257,  258,   -1,
   -1,  261,  262,  263,  264,   -1,   -1,   -1,  268,   -1,
   -1,  256,   -1,  256,   -1,   -1,   -1,   -1,  263,  264,
  263,  264,   -1,  268,   -1,  268,   -1,   -1,  256,  257,
  258,    0,   -1,  261,  262,  263,  264,   -1,   -1,   -1,
  268,   -1,   -1,   12,   -1,   -1,   99,   -1,   -1,   -1,
   -1,   -1,   -1,  256,  257,  258,  259,  260,  261,  262,
   -1,   -1,   -1,   -1,   -1,  268,  256,  257,  258,  259,
  260,  261,  262,   -1,   -1,   -1,   -1,   -1,  268,   -1,
   -1,   50,   51,   -1,  256,  257,  258,  259,   -1,  261,
  262,   -1,   -1,    0,   -1,   -1,  268,   66,   -1,   -1,
   -1,   -1,  256,  257,  258,   12,   -1,  261,  262,   -1,
  256,  257,  258,   -1,  268,  261,  262,   -1,   -1,  256,
   -1,   -1,  268,  256,   -1,   -1,  263,  264,   97,   98,
  263,  264,   -1,   -1,    2,    3,   -1,   -1,  107,    2,
    8,   -1,   10,   50,   51,    8,   -1,   -1,   -1,   -1,
   -1,   -1,  121,   -1,   -1,   82,   24,   -1,   26,   66,
   -1,   -1,    8,   -1,   -1,   -1,   34,   -1,   -1,   -1,
   -1,   -1,   18,   -1,   -1,   43,   -1,   -1,  105,   47,
   43,  150,   -1,   -1,   47,   -1,  113,  114,   -1,   -1,
   97,   98,  161,  162,   -1,   -1,   -1,   -1,  125,  126,
  107,   69,   -1,   49,   50,   -1,   74,   75,   -1,   77,
   78,   74,   75,   81,  121,   -1,   62,   63,   81,   -1,
  189,  190,   -1,   -1,   -1,   -1,   -1,   95,   96,   -1,
  157,  158,   95,   -1,   -1,   -1,   -1,   -1,  165,   -1,
   -1,  168,  169,  150,  171,   -1,   -1,   -1,   -1,   95,
   96,   -1,  120,   99,  161,  162,   -1,  120,  185,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  194,   -1,   -1,
   -1,  198,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  148,  149,  189,  190,   -1,  148,  149,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  153,   -1,  177,
   -1,   -1,   -1,   -1,  177,
};
}
final static short YYFINAL=11;
final static short YYMAXTOKEN=277;
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
null,null,null,null,null,null,null,"ASIG","IF","ENDIF","ELSE","PRINT","WHILE",
"USINTEGER","LINTEGER","READONLY","WRITE","PASS","ID","CTE","RETURN","STRING",
"MAY_IG","MEN_IG","DIST","\"MEN_IG\"","\"MAY_IG\"","\"DIST\"",
};
final static String yyrule[] = {
"$accept : prog",
"prog : sent",
"sent : sent decl",
"sent : sent exec",
"sent : sent asig",
"sent : decl",
"sent : exec",
"sent : asig",
"sent : error ','",
"decl : var",
"decl : func",
"var : type lst_var ','",
"var : lst_var ','",
"var : type error ','",
"lst_var : ID ';' lst_var",
"lst_var : ';' lst_var",
"lst_var : ID error lst_var",
"lst_var : ID",
"func : type ID param func_body",
"func : ID param func_body",
"func : type param func_body",
"func : ID type func_body",
"func : ID type param",
"param : '(' type ID ')'",
"param : type ID ')'",
"param : '(' ID ')'",
"param : '(' type ')'",
"param : '(' type ID",
"func_body : '{' sent ret '}'",
"func_body : '{' ret '}'",
"func_body : sent ret '}'",
"func_body : '{' sent ret error ','",
"func_body : '{' sent '}'",
"ret : RETURN '(' expr ')' ','",
"ret : '(' expr ')' ','",
"ret : RETURN expr ')' ','",
"ret : RETURN '(' ')' ','",
"ret : RETURN '(' expr ','",
"ret : RETURN '(' expr ')'",
"exec : s_cond",
"exec : ctrl",
"exec : prt",
"exec : inv_fun ','",
"s_cond : IF '(' cond ')' ctrl_blck ELSE ctrl_blck ENDIF ','",
"s_cond : IF '(' cond ')' ctrl_blck ENDIF ','",
"s_cond : '(' cond ')' ctrl_blck ELSE ctrl_blck ENDIF ','",
"s_cond : '(' cond ')' ctrl_blck ENDIF ','",
"s_cond : IF cond ')' ctrl_blck ELSE ctrl_blck ENDIF ','",
"s_cond : IF '(' ')' ctrl_blck ELSE ctrl_blck ENDIF ','",
"s_cond : IF '(' cond error ctrl_blck ELSE ctrl_blck ENDIF ','",
"s_cond : IF '(' cond ')' ELSE ctrl_blck ENDIF ','",
"s_cond : IF '(' cond ')' ctrl_blck ctrl_blck ENDIF ','",
"s_cond : IF '(' cond ')' ctrl_blck ELSE ENDIF ','",
"s_cond : IF '(' cond ')' ctrl_blck ELSE ctrl_blck error ','",
"s_cond : IF '(' cond ')' ctrl_blck ELSE ctrl_blck ENDIF",
"s_cond : IF '(' cond ')' ctrl_blck ENDIF",
"s_cond : IF cond ')' ctrl_blck ENDIF",
"s_cond : IF '(' ')' ctrl_blck ENDIF",
"s_cond : IF '(' cond error ctrl_blck ENDIF",
"s_cond : IF '(' cond ')' ctrl_blck error ','",
"s_cond : IF '(' cond ')' ENDIF",
"ctrl : while '(' cond ')' ctrl_blck",
"ctrl : error '(' cond ')' ctrl_blck",
"ctrl : while cond ')' ctrl_blck",
"ctrl : while '(' cond error ctrl_blck",
"ctrl : while '(' cond ')' error ','",
"while : WHILE",
"prt : PRINT '(' STRING ')' ','",
"prt : '(' STRING ')' ','",
"prt : PRINT STRING ')' ','",
"prt : PRINT '(' STRING ','",
"prt : PRINT '(' ')' ','",
"prt : PRINT '(' STRING ')'",
"cond : expr comp expr",
"cond : comp expr",
"cond : expr comp",
"ctrl_blck : exec",
"ctrl_blck : asig",
"ctrl_blck : '{' ctrl_sent '}'",
"ctrl_sent : exec sent",
"ctrl_sent : asig sent",
"ctrl_sent : exec",
"ctrl_sent : asig",
"inv_fun : ID '(' func_var ')'",
"inv_fun : ID '(' func_var ','",
"func_var : ID ';' lst_perm",
"func_var : error ','",
"func_var : error ';' lst_perm",
"func_var : ID lst_perm",
"func_var : ID ';'",
"asig : ID ASIG expr ','",
"asig : ASIG expr ','",
"asig : ID expr ','",
"asig : ID ASIG ','",
"asig : ID ASIG expr",
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
"lst_perm : WRITE ';' error ','",
"type : USINTEGER",
"type : LINTEGER",
"fact : ID",
"fact : CTE",
"fact : '-' CTE",
"fact : inv_fun",
"comp : '='",
"comp : '<'",
"comp : '>'",
"comp : \"MEN_IG\"",
"comp : \"MAY_IG\"",
"comp : \"DIST\"",
};

//#line 364 "gramatica"

LexicalAnalizer la;
List<Triples> triples= new LinkedList();
List<Triples> pendingTriples= new LinkedList();
List<String> varList = new LinkedList();
int functionAllows=-1;
List<String> paramList = new LinkedList();
List<Permissions> permsList = new LinkedList();
List<String> typeRet = new LinkedList();

public Parser(String path) {
		la = new LexicalAnalizer(path);
}

public void yyerror(int l, String s){

    System.out.println("Sintax Error: Line " + l + " - " + s);

}


public boolean ifUS(Token t) {
		//System.out.println(t.getLex());
		if (((CTNInformation) LexicalAnalizer.symbolTable.getLexeme(t.getLex())).getType().equals(Constants.US_INT))
			return true;
		return false;
	}

public void yyerror(String s){

    System.out.println(s);

}

public void print(String s){
    System.out.println(s);
}

public boolean correctCall(Boolean converted, Operand param, String functionName, String paramName, int line){
    if(la.symboltable.isFunction(functionName)){
    	if (la.symboltable.paramAllow(functionName,functionAllows)){
    		if(!LexicalAnalizer.symboltable.isVar(paramName)){
    			yyerror(line, "Semantic Error: Parameto inexistente");
    			return false;
    		}
    		if(!LexicalAnalizer.symboltable.getType(paramName).equals(LexicalAnalizer.symboltable.getParamType(functionName)))
	    		if(!TypeConverter.isValid(LexicalAnalizer.symboltable.getType(paramName),LexicalAnalizer.symboltable.getParamType(functionName) )){
	    			yyerror(line, "Semantic Error: Tipo del parametro incompatible");
	    			return false;
	    		}
	    		else{
	    			converted = true;
	    			Triples cnv = new TrCNV(param);
	    			triples.add(cnv);
	    			return true;
	    		}
	    	else
	    		return true;

    	}
    	else yyerror(line, "Semantic Error: Pasaje de parametro invalido");
        
    }
    else yyerror(line, "Semantic Error: Funcion no declarada");
    return false;
}

public void testPos(Token t) {
		Long l = Long.parseLong(t.getLex());
		if (l.compareTo((long)Integer.MAX_VALUE)>0) {
			l = (long)Integer.MAX_VALUE;
			if (((CTNInformation)LexicalAnalizer.symbolTable.getLexeme(t.getLex())).getCounter()>1) {
				((CTNInformation)LexicalAnalizer.symbolTable.getLexeme(t.getLex())).decreaseCounter();
				CTNInformation ctni = new CTNInformation();
				ctni.setType(Constants.L_INT);
				LexicalAnalizer.symbolTable.addCTN(l.toString(), ctni);
			}
			else {
				LexicalAnalizer.symbolTable.removeCTN(t.getLex());
				CTNInformation ctni = new CTNInformation();
				ctni.setType(Constants.L_INT);
				LexicalAnalizer.symbolTable.addCTN(l.toString(), ctni);
			}
		}
			
	}
	
	public void testNeg(Token t) {
		Long l = Long.parseLong(t.getLex())*-1;
		CTNInformation ctni = new CTNInformation();
		ctni.setType(Constants.L_INT);
		if (((CTNInformation)LexicalAnalizer.symbolTable.getLexeme(t.getLex())).getCounter()>1)
			((CTNInformation)LexicalAnalizer.symbolTable.getLexeme(t.getLex())).decreaseCounter();
		else
			LexicalAnalizer.symbolTable.removeCTN(t.getLex());
		LexicalAnalizer.symbolTable.addCTN(l.toString(), ctni);
		
	}

        public void blockFound(){
            Triples t = new TrBI();
            triples.add(t);
            Triple label= new Label();
            triples.add(label);
            pendingTriples.removeLast().setO1(label);
            pendingTriples.add(triples.get(pendingTriples.size()-2));
        }

        public void completeLast(Triples t){
            pendingTriples.removeLast().setO1(t);
        }

        public void setTypes(String type, int lineNumber){
            for(String var: varList)
                if(!la.symbolTable.isFunction(var)){
                    la.symbolTable.setType(var, type);
                }else{
                    yyerror(lineNumber, "El id "+ var +" ya fue usado como función");
                }
            varList.clear();
        }

        public void getConversion(Operand in1, Operand in2,Operand out1, Operand out2){

            if (!in1.getType().equals(in2.getType()){
            	Triples cnv;
            	if (!(in1.getType().equals("usinteger")){
            		cnv= new TrCNV(in1);
            		out1=cnv;
            		out2=in2;
            	}else{
            		cnv= new TrCNV(in3);
            		out1=in1;
            		out2=cnv;
            	}
            }else{
            	out1=in1;
            	out2=in2;
            }
        }

public void gt() {
		String path = "/home/alan/Documents";
		LexicalAnalizer.symbolTable.genOutput(path);
	}

//#line 712 "Parser.java"
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
case 1:
//#line 14 "gramatica"
{print("Sintaxis: Codigo reconocido");}
break;
case 8:
//#line 23 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "");}
break;
case 11:
//#line 30 "gramatica"
{print("Sintaxis: Declaracion de variable");
                            setTypes(((Token)(val_peek(2).obj)).getLex(),(Token)(val_peek(2).obj).getLine());}
break;
case 12:
//#line 32 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta especificar el tipo de la variable");}
break;
case 13:
//#line 33 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Error en la declaracion de variable/s");}
break;
case 14:
//#line 36 "gramatica"
{varList.add(((Token) (val_peek(2).obj)).getLex());}
break;
case 15:
//#line 37 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta identificador antes del ';'");}
break;
case 16:
//#line 38 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta ';'");}
break;
case 17:
//#line 39 "gramatica"
{varList.add(((Token) (val_peek(0).obj)).getLex());}
break;
case 18:
//#line 42 "gramatica"
{print("Sintaxis: Declaracion de funcion");
                                        if(!la.symbolTable.isVar((Token)(val_peek(2).obj).getLex())){
                                        	if(TypeConverter.isValid(((Token)val_peek(3).obj).getType(),typeRet)){
	                                            la.symbolTable.removeID((Token)(val_peek(2).obj).getLex());
	                                            FNCInformation fnc = new FNCInformation();
	                                            fnc.setType((Token)(val_peek(3).obj).getLex());
	                                            fnc.setParamType(((Token)val_peek(1).obj).getLex());
	                                            la.symbolTable.addFNC((Token)(val_peek(2).obj).getLex(), fnc);
	                                            la.symbolTable.setType((Token)(val_peek(2).obj).getLex(),(Token)(val_peek(3).obj).getLex());
	                                            
                                            	if(!((Token)val_peek(3).obj).getType().equals(typeRet)){
                                            		Triples cnv = TrCNV(((Triples)val_peek(0).obj).getO1());
                                            		cnv.setId(((Triples)val_peek(0).obj).getId());
                                            		triples.add(triples.size()-2,cnv);
                                            		((Triples)val_peek(0).obj).setId(((Triples)val_peek(0).obj).getId()+1);
                                            		((Triples)val_peek(0).obj).setO1(cnv);

                                            	}
                                            }
                                            else
                                            	yyerror((Token)(val_peek(3).obj).getLine(), "Semantic Error: Tipo de retorno incompatible")
                                        }else{
                                            yyerror((Token)(val_peek(3).obj).getLine(), "Semantic Error: El id "+ var +" ya fue usado como variable");
                                        }
                                        /*remuevo el parametro para esta funcion en particular (considero funciones anidadas)*/
                                        listParam.removeLast();}
break;
case 19:
//#line 68 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta el tipo de retorno de la funcion");}
break;
case 20:
//#line 69 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "No se permiten funciones anonimas");}
break;
case 21:
//#line 70 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta el argumento de la funcion");}
break;
case 22:
//#line 71 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta cuerpo de la funcion");}
break;
case 23:
//#line 74 "gramatica"
{   Triples t= new TrFUN();
                                        triples.add(t);
                                        permList.addLast(new Permissions(false,false));
                                        paramList.addLast(((Token) val_peek(1).obj).getLex());   
                                        yyval=val_peek(2); 
                                     }
break;
case 24:
//#line 80 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta '('");}
break;
case 25:
//#line 81 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta especificar el tipo del parametro de la funcion");}
break;
case 26:
//#line 82 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta ID como parametro de la funcion");}
break;
case 27:
//#line 83 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta ')'");}
break;
case 28:
//#line 86 "gramatica"
{yyval=val_peek(1)}
break;
case 29:
//#line 87 "gramatica"
{yyval=val_peek(1)}
break;
case 30:
//#line 88 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta '{'");}
break;
case 31:
//#line 89 "gramatica"
{yyerror(((Token) val_peek(4).obj).getLine(), "Falta '}'");}
break;
case 32:
//#line 90 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta sentencia de retorno");}
break;
case 33:
//#line 93 "gramatica"
{Triples t = new TrRET((Operand)val_peek(2).obj);
                                     triples.add(t);
                                     typeRet.add(((Operand)val_peek(2).obj).getType());
                                     yyval = new ParserVal(t);}
break;
case 34:
//#line 97 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Retorno de funcion sin 'RETURN'");}
break;
case 35:
//#line 98 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Falta '('");}
break;
case 36:
//#line 99 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta valor de retorno de la funcion");}
break;
case 37:
//#line 100 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta ')'");}
break;
case 38:
//#line 101 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta ','");}
break;
case 43:
//#line 110 "gramatica"
{print("Sintaxis: Sentencia IF-ELSE");
                                                                pendingTriples.removeLast();
                                                                triples.remove(triples.size()-2);
                                                                triples.getLast().decreaseId();
                                                            	}
break;
case 44:
//#line 115 "gramatica"
{print("Sintaxis: Sentencia IF");
                                                                pendingTriples.removeLast();
                                                                triples.getLast().decreaseId();
                                                                triples.removeLast();}
break;
case 45:
//#line 119 "gramatica"
{yyerror(((Token) val_peek(7).obj).getLine(), "'ELSE' sin 'IF'");}
break;
case 46:
//#line 120 "gramatica"
{yyerror(((Token) val_peek(5).obj).getLine(), "'ENDIF' sin 'IF'");}
break;
case 47:
//#line 121 "gramatica"
{yyerror(((Token) val_peek(7).obj).getLine(), "Falta '(' en condicion de sentencia condicional");}
break;
case 48:
//#line 122 "gramatica"
{yyerror(((Token) val_peek(6).obj).getLine(), "condicion faltante en sentencia condicional");}
break;
case 49:
//#line 123 "gramatica"
{yyerror(((Token) val_peek(7).obj).getLine(), "Falta ')' en la sentencia condicional");}
break;
case 50:
//#line 124 "gramatica"
{yyerror(((Token) val_peek(4).obj).getLine(), "Sentencia/s faltante/s luego de 'IF'");}
break;
case 51:
//#line 125 "gramatica"
{yyerror(((Token) val_peek(4).obj).getLine(), "Falta 'ELSE' o sobran llaves en la declaracion de bloque");}
break;
case 52:
//#line 126 "gramatica"
{yyerror(((Token) val_peek(5).obj).getLine(), "Bloque faltante luego de 'ELSE'");}
break;
case 53:
//#line 127 "gramatica"
{yyerror(((Token) val_peek(6).obj).getLine(), "Falta 'ENDIF'");}
break;
case 54:
//#line 128 "gramatica"
{yyerror(((Token) val_peek(5).obj).getLine(), "Falta ','");}
break;
case 55:
//#line 129 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Falta ','");}
break;
case 56:
//#line 130 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Falta '('");}
break;
case 57:
//#line 131 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Falta condicion en la sentencia condicional");}
break;
case 58:
//#line 132 "gramatica"
{yyerror(((Token) val_peek(4).obj).getLine(), "Falta ')'");}
break;
case 59:
//#line 133 "gramatica"
{yyerror(((Token) val_peek(4).obj).getLine(), "Falta 'ENDIF' en sentencia condicional");}
break;
case 60:
//#line 134 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta bloque de sentencias en sentencia condicional");}
break;
case 61:
//#line 137 "gramatica"
{print("Sintaxis: Sentencia While");
                                                     completeLast((Triples)val_peek(2).obj);}
break;
case 62:
//#line 139 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "'SENTENCIA DE CONTROL' desconocida");}
break;
case 63:
//#line 140 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Falta'('");}
break;
case 64:
//#line 141 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Falta ')'");}
break;
case 65:
//#line 142 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Sentencia de control sin bloque de sentencias");}
break;
case 66:
//#line 145 "gramatica"
{Triple label=new Label();
                    triples.add(label);
                    pendingTriples.add(label);}
break;
case 67:
//#line 150 "gramatica"
{print("Sintaxis: Sentencia print");
                                    Triples aux = new TrPRT((Operand)val_peek(2).obj);
                                    tercets.add(aux);
                                    yyval = new ParserVal(aux);}
break;
case 68:
//#line 154 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "'PRINT' faltante en la sentencia de impresion");}
break;
case 69:
//#line 155 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta '('");}
break;
case 70:
//#line 156 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta ')'");}
break;
case 71:
//#line 157 "gramatica"
{print("Sintaxis: Sentencia print");
                                    Triples aux= new TrPRT();
                                    triples.add(aux);
                                    yyval = new ParserVal(aux);
                                    }
break;
case 72:
//#line 162 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta ','");}
break;
case 73:
//#line 165 "gramatica"
{   if (TypeConverter.isValidBidirectional(((Operand)val_peek(2)).getType(),((Operand)val_peek(1)).getType()){
									
		                            Triples aux;
		                            Operand o1;
		                            Operand o2;

		                            getConversion((Operand)val_peek(2).obj),(Operand)val_peek(0).obj),o1,o2);

	                                switch (((Token)val_peek(1).obj).getLex()){
	                                    case "<": aux = new TrLT(o1,o2);
	                                    case ">": aux = new TrGT((o1,o2));
	                                    case "<=": aux = new TrLE(o1,o2);
	                                    case ">=": aux = new TrGE(o1,o2);
	                                    case "=": aux = new TrEQ(o1,o2);
	                                    case "!=": aux = new TrNE(o1,o2);
	                                }
	                                triples.add(aux);
	                                Triples t = new TrBF(null, aux);
	                                triples.add(t);
	                                pendingTriples.add(triples.getLast());
	                                yyval = new ParserVal(aux);
								}

                                else
                                	yyerror(((Token)val_peek(1).obj).getLine(),"Semantic Error: Tipos incompatibles en comparacion");
                            }
break;
case 74:
//#line 191 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta expresion del lado izquierdo del comparador");}
break;
case 75:
//#line 192 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta expresion del lado derecho del comparador");}
break;
case 76:
//#line 195 "gramatica"
{blockFound();}
break;
case 77:
//#line 196 "gramatica"
{blockFound();}
break;
case 78:
//#line 197 "gramatica"
{blockFound();}
break;
case 83:
//#line 206 "gramatica"
{	Boolean converted = false;
									if(correctCall(converted, (Operand)val_peek(1).obj,((Token)(val_peek(3).obj.getLexeme()),((Token)val_peek(1).obj)).getLexeme(),((Token)(val_peek(3).obj.getLine()) )){
                                    print("Sintaxis: Invocacion a funcion");
                                    Operand param;
                                    if (converted.booleanValue())
                                    	param=tercets.getLast();
                                    else
                                    	param=(Operand)val_peek(1).obj;
                                    Triples t= new TrINV((Token)val_peek(3).obj,param); 
                                    triples.add(t);
                                    if (!paramList.isEmpty()){
                                         if ( ((FNCInformation)LexicalAnalizer.symbolTable.getLexeme(((Token) val_peek(3).obj).getLex())).isWrite() )
                                             permList.getLast().setWrite(true);
                                          if (((Token) val_peek(3).obj).getLex().equals(paramList.getFirst()))
                                             permList.getLast().setPass(true);                                   
                                     }                                    
                                 }
                                }
break;
case 84:
//#line 224 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta ')'");}
break;
case 85:
//#line 228 "gramatica"
{yyval=val_peek(2)}
break;
case 86:
//#line 229 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Argumentos mal definidos");}
break;
case 87:
//#line 230 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Llamado a funcion sin argumento");}
break;
case 88:
//#line 231 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta ';'");}
break;
case 89:
//#line 232 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Especificacion de permisos faltante");}
break;
case 90:
//#line 235 "gramatica"
{if(TypeConverter.isValid(((Token) val_peek(1).obj).getType(),((Token) val_peek(3).obj).getType())){
 										Triples aux;
										if (!((Token) val_peek(3).obj).getType().equals((Token) val_peek(1).obj).getType()  ){
                                            	Triples cnv = new TrCNV((Token)val_peek(3).obj);
                                                triples.add(cnv);
                                                aux = new TrASG((Operand)val_peek(3).obj,cnv);
                                            }
                                         else {  
		                                    aux = new TrASG((Operand)val_peek(3).obj,(Operand)val_peek(1).obj);
		                                    }
		                             triples.add(aux);
                                    yyval = new ParserVal(aux);
                                     if (!paramList.isEmpty())
                                         if (((Token) val_peek(3).obj).getLex().equals(paramList.getFirst()))
                                             permList.getLast().setWrite(true);
                                     print("Sintaxis: Asignacion");
                                     }
                                    else
                                       yyerror(((Token) val_peek(3).obj).getLine(),"Semantic Error: Tipo invalido en la asignacion :(");}
break;
case 91:
//#line 254 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Asignacion sin id del lado izq");}
break;
case 92:
//#line 255 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Asignacion sin :=");}
break;
case 93:
//#line 256 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta expresion del lado derecho del :=");}
break;
case 94:
//#line 257 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta ','");}
break;
case 95:
//#line 261 "gramatica"
{if (TypeConverter.isValidBidirectional(((Operand)val_peek(2)).getType(),((Operand)val_peek(1)).getType()){
	                        Operand o1;
	                        Operand o2;

	                        getConversion((Operand)val_peek(2).obj),(Operand)val_peek(0).obj),o1,o2);
	                            
							Triples aux = new TrADD(o1,o2);
                            triples.add(aux);
                            yyval = new ParserVal(aux);

							}else{
								yyerror(((Token)val_peek(1).obj).getLine(),"Semantic Error: Tipos incompatibles en suma");
							}
                        }
break;
case 96:
//#line 275 "gramatica"
{if (TypeConverter.isValidBidirectional(((Operand)val_peek(2)).getType(),((Operand)val_peek(0)).getType()){
	                        Operand o1;
	                        Operand o2;

	                        getConversion((Operand)val_peek(2).obj),(Operand)val_peek(0).obj),o1,o2);
	                            
							Triples aux = new TrSUB(o1,o2);
                            triples.add(aux);
                            yyval = new ParserVal(aux);

							}else{
								yyerror(((Token)val_peek(1).obj).getLine(),"Semantic Error: Tipos incompatibles en resta");
							}
							}
break;
case 98:
//#line 292 "gramatica"
{if (TypeConverter.isValidBidirectional(((Operand)val_peek(2)).getType(),((Operand)val_peek(1)).getType()){
	                        Operand o1;
	                        Operand o2;

	                        getConversion((Operand)val_peek(2).obj),(Operand)val_peek(0).obj),o1,o2);
	                            
							Triples aux = new TrMUL(o1,o2);
                            triples.add(aux);
                            yyval = new ParserVal(aux);

							}else{
								yyerror(((Token)val_peek(1).obj).getLine(),"Semantic Error: Tipos incompatibles en multiplicacion");
							}
							}
break;
case 99:
//#line 306 "gramatica"
{if (TypeConverter.isValidBidirectional(((Operand)val_peek(2)).getType(),((Operand)val_peek(1)).getType()){
	                        Operand o1;
	                        Operand o2;

	                        getConversion((Operand)val_peek(2).obj),(Operand)val_peek(0).obj),o1,o2);
	                            
							Triples aux = new TrDIV(o1,o2);
                            triples.add(aux);
                            yyval = new ParserVal(aux);

							}else{
								yyerror(((Token)val_peek(1).obj).getLine(),"Semantic Error: Tipos incompatibles en division");
							}}
break;
case 101:
//#line 322 "gramatica"
{functionAllows=Constants.FUNC_ALLOW_READ;}
break;
case 102:
//#line 323 "gramatica"
{functionAllows=Constants.FUNC_ALLOW_WRITE;}
break;
case 103:
//#line 324 "gramatica"
{functionAllows=Constants.FUNC_ALLOW_PASS;}
break;
case 104:
//#line 325 "gramatica"
{functionAllows=Constants.FUNC_ALLOW_WRITEPASS;}
break;
case 109:
//#line 334 "gramatica"
{/*numero es positivo*/
				Object obj = val_peek(0).obj;
				if (!ifUS(((Token) obj)))
					testPos((Token) obj);}
break;
case 110:
//#line 338 "gramatica"
{
                      Object obj = val_peek(0).obj;
				if (ifUS(((Token) obj))){
					yyerror("Constante Unsigned negativa");
                    if (((CTNInformation)LexicalAnalizer.symbolTable.getLexeme(((Token)obj).getLex())).getCounter()>1) 
						((CTNInformation)LexicalAnalizer.symbolTable.getLexeme(((Token)obj).getLex())).decreaseCounter();
					else
						LexicalAnalizer.symbolTable.removeCTN(((Token)obj).getLex());
                    }
				else
					testNeg(((Token) obj));
                        }
break;
//#line 1389 "Parser.java"
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
