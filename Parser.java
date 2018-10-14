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
   14,   14,   14,   14,   14,   15,   15,   15,   15,   15,
   15,   17,   17,   17,   18,   18,   18,   20,   20,   20,
   20,   16,   16,   21,   21,   21,   21,   21,    4,    4,
    4,    4,    4,   12,   12,   12,   23,   23,   23,   22,
   22,   22,   22,   22,    7,    7,   24,   24,   24,   24,
   19,   19,   19,   19,   19,   19,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    1,    1,    1,    2,    1,    1,
    3,    2,    3,    3,    2,    3,    1,    4,    3,    3,
    3,    3,    4,    3,    3,    3,    3,    4,    3,    3,
    5,    3,    5,    4,    4,    4,    4,    4,    1,    1,
    1,    2,    9,    7,    8,    6,    8,    8,    9,    8,
    8,    8,    9,    8,    6,    5,    5,    6,    7,    5,
    5,    5,    4,    5,    6,    5,    4,    4,    4,    4,
    4,    3,    2,    2,    1,    1,    3,    2,    2,    1,
    1,    4,    4,    3,    2,    3,    2,    2,    4,    3,
    3,    3,    3,    3,    3,    1,    3,    3,    1,    1,
    1,    1,    3,    4,    1,    1,    1,    1,    2,    1,
    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,  105,  106,    0,    0,    0,
    0,    0,    5,    6,    7,    9,   10,    0,    0,   39,
   40,   41,    0,    8,    0,    0,  108,    0,    0,  110,
    0,   99,    0,  111,  112,  113,  114,  115,  116,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   15,    0,    0,    0,    2,    3,
    4,    0,    0,    0,    0,    0,    0,   12,   42,    0,
    0,  109,    0,    0,   90,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   16,   92,    0,
   14,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   22,   21,   19,   91,    0,    0,   13,    0,    0,    0,
   11,   20,    0,    0,   94,   95,   97,   98,    0,    0,
   75,   76,    0,    0,    0,    0,    0,   68,   69,    0,
   70,    0,    0,   63,   89,   85,    0,  100,    0,  102,
    0,   25,   87,    0,   26,   83,   82,   24,    0,    0,
    0,    0,    0,    0,    0,   67,    0,   18,   62,    0,
    0,    0,   57,    0,    0,   60,    0,    0,   56,    0,
   66,   64,    0,   61,   86,    0,   84,   23,    0,    0,
    0,   32,    0,   29,   30,    0,    0,    0,    0,   77,
    0,   58,    0,    0,    0,    0,    0,    0,    0,   65,
    0,  103,    0,    0,    0,    0,    0,   28,   46,    0,
    0,    0,    0,   59,   44,    0,    0,    0,    0,  104,
   36,   37,    0,   35,   34,   31,    0,   48,    0,   50,
   52,    0,    0,   51,   47,   33,   45,   49,   53,   43,
};
final static short yydgoto[] = {                         11,
   99,   13,  121,  122,   16,   17,   18,   19,   52,  102,
  153,   40,   20,   21,   22,   23,   57,  123,   42,  162,
   95,  143,   31,   32,
};
final static short yysindex[] = {                       257,
   -7,   55,  -40,  -37,  -26,    0,    0,  126,  -55,  149,
    0,  301,    0,    0,    0,    0,    0,  316,   22,    0,
    0,    0,   74,    0,  159,   -9,    0, -188,  135,    0,
   54,    0,  128,    0,    0,    0,    0,    0,    0,   37,
   84,   55,   95,  -39,  159,  100,  -55,   27,  -55, -124,
  285,  333,  162,  -53,    0,  119,  127,  124,    0,    0,
    0,  140,  428, -214,  -81,  164,  333,    0,    0,  171,
 -238,    0,   55,   55,    0,   55,   55,  407,  -33,   55,
  407,   13,  203,   75,  226,  -32,  407,    0,    0,  387,
    0,  -21,   53,  -28,  115,  185,  138,  182,  234,  318,
    0,    0,    0,    0,  228,  407,    0,  333,  238,  261,
    0,    0,  407,  156,    0,    0,    0,    0,  -30,  205,
    0,    0, -220,  407,  358,   13, -170,    0,    0,  231,
    0,  407,  415,    0,    0,    0,  192,    0,  239,    0,
  192,    0,    0,  280,    0,    0,    0,    0,  134,  -13,
  149,  214,  197,  217,  424,    0,  -68,    0,    0,  257,
  257,  218,    0,  407,  -63,    0,  407,  371,    0,  407,
    0,    0,   33,    0,    0, -239,    0,    0,  -16,   68,
   14,    0, -118,    0,    0,  302,  407,  301,  301,    0,
   98,    0,  407,  103,   48,  319,  389,  129,  141,    0,
  330,    0,  355,  110,  366,  368,  372,    0,    0,  160,
  393,  174,  416,    0,    0,  425, -129,  430,  435,    0,
    0,    0,  436,    0,    0,    0,  441,    0,  442,    0,
    0,  444,  445,    0,    0,    0,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,  449,    0,    0,
    0,  345,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,
   24,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  449,    0,    0,    0,    0,    0,    0,
    0,    0,  449,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   10,
    0,   11,    0,    0,    0,    0,    0,    0,    0,   47,
    0,    0,    0,    0,    0,  449,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   12,    0,    0,    0,   70,
    0,    0,    0,    0,    0,    0,    0,    0,  117,    0,
  121,    0,    0,  123,    0,    0,    0,    0,   60,    0,
    0,    0,    0,    0,  449,    0,    0,    0,    0,  326,
  374,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  375,  376,    0,
    0,    0,    0,    0,    0,   91,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -113,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  108,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   16,   29,  554,  642,    0,    0,  708,   86,  488,   26,
  -73,  705,    0,    0,    0,  719,  112,  650,  -29,    0,
    0,    5,    0,  125,
};
final static int YYTABLESIZE=898;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         33,
  107,   85,   44,    9,   28,   49,  208,  125,  133,   71,
   80,   38,  145,   45,   28,   12,  201,   92,   28,   35,
   34,   36,  136,   96,  203,  154,  179,  202,   28,  114,
   71,   28,   25,   35,   34,   36,   24,  137,  163,  164,
   59,  107,  107,  107,  107,  107,   93,  107,    6,    7,
   74,   73,   72,  109,  206,   73,   73,   74,   74,  107,
  107,  107,  107,   96,   96,   68,   96,   96,   96,   71,
   89,   28,   25,   35,   34,   36,  200,  103,  183,   73,
   72,   74,   96,   96,   96,   96,   93,   25,  169,  170,
   55,  214,  112,  142,   55,   76,   35,   34,   36,   28,
   77,  107,  107,   66,  107,   93,  107,   54,  205,   71,
   73,  141,   74,  152,   41,  130,   46,   69,  129,  107,
  107,  107,   27,  107,   81,  107,  232,   59,   71,  233,
   55,   92,   88,  158,   91,   83,   70,  207,    6,    7,
   87,  175,   38,   93,   79,  177,   96,   54,   96,   55,
  223,   80,   73,  222,   74,  147,   86,  101,  146,  105,
  101,   88,   27,   25,   88,   50,   54,  106,   78,   93,
   28,   93,   28,   71,  142,  188,  189,   73,   75,   74,
   59,   27,   28,  107,   49,   66,  110,   35,   34,   36,
  186,  187,   71,   28,   71,  192,  193,   35,   34,   36,
  117,  118,   47,   28,   73,  104,   74,  111,   35,   34,
   36,  113,   54,   55,  141,   55,   59,   59,   35,   34,
   36,  151,  124,  132,   50,  148,   48,   26,   27,   28,
   54,   84,   54,   43,   37,   38,   39,   26,   27,  144,
    9,   26,   27,   49,   10,   27,  128,   27,   37,   38,
   39,   26,   27,  151,   26,   27,  107,  107,  107,  107,
  107,  107,  107,  107,  107,   74,   73,   72,  107,  131,
  107,  156,    9,  151,  171,  107,  107,  107,  142,   96,
   96,   96,   96,   96,   96,   96,   96,   96,   37,   38,
   39,   96,    9,   96,   26,   27,   10,  176,   96,   96,
   96,  148,   93,   93,   93,   93,   93,   93,   93,   93,
   93,   37,   38,   39,   93,    9,   93,  138,  139,  140,
  178,  184,   26,   27,   97,   71,   71,   71,   71,   71,
   71,   71,   71,   71,  107,  107,  107,   71,  182,   71,
   10,  185,  190,    9,    1,  209,   55,   55,   55,   55,
   55,   55,   55,   55,   55,   64,  211,   64,   55,    9,
   55,  213,  215,   54,   54,   54,   54,   54,   54,   54,
   54,   54,   10,  220,    9,   54,    9,   54,   27,   27,
   27,   47,   48,   27,   27,   27,   27,  218,    6,    7,
   27,    9,   27,   26,   27,   26,   27,   10,  221,  219,
    6,    7,   37,   38,   39,  149,   27,   98,   56,  224,
   10,  225,   37,   38,   39,  226,   26,   27,  227,   56,
  138,  139,  140,   37,   38,   39,   26,   27,   10,   73,
  135,   74,  229,   37,   38,   39,  228,    1,    2,    3,
   47,   48,    4,    5,    6,    7,   10,    6,    7,    8,
   80,  150,   26,   27,   10,   98,  138,  139,  140,  230,
   58,    2,    3,   64,  148,    4,    5,   64,  231,   58,
    2,    3,  119,  234,    4,    5,    6,    7,  235,  236,
  120,    8,   49,  150,  237,  238,   49,  239,  240,   58,
    2,    3,   17,  120,    4,    5,    6,    7,   81,   78,
   79,    8,    0,  150,    0,   67,    0,    0,    0,    0,
    0,  120,    1,    2,    3,    0,    0,    4,    5,    6,
    7,    0,    0,    0,    8,    0,    0,    0,    0,  120,
    0,    0,    0,    0,    0,    0,    0,  120,  101,    0,
    1,    2,    3,    0,    0,    4,    5,    6,    7,    0,
  108,    0,   96,   14,    0,    0,   58,    2,    3,    0,
    0,    4,    5,    6,    7,   60,    0,    0,    8,    0,
    0,   62,    0,   62,    0,    0,    0,    0,    6,    7,
    6,    7,    0,   63,    0,  155,    0,   67,    1,    2,
    3,    0,    0,    4,    5,    6,    7,    0,    0,    0,
    8,    0,    0,    0,   14,   14,    0,    0,    0,    0,
    0,    0,    0,   58,    2,    3,  166,  167,    4,    5,
   14,    0,    0,    0,    0,  119,  195,    2,    3,  196,
  197,    4,    5,    0,    0,    0,    0,    0,  119,    0,
    0,   15,  108,    0,   58,    2,    3,  216,    0,    4,
    5,   14,   60,   61,    0,    0,  119,    0,    0,    0,
    0,   14,   58,    2,    3,    0,    0,    4,    5,    0,
  173,    2,    3,  160,  119,    4,    5,    0,    0,   47,
    0,    0,  119,   47,    0,    0,    6,    7,    0,    0,
    6,    7,   15,   15,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   60,   29,    0,   15,    0,
    0,    0,   53,   14,   14,   51,    0,    0,    0,    0,
   30,   30,    0,   30,    0,   65,   30,    0,   30,    0,
  127,    0,    0,    0,    0,    0,  134,    0,    0,   15,
   61,   60,   60,   30,    0,    0,   82,    0,    0,   15,
    0,   30,   90,    0,    0,  157,    0,   94,  100,    0,
   30,  161,  159,   30,    0,    0,   30,    0,    0,    0,
   65,   94,    0,  165,  168,    0,    0,  115,  116,    0,
    0,  172,  174,    0,  126,    0,    0,    0,    0,    0,
    0,   30,   30,   61,   30,   30,    0,    0,   30,    0,
   53,   15,   15,   51,   94,    0,    0,   65,    0,    0,
    0,    0,    0,  191,   30,   30,  194,  198,    0,  199,
    0,    0,    0,   53,    0,    0,    0,    0,    0,   61,
   61,    0,    0,    0,    0,    0,  210,   30,    0,    0,
    0,    0,  212,    0,    0,    0,  217,    0,    0,    0,
    0,    0,    0,    0,  180,  181,    0,    0,    0,    0,
    0,    0,   65,    0,    0,    0,    0,    0,   30,   30,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  204,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   30,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   41,   40,   59,   45,   59,  125,   41,   41,   40,
   40,  125,   41,   40,   45,    0,  256,  256,   45,   60,
   61,   62,   44,    0,   41,   99,   40,  267,   45,  268,
   40,   45,   40,   60,   61,   62,   44,   59,  259,  260,
   12,   41,   42,   43,   44,   45,    0,   47,  263,  264,
   41,   41,   41,  268,   41,   43,   43,   45,   45,   59,
   60,   61,   62,   40,   41,   44,   43,   44,   45,    0,
   44,   45,   40,   60,   61,   62,   44,   52,  152,   43,
  269,   45,   59,   60,   61,   62,   40,   40,  259,  260,
    0,   44,   67,   41,    9,   42,   60,   61,   62,   45,
   47,   42,   43,   18,   45,   59,   47,    0,   41,   40,
   43,   59,   45,   98,    3,   41,    5,   44,   44,   60,
   61,   62,    0,  123,   41,  125,  256,   99,   59,  259,
   40,  256,   47,  108,   49,   41,   25,  256,  263,  264,
   41,  137,  256,  268,   33,  141,  123,   40,  125,   59,
   41,  181,   43,   44,   45,   41,   45,   41,   44,   41,
   44,   41,   40,   40,   44,   40,   59,   41,   41,  123,
   45,  125,   45,   40,   41,  160,  161,   43,   44,   45,
  152,   59,   45,   44,   59,  100,  268,   60,   61,   62,
  259,  260,  123,   45,  125,  259,  260,   60,   61,   62,
   76,   77,  256,   45,   43,   44,   45,   44,   60,   61,
   62,   41,  268,  123,   59,  125,  188,  189,   60,   61,
   62,   40,  256,  256,   40,   41,  257,  268,  269,   45,
  123,  271,  125,  271,  275,  276,  277,  268,  269,  268,
   59,  268,  269,   59,   40,  123,   44,  125,  275,  276,
  277,  268,  269,   40,  268,  269,  256,  257,  258,  259,
  260,  261,  262,  263,  264,  256,  256,  256,  268,   44,
  270,   44,   59,   40,   44,  275,  276,  277,   41,  256,
  257,  258,  259,  260,  261,  262,  263,  264,  275,  276,
  277,  268,   59,  270,  268,  269,   40,   59,  275,  276,
  277,   41,  256,  257,  258,  259,  260,  261,  262,  263,
  264,  275,  276,  277,  268,   59,  270,  265,  266,  267,
   41,  125,  268,  269,   40,  256,  257,  258,  259,  260,
  261,  262,  263,  264,  275,  276,  277,  268,  125,  270,
   40,  125,  125,   59,    0,   44,  256,  257,  258,  259,
  260,  261,  262,  263,  264,   40,  259,   40,  268,   59,
  270,  259,   44,  256,  257,  258,  259,  260,  261,  262,
  263,  264,   40,   44,   59,  268,   59,  270,  256,  257,
  258,  256,  257,  261,  262,  263,  264,  259,  263,  264,
  268,   59,  270,  268,  269,  268,  269,   40,   44,  259,
  263,  264,  275,  276,  277,  268,  269,  123,  271,   44,
   40,   44,  275,  276,  277,   44,  268,  269,  259,  271,
  265,  266,  267,  275,  276,  277,  268,  269,   40,   43,
   44,   45,  259,  275,  276,  277,   44,  256,  257,  258,
  256,  257,  261,  262,  263,  264,   40,  263,  264,  268,
  125,  270,  268,  269,   40,  123,  265,  266,  267,   44,
  256,  257,  258,   40,   41,  261,  262,   40,   44,  256,
  257,  258,  268,   44,  261,  262,  263,  264,   44,   44,
  123,  268,   59,  270,   44,   44,   59,   44,   44,  256,
  257,  258,   44,  123,  261,  262,  263,  264,  125,  125,
  125,  268,   -1,  270,   -1,   18,   -1,   -1,   -1,   -1,
   -1,  123,  256,  257,  258,   -1,   -1,  261,  262,  263,
  264,   -1,   -1,   -1,  268,   -1,   -1,   -1,   -1,  123,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  123,   51,   -1,
  256,  257,  258,   -1,   -1,  261,  262,  263,  264,   -1,
   63,   -1,  268,    0,   -1,   -1,  256,  257,  258,   -1,
   -1,  261,  262,  263,  264,   12,   -1,   -1,  268,   -1,
   -1,  256,   -1,  256,   -1,   -1,   -1,   -1,  263,  264,
  263,  264,   -1,  268,   -1,  268,   -1,  100,  256,  257,
  258,   -1,   -1,  261,  262,  263,  264,   -1,   -1,   -1,
  268,   -1,   -1,   -1,   51,   52,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  256,  257,  258,  259,  260,  261,  262,
   67,   -1,   -1,   -1,   -1,  268,  256,  257,  258,  259,
  260,  261,  262,   -1,   -1,   -1,   -1,   -1,  268,   -1,
   -1,    0,  155,   -1,  256,  257,  258,  259,   -1,  261,
  262,   98,   99,   12,   -1,   -1,  268,   -1,   -1,   -1,
   -1,  108,  256,  257,  258,   -1,   -1,  261,  262,   -1,
  256,  257,  258,  120,  268,  261,  262,   -1,   -1,  256,
   -1,   -1,  268,  256,   -1,   -1,  263,  264,   -1,   -1,
  263,  264,   51,   52,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  152,    2,   -1,   67,   -1,
   -1,   -1,    8,  160,  161,    8,   -1,   -1,   -1,   -1,
    2,    3,   -1,    5,   -1,   18,    8,   -1,   10,   -1,
   81,   -1,   -1,   -1,   -1,   -1,   87,   -1,   -1,   98,
   99,  188,  189,   25,   -1,   -1,   42,   -1,   -1,  108,
   -1,   33,   48,   -1,   -1,  106,   -1,   50,   51,   -1,
   42,  120,  113,   45,   -1,   -1,   48,   -1,   -1,   -1,
   63,   64,   -1,  124,  125,   -1,   -1,   73,   74,   -1,
   -1,  132,  133,   -1,   80,   -1,   -1,   -1,   -1,   -1,
   -1,   73,   74,  152,   76,   77,   -1,   -1,   80,   -1,
   96,  160,  161,   96,   97,   -1,   -1,  100,   -1,   -1,
   -1,   -1,   -1,  164,   96,   97,  167,  168,   -1,  170,
   -1,   -1,   -1,  119,   -1,   -1,   -1,   -1,   -1,  188,
  189,   -1,   -1,   -1,   -1,   -1,  187,  119,   -1,   -1,
   -1,   -1,  193,   -1,   -1,   -1,  197,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  150,  151,   -1,   -1,   -1,   -1,
   -1,   -1,  155,   -1,   -1,   -1,   -1,   -1,  150,  151,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  179,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  179,
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
"ctrl : WHILE '(' cond ')' ctrl_blck",
"ctrl : error '(' cond ')' ctrl_blck",
"ctrl : WHILE cond ')' ctrl_blck",
"ctrl : WHILE '(' cond error ctrl_blck",
"ctrl : WHILE '(' cond ')' error ','",
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

//#line 198 "gramatica"

LexicalAnalizer la;

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







public void gt() {
		String path = "/home/alan/Documents";
		LexicalAnalizer.symbolTable.genOutput(path);
	}

//#line 640 "Parser.java"
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
{print("Sintaxis: Declaracion de variable");}
break;
case 12:
//#line 31 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta especificar el tipo de la variable");}
break;
case 13:
//#line 32 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Error en la declaracion de variable/s");}
break;
case 15:
//#line 35 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta identificador antes del ';'");}
break;
case 16:
//#line 36 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta ';'");}
break;
case 18:
//#line 40 "gramatica"
{print("Sintaxis: Declaracion de funcion");}
break;
case 19:
//#line 41 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta el tipo de retorno de la funcion");}
break;
case 20:
//#line 42 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "No se permiten funciones anonimas");}
break;
case 21:
//#line 43 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta el argumento de la funcion");}
break;
case 22:
//#line 44 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta cuerpo de la funcion");}
break;
case 24:
//#line 48 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta '('");}
break;
case 25:
//#line 49 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta especificar el tipo del parametro de la funcion");}
break;
case 26:
//#line 50 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta ID como parametro de la funcion");}
break;
case 27:
//#line 51 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta ')'");}
break;
case 30:
//#line 56 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta '{'");}
break;
case 31:
//#line 57 "gramatica"
{yyerror(((Token) val_peek(4).obj).getLine(), "Falta '}'");}
break;
case 32:
//#line 58 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta sentencia de retorno");}
break;
case 34:
//#line 62 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Retorno de funcion sin 'RETURN'");}
break;
case 35:
//#line 63 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Falta '('");}
break;
case 36:
//#line 64 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta valor de retorno de la funcion");}
break;
case 37:
//#line 65 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta ')'");}
break;
case 38:
//#line 66 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta ','");}
break;
case 43:
//#line 75 "gramatica"
{print("Sintaxis: Sentencia IF-ELSE");}
break;
case 44:
//#line 76 "gramatica"
{print("Sintaxis: Sentencia IF");}
break;
case 45:
//#line 77 "gramatica"
{yyerror(((Token) val_peek(7).obj).getLine(), "'ELSE' sin 'IF'");}
break;
case 46:
//#line 78 "gramatica"
{yyerror(((Token) val_peek(5).obj).getLine(), "'ENDIF' sin 'IF'");}
break;
case 47:
//#line 79 "gramatica"
{yyerror(((Token) val_peek(7).obj).getLine(), "Falta '(' en condicion de sentencia condicional");}
break;
case 48:
//#line 80 "gramatica"
{yyerror(((Token) val_peek(6).obj).getLine(), "condicion faltante en sentencia condicional");}
break;
case 49:
//#line 81 "gramatica"
{yyerror(((Token) val_peek(7).obj).getLine(), "Falta ')' en la sentencia condicional");}
break;
case 50:
//#line 82 "gramatica"
{yyerror(((Token) val_peek(4).obj).getLine(), "Sentencia/s faltante/s luego de 'IF'");}
break;
case 51:
//#line 83 "gramatica"
{yyerror(((Token) val_peek(4).obj).getLine(), "Falta 'ELSE' o sobran llaves en la declaracion de bloque");}
break;
case 52:
//#line 84 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Bloque faltante luego de 'ELSE'");}
break;
case 53:
//#line 85 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Falta 'ENDIF'");}
break;
case 54:
//#line 86 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta ','");}
break;
case 55:
//#line 87 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta ','");}
break;
case 56:
//#line 88 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Falta '('");}
break;
case 57:
//#line 89 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Falta condicion en la sentencia condicional");}
break;
case 58:
//#line 90 "gramatica"
{yyerror(((Token) val_peek(4).obj).getLine(), "Falta ')'");}
break;
case 59:
//#line 91 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta 'ENDIF' en sentencia condicional");}
break;
case 60:
//#line 92 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta bloque de sentencias en sentencia condicional");}
break;
case 61:
//#line 95 "gramatica"
{print("Sintaxis: Sentencia While");}
break;
case 62:
//#line 96 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "'SENTENCIA DE CONTROL' desconocida");}
break;
case 63:
//#line 97 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Falta'('");}
break;
case 64:
//#line 98 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Falta ')'");}
break;
case 65:
//#line 99 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Sentencia de control sin bloque de sentencias");}
break;
case 66:
//#line 102 "gramatica"
{print("Sintaxis: Sentencia print");}
break;
case 67:
//#line 103 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "'PRINT' faltante en la sentencia de impresion");}
break;
case 68:
//#line 104 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta '('");}
break;
case 69:
//#line 105 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta ')'");}
break;
case 70:
//#line 106 "gramatica"
{print("Sintaxis: Sentencia print");}
break;
case 71:
//#line 107 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta ','");}
break;
case 73:
//#line 111 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta expresion del lado izquierdo del comparador");}
break;
case 74:
//#line 112 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta expresion del lado derecho del comparador");}
break;
case 82:
//#line 126 "gramatica"
{print("Sintaxis: Invocacion a funcion");}
break;
case 83:
//#line 127 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta ')'");}
break;
case 85:
//#line 132 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Argumentos mal definidos");}
break;
case 86:
//#line 133 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Llamado a funcion sin argumento");}
break;
case 87:
//#line 134 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta ';'");}
break;
case 88:
//#line 135 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Especificacion de permisos faltante");}
break;
case 89:
//#line 138 "gramatica"
{print("Sintaxis: Asignacion");}
break;
case 90:
//#line 139 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Asignacion sin id del lado izq");}
break;
case 91:
//#line 140 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Asignacion sin :=");}
break;
case 92:
//#line 141 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta expresion del lado derecho del :=");}
break;
case 93:
//#line 142 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta ','");}
break;
case 108:
//#line 168 "gramatica"
{/*numero es positivo*/
				Object obj = val_peek(0).obj;
				if (!ifUS(((Token) obj)))
					testPos((Token) obj);}
break;
case 109:
//#line 172 "gramatica"
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
//#line 1075 "Parser.java"
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
