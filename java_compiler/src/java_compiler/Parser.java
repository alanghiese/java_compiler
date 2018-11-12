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

import utilities.*;

import java.util.LinkedList;
import java.util.List;

import codigo_intermedio.*;
import java.util.concurrent.atomic.AtomicReference;
//#line 27 "Parser.java"




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
public final static short MEN_IG=275;
public final static short MAY_IG=276;
public final static short DIST=277;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    1,    1,    1,    1,    2,    2,
    5,    5,    5,    8,    8,    8,    8,    6,    9,    9,
    9,   11,   11,   11,   11,   11,   11,   10,   10,   10,
   10,   10,   10,   12,   12,   12,   12,   12,   12,    3,
    3,    3,    3,   14,   14,   14,   14,   14,   14,   14,
   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,
   14,   15,   15,   15,   15,   20,   16,   16,   16,   16,
   16,   16,   18,   18,   18,   19,   19,   19,   22,   22,
   22,   22,   17,   17,   23,   23,   23,   23,   23,    4,
    4,    4,    4,    4,   13,   13,   13,   25,   25,   25,
   24,   24,   24,   24,   24,    7,    7,   26,   26,   26,
   26,   21,   21,   21,   21,   21,   21,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    1,    1,    1,    2,    1,    1,
    3,    2,    3,    3,    2,    3,    1,    2,    3,    2,
    2,    4,    3,    3,    3,    3,    2,    4,    3,    3,
    5,    3,    2,    5,    4,    4,    4,    4,    4,    1,
    1,    1,    2,    9,    7,    8,    6,    8,    8,    9,
    8,    8,    8,    9,    8,    6,    5,    5,    6,    7,
    5,    5,    4,    5,    6,    1,    5,    4,    4,    4,
    4,    4,    3,    2,    2,    1,    1,    3,    2,    2,
    1,    1,    4,    4,    3,    2,    3,    2,    2,    4,
    3,    3,    3,    3,    3,    3,    1,    3,    3,    1,
    1,    1,    1,    3,    4,    1,    1,    1,    1,    2,
    1,    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,   66,  106,  107,    0,    0,    0,
    0,    0,    5,    6,    7,    9,   10,    0,    0,    0,
   40,   41,   42,    0,    0,    8,    0,  109,    0,    0,
  111,    0,  100,    0,  112,  113,  114,  115,  116,  117,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   20,    0,    0,   15,    0,    0,    2,    3,    4,    0,
    0,    0,    0,   21,   12,    0,    0,   18,   43,    0,
    0,    0,  110,    0,    0,   91,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   16,   93,    0,   14,
    0,    0,   27,    0,    0,    0,   92,    0,    0,   13,
   19,    0,   11,    0,    0,   33,    0,    0,    0,    0,
    0,    0,   95,   96,   98,   99,    0,    0,   76,   77,
    0,    0,    0,    0,    0,   69,   70,    0,   71,   90,
   86,    0,  101,    0,  103,    0,   24,   88,    0,   25,
   84,   83,   23,   68,    0,    0,    0,    0,   32,    0,
   29,   30,    0,    0,   63,    0,    0,    0,   58,    0,
    0,   61,    0,    0,   57,    0,   67,   87,    0,   85,
   22,    0,    0,    0,    0,    0,    0,    0,   28,   64,
    0,   62,    0,    0,   78,    0,   59,    0,    0,    0,
    0,    0,    0,    0,    0,  104,   47,    0,   37,   38,
    0,   36,   35,   31,   65,    0,    0,    0,   60,   45,
    0,    0,    0,    0,  105,    0,   34,   49,    0,   51,
   53,    0,    0,   52,   48,   46,   50,   54,   44,
};
final static short yydgoto[] = {                         11,
   12,   13,  119,  120,   16,   17,   18,   19,   20,   68,
   51,  108,   41,   21,   22,   23,   24,   56,  121,   25,
   43,  158,   95,  138,   32,   33,
};
final static short yysindex[] = {                       159,
  -25,   89,  -40,  -37,    0,    0,    0,  144,  -55,  113,
    0,  276,    0,    0,    0,    0,    0,  216,  -19,  213,
    0,    0,    0,   -6,  -30,    0,   13,    0, -180,  111,
    0,    9,    0,  -27,    0,    0,    0,    0,    0,    0,
   37,   52,   89,   71,  -39,  -55,    5,  -55,  226, -147,
    0,  116,  -50,    0,   87,   96,    0,    0,    0,   81,
  347,  279,   88,    0,    0,  141,  184,    0,    0,  117,
  103, -227,    0,   89,   89,    0,   89,   89,  355,  -35,
   89,  355,  123,  121,   60,  125,    0,    0,  147,    0,
   -7,   77,    0,  -24,   78,  130,    0,  132,  355,    0,
    0,  139,    0,   50,  113,    0,  168,   27,   32,  -34,
  355,  243,    0,    0,    0,    0,  -17,  230,    0,    0,
 -119,  355,  301,  123,  -77,    0,    0,  150,    0,    0,
    0,  -69,    0,  148,    0,  -69,    0,    0,  169,    0,
    0,    0,    0,    0,  -74,   55,   64,   14,    0, -114,
    0,    0,  355,  316,    0,  159,  159,   90,    0,  355,
  -58,    0,  355,  256,    0,  355,    0,    0, -179,    0,
    0,  165,  355,  167,   72,  173,  175,  176,    0,    0,
  181,    0,  276,  276,    0,  -47,    0,  355,  -29,  182,
  224,  333,   36,   38,  277,    0,    0,   63,    0,    0,
  281,    0,    0,    0,    0,  293,   80,  302,    0,    0,
  319, -178,  330,  331,    0,  336,    0,    0,  339,    0,
    0,  352,  366,    0,    0,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,  370,    0,    0,
    0,  345,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,
    0,   24,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  370,    0,    0,    0,    0,    0,    0,    0,
  370,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -33,    0,   -2,    0,    0,    0,    0,    0,   47,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   -1,    0,    0,    0,   70,    0,    0,
    0,    0,    0,   86,    0,   94,    0,    0,  239,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  235,  252,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  266,  294,    0,    0,    0,    0,    0,    0,
   91,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -113,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  108,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    7,    4,  555,  568,    0,    0,   84,  105,    0,    0,
   18,  -41,  562,    0,    0,    0,  617,   69,  577,    0,
  -28,    0,    0,  -78,    0,  127,
};
final static int YYTABLESIZE=769;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         34,
  108,   86,   45,    9,   29,  123,  154,   75,   48,   70,
  179,   39,   81,   79,   29,   57,  140,   29,   26,   36,
   35,   37,   72,   97,   65,  109,   67,   29,   91,   36,
   35,   37,   36,   35,   37,   64,  131,   69,   74,   73,
  112,  108,  108,  108,  108,  108,   94,  108,   88,   29,
   77,  132,   72,  168,  177,   78,   74,  170,   75,  108,
  108,  108,  108,   97,   97,  150,   97,   97,   97,   72,
   57,   42,  107,   36,   35,   37,  195,  222,  101,   74,
  223,   75,   97,   97,   97,   97,   94,  196,   73,  146,
   56,   50,   82,   71,   29,  174,   36,   35,   37,   29,
  128,   50,   80,  127,  176,   94,   74,   55,   75,   72,
   57,   84,  201,   54,   74,  200,   75,  137,  142,   81,
   96,  141,   63,  108,  100,  108,  102,   98,   72,  102,
   56,  103,   94,   29,   89,  136,   99,   89,  110,  159,
  160,  178,   39,  111,   50,   94,   97,   55,   97,   56,
   87,  151,   90,   74,   76,   75,  152,   29,   74,   97,
   75,   29,  183,  184,  126,   74,   55,   75,  129,   94,
  143,   94,   36,   35,   37,  144,   36,   35,   37,  137,
  105,  165,  166,   49,  172,  173,   57,   57,   29,   74,
  130,   75,   72,  167,   72,  133,  134,  135,   10,    9,
  187,  188,   48,  115,  116,   46,  169,  105,  197,  171,
  199,  206,   53,   56,  185,   56,  202,    9,  203,  204,
  122,  153,   75,  105,  205,  209,    9,   27,   28,  208,
   55,   85,   55,   44,   38,   39,   40,   27,   28,   47,
   27,   28,    9,  139,   38,   39,   40,   38,   39,   40,
   27,   28,   10,   74,   73,   62,  108,  108,  108,  108,
  108,  108,  108,  108,  108,  106,   93,  210,  108,   10,
  108,    9,   27,   28,    9,  108,  108,  108,   26,   97,
   97,   97,   97,   97,   97,   97,   97,   97,   38,   39,
   40,   97,  149,   97,  213,   10,  214,   26,   97,   97,
   97,  136,   94,   94,   94,   94,   94,   94,   94,   94,
   94,   38,   39,   40,   94,   10,   94,   27,   28,   93,
  215,  216,   27,   28,  217,   72,   72,   72,   72,   72,
   72,   72,   72,   72,    9,   66,  218,   72,  219,   72,
   10,  133,  134,  135,    1,  220,   56,   56,   56,   56,
   56,   56,   56,   56,   56,   10,   27,   28,   56,   81,
   56,   26,  221,   55,   55,   55,   55,   55,   55,   55,
   55,   55,   10,  224,  225,   55,   82,   55,  118,  226,
   27,   28,  227,   55,   27,   28,   62,   38,   39,   40,
   79,   38,   39,   40,   10,  228,    1,    2,    3,   46,
   47,    4,    5,    6,    7,   48,    6,    7,    8,  229,
  104,   27,   28,   17,    1,    2,    3,    0,   80,    4,
    5,    6,    7,  118,    2,    3,    8,    0,    4,    5,
    6,    7,    0,    0,    0,    8,    0,  104,  118,    0,
    2,    3,    0,    0,    4,    5,    6,    7,    0,    0,
    0,    8,    0,  104,    0,  118,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    1,    2,
    3,   60,    0,    4,    5,    6,    7,  118,    6,    7,
    8,   91,    0,   61,    0,    0,    2,    3,    6,    7,
    4,    5,    0,   92,   26,   26,   26,  117,    0,   26,
   26,   26,   26,    0,    0,    0,   26,  133,  134,  135,
    0,  190,    2,    3,  191,  192,    4,    5,    0,    0,
    0,    0,    0,  117,    0,    0,    0,    0,    0,    0,
    0,    0,    2,    3,    0,    0,    4,    5,    6,    7,
    0,    6,    7,    8,    0,    0,  102,    0,    0,    0,
    0,    0,    0,    0,   14,    0,    0,    2,    3,  162,
  163,    4,    5,   30,    0,    0,   58,   15,  117,   52,
    0,  181,    2,    3,   14,    0,    4,    5,    0,   59,
    0,    0,    0,  117,    0,    0,    0,   15,    0,    2,
    3,  211,    0,    4,    5,    0,    0,    0,    0,    0,
  117,    0,   46,    0,   83,    0,    0,    0,   89,    6,
    7,    2,    3,    0,    0,    4,    5,    0,   31,   31,
   14,   58,  117,    0,   31,    0,   31,    0,    0,    0,
    0,    0,    0,   15,   59,  113,  114,    0,    0,    0,
    0,   31,  124,    0,    0,    0,    0,    0,    0,    0,
   31,    0,    0,    0,    0,    0,    0,    0,  125,   31,
    0,   58,    0,   31,    0,  147,  148,    0,    0,    0,
    0,    0,  156,    0,   59,  145,    0,    0,   52,    0,
    0,    0,    0,    0,    0,  157,   31,  155,    0,    0,
   31,   31,    0,   31,   31,    0,    0,   31,  161,  164,
    0,    0,    0,    0,    0,    0,    0,  175,    0,    0,
   14,   14,    0,    0,    0,    0,    0,    0,    0,    0,
   31,   31,    0,   15,   15,    0,    0,    0,    0,  180,
  182,    0,    0,   31,    0,    0,  186,   58,   58,  189,
  193,    0,  194,    0,    0,    0,    0,    0,    0,  198,
   59,   59,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   31,    0,  207,    0,    0,    0,  212,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   41,   40,   59,   45,   41,   41,   41,   59,   40,
  125,  125,   41,   41,   45,   12,   41,   45,   44,   60,
   61,   62,   40,    0,   44,   67,   20,   45,  256,   60,
   61,   62,   60,   61,   62,   18,   44,   44,   41,   41,
  268,   41,   42,   43,   44,   45,    0,   47,   44,   45,
   42,   59,   40,  132,   41,   47,   43,  136,   45,   59,
   60,   61,   62,   40,   41,  107,   43,   44,   45,    0,
   67,    3,   66,   60,   61,   62,  256,  256,   61,   43,
  259,   45,   59,   60,   61,   62,   40,  267,  269,   40,
    0,    8,   41,   25,   45,   41,   60,   61,   62,   45,
   41,   18,   34,   44,   41,   59,   43,    0,   45,   40,
  107,   41,   41,    9,   43,   44,   45,   41,   41,  148,
  268,   44,   18,  123,   44,  125,   41,   41,   59,   44,
   40,   44,   49,   45,   41,   59,   41,   44,   70,  259,
  260,  256,  256,   41,   61,   62,  123,   40,  125,   59,
   46,  125,   48,   43,   44,   45,  125,   45,   43,   44,
   45,   45,  156,  157,   44,   43,   59,   45,   44,  123,
   41,  125,   60,   61,   62,   44,   60,   61,   62,   41,
   40,  259,  260,   40,  259,  260,  183,  184,   45,   43,
   44,   45,  123,   44,  125,  265,  266,  267,   40,   59,
  259,  260,   59,   77,   78,  256,   59,   40,   44,   41,
   44,  259,  268,  123,  125,  125,   44,   59,   44,   44,
  256,  256,  256,   40,   44,   44,   59,  268,  269,  259,
  123,  271,  125,  271,  275,  276,  277,  268,  269,  257,
  268,  269,   59,  268,  275,  276,  277,  275,  276,  277,
  268,  269,   40,  256,  256,   40,  256,  257,  258,  259,
  260,  261,  262,  263,  264,  125,   41,   44,  268,   40,
  270,   59,  268,  269,   59,  275,  276,  277,   40,  256,
  257,  258,  259,  260,  261,  262,  263,  264,  275,  276,
  277,  268,  125,  270,  259,   40,  259,   59,  275,  276,
  277,   59,  256,  257,  258,  259,  260,  261,  262,  263,
  264,  275,  276,  277,  268,   40,  270,  268,  269,   41,
   44,  259,  268,  269,   44,  256,  257,  258,  259,  260,
  261,  262,  263,  264,   59,  123,   44,  268,  259,  270,
   40,  265,  266,  267,    0,   44,  256,  257,  258,  259,
  260,  261,  262,  263,  264,   40,  268,  269,  268,  125,
  270,  123,   44,  256,  257,  258,  259,  260,  261,  262,
  263,  264,   40,   44,   44,  268,  125,  270,  123,   44,
  268,  269,   44,  271,  268,  269,   40,  275,  276,  277,
  125,  275,  276,  277,   40,   44,  256,  257,  258,  256,
  257,  261,  262,  263,  264,   59,  263,  264,  268,   44,
  270,  268,  269,   44,  256,  257,  258,   -1,  125,  261,
  262,  263,  264,  123,  257,  258,  268,   -1,  261,  262,
  263,  264,   -1,   -1,   -1,  268,   -1,  270,  123,   -1,
  257,  258,   -1,   -1,  261,  262,  263,  264,   -1,   -1,
   -1,  268,   -1,  270,   -1,  123,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  256,  257,
  258,  256,   -1,  261,  262,  263,  264,  123,  263,  264,
  268,  256,   -1,  268,   -1,   -1,  257,  258,  263,  264,
  261,  262,   -1,  268,  256,  257,  258,  268,   -1,  261,
  262,  263,  264,   -1,   -1,   -1,  268,  265,  266,  267,
   -1,  256,  257,  258,  259,  260,  261,  262,   -1,   -1,
   -1,   -1,   -1,  268,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  257,  258,   -1,   -1,  261,  262,  263,  264,
   -1,  263,  264,  268,   -1,   -1,  268,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,    0,   -1,   -1,  257,  258,  259,
  260,  261,  262,    2,   -1,   -1,   12,    0,  268,    8,
   -1,  256,  257,  258,   20,   -1,  261,  262,   -1,   12,
   -1,   -1,   -1,  268,   -1,   -1,   -1,   20,   -1,  257,
  258,  259,   -1,  261,  262,   -1,   -1,   -1,   -1,   -1,
  268,   -1,  256,   -1,   43,   -1,   -1,   -1,   47,  263,
  264,  257,  258,   -1,   -1,  261,  262,   -1,    2,    3,
   66,   67,  268,   -1,    8,   -1,   10,   -1,   -1,   -1,
   -1,   -1,   -1,   66,   67,   74,   75,   -1,   -1,   -1,
   -1,   25,   81,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   34,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   82,   43,
   -1,  107,   -1,   47,   -1,  104,  105,   -1,   -1,   -1,
   -1,   -1,  118,   -1,  107,   99,   -1,   -1,  117,   -1,
   -1,   -1,   -1,   -1,   -1,  118,   70,  111,   -1,   -1,
   74,   75,   -1,   77,   78,   -1,   -1,   81,  122,  123,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  146,   -1,   -1,
  156,  157,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  104,  105,   -1,  156,  157,   -1,   -1,   -1,   -1,  153,
  154,   -1,   -1,  117,   -1,   -1,  160,  183,  184,  163,
  164,   -1,  166,   -1,   -1,   -1,   -1,   -1,   -1,  173,
  183,  184,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  146,   -1,  188,   -1,   -1,   -1,  192,
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
"func : func_head func_body",
"func_head : type ID param",
"func_head : ID param",
"func_head : type param",
"param : '(' type ID ')'",
"param : type ID ')'",
"param : '(' ID ')'",
"param : '(' type ')'",
"param : '(' type ID",
"param : '(' ')'",
"func_body : '{' sent ret '}'",
"func_body : '{' ret '}'",
"func_body : sent ret '}'",
"func_body : '{' sent ret error ','",
"func_body : '{' sent '}'",
"func_body : '{' '}'",
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

//#line 414 "gramatica"

public final static String GLOBAL_SCOPE="Global";

LexicalAnalizer la;
LinkedList<Triples> triples= new LinkedList<>();
LinkedList<Triples> pendingTriples= new LinkedList<>();
LinkedList<String> varList = new LinkedList<>();
int functionAllows=-1;
LinkedList<String> paramList = new LinkedList<>();
LinkedList<Permissions> permsList = new LinkedList<>();
String typeRet = new String();
String currentScope=GLOBAL_SCOPE;
boolean macrigato=false;

public Parser(String path) {
		la = new LexicalAnalizer(path);
}

public void yyerror(int l, String s){
	macrigato=true;
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
    //System.out.println(s);
}

public boolean correctCall(Boolean converted, Operand param, String functionName, String paramName, int line){
    if(LexicalAnalizer.symbolTable.isFunction(functionName)){
    	if (LexicalAnalizer.symbolTable.paramAllow(functionName,functionAllows)){
    		if(!LexicalAnalizer.symbolTable.isVar(paramName,currentScope)){
    			yyerror(line, "Semantic Error: Parameto inexistente");
    			return false;
    		}
    		if(!LexicalAnalizer.symbolTable.getType(paramName).equals(LexicalAnalizer.symbolTable.getParamType(functionName)))
	    		if(!TypeConverter.isValid(LexicalAnalizer.symbolTable.getType(paramName),LexicalAnalizer.symbolTable.getParamType(functionName) )){
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
		String number = t.getLex();
		StringBuilder withoutSuffix = new StringBuilder();
		for (int i = 0; i < number.length() - 2; i++)
			withoutSuffix.append(number.charAt(i));
		Long l = Long.parseLong(withoutSuffix.toString())*-1;
		if (l.compareTo((long)Integer.MAX_VALUE)>0) {
			l = (long)Integer.MAX_VALUE;
			if (((CTNInformation)LexicalAnalizer.symbolTable.getLexeme(t.getLex())).getCounter()>1) {
				((CTNInformation)LexicalAnalizer.symbolTable.getLexeme(t.getLex())).decreaseCounter();
				CTNInformation ctni = new CTNInformation();
				ctni.setType(Constants.L_INT);
				LexicalAnalizer.symbolTable.addCTN(l.toString()+"_l", ctni);
			}
			else {
				LexicalAnalizer.symbolTable.removeCTN(t.getLex());
				CTNInformation ctni = new CTNInformation();
				ctni.setType(Constants.L_INT);
				LexicalAnalizer.symbolTable.addCTN(l.toString()+"_l", ctni);
			}
		}
			
	}
	
	public void testNeg(Token t) {
		String number = t.getLex();
		StringBuilder withoutSuffix = new StringBuilder();
		for (int i = 0; i < number.length() - 2; i++)
			withoutSuffix.append(number.charAt(i));
		Long l = Long.parseLong(withoutSuffix.toString())*-1;
		CTNInformation ctni = new CTNInformation();
		ctni.setType(Constants.L_INT);
		if (((CTNInformation)LexicalAnalizer.symbolTable.getLexeme(t.getLex())).getCounter()>1)
			((CTNInformation)LexicalAnalizer.symbolTable.getLexeme(t.getLex())).decreaseCounter();
		else
			LexicalAnalizer.symbolTable.removeCTN(t.getLex());
        t.setLex(l.toString()+"_l");
		LexicalAnalizer.symbolTable.addCTN(l.toString()+"_l", ctni);
		
	}

        public void blockFound(){
            Triples t = new TrBI();
            triples.add(t);
            Triples label= new Label();
            triples.add(label);
            pendingTriples.removeLast().setO1(label);
            pendingTriples.add(triples.get(triples.size()-2));
        }

        public void completeLast(){
            Triples t = pendingTriples.removeLast();
            t.setO1(pendingTriples.removeLast());
        }

        public void setTypes(String type, int lineNumber){
            for(String var: varList)
                if(!LexicalAnalizer.symbolTable.isUsed(var)){
                    LexicalAnalizer.symbolTable.setType(var, type);
                    LexicalAnalizer.symbolTable.setScope(var,currentScope);
                }else{
                    yyerror(lineNumber, "El id "+ var +" ya fue usado");
                }
            varList.clear();
        }

        public void getConversion(Operand in1, Operand in2, AtomicReference<Operand> out1, AtomicReference<Operand> out2) {

		if (!in1.getType().equals(in2.getType())) {
			Triples cnv;
			if ((in1.getType().equals("usinteger"))) {
				cnv = new TrCNV(in1);
				triples.add(cnv);
				out1.set(cnv);
				out2.set(in2);
			} else {
				cnv = new TrCNV(in2);
				triples.add(cnv);
				out1.set(in1);
				out2.set(cnv);
			}
		} else {
			out1.set(in1);
			out2.set(in2);
		}
	}

public void gt() {
		String path = "/home/alan/Documents";
		LexicalAnalizer.symbolTable.genOutput(path);
	}

public void getTable(String path) {
		LexicalAnalizer.symbolTable.genOutput(path);
	}
	
	
	public List<Triples> getTriples(){
	return this.triples;
}

//#line 715 "Parser.java"
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
        {yyval=new ParserVal();
        AtomicReference<ParserVal> ref = new AtomicReference<>();
        
        yychar = la.yylex(ref); 
        yylval = ref.get();  //get next token
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
//#line 19 "gramatica"
{print("Sintaxis: Codigo reconocido");}
break;
case 8:
//#line 28 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "");}
break;
case 11:
//#line 35 "gramatica"
{print("Sintaxis: Declaracion de variable");
                            setTypes(((Token)(val_peek(2).obj)).getLex(),((Token)val_peek(2).obj).getLine());}
break;
case 12:
//#line 37 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta especificar el tipo de la variable");}
break;
case 13:
//#line 38 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Error en la declaracion de variable/s");}
break;
case 14:
//#line 41 "gramatica"
{varList.add(((Token) (val_peek(2).obj)).getLex());}
break;
case 15:
//#line 42 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta identificador antes del ';'");}
break;
case 16:
//#line 43 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta ';'");}
break;
case 17:
//#line 44 "gramatica"
{varList.add(((Token) (val_peek(0).obj)).getLex());}
break;
case 18:
//#line 47 "gramatica"
{if (paramList.size()==1) {
										 print("Sintaxis: Declaracion de funcion");
	                                     if(TypeConverter.isValid(((Token)val_peek(1).obj).getLex(),typeRet)){
	                                     	if(!((Token)val_peek(1).obj).getLex().equals(typeRet)){
												Triples cnv = new TrCNV(((Triples)val_peek(0).obj).getO1());
												cnv.setId(((Triples)val_peek(0).obj).getId());
												triples.add(triples.size()-2,cnv);
												((Triples)val_peek(0).obj).setId(((Triples)val_peek(0).obj).getId()+1);
	                                            ((Triples)val_peek(0).obj).setO1(cnv);
	                                        }
	                                            
	                                     }else{
	                                     	yyerror(((Token)val_peek(1).obj).getLine(), "Semantic Error: Tipo de retorno incompatible"); 
	                                     }
	                                     /*remuevo el parametro para esta funcion en particular (considero funciones anidadas)*/
	                                     paramList.removeLast();
	                                     LexicalAnalizer.symbolTable.setPermissions(currentScope,permsList.removeLast());
	                                     currentScope=GLOBAL_SCOPE;
	                                  }else{
	                                  	yyerror(((Token) val_peek(1).obj).getLine(), "Función declarada dentro de otra función");
	                                  	paramList.removeLast();
	                                  	permsList.removeLast();
	                                  	}
	                                 }
break;
case 19:
//#line 73 "gramatica"
{
							if(!LexicalAnalizer.symbolTable.isUsed(((Token)val_peek(1).obj).getLex())){
								LexicalAnalizer.symbolTable.removeID(((Token)val_peek(1).obj).getLex());
								FNCInformation fnc = new FNCInformation();
				                fnc.setType(((Token)val_peek(2).obj).getLex());
                                fnc.setParamType(((Token)val_peek(0).obj).getLex());
                                fnc.setParamName(paramList.getFirst());

                                Triples t= new TrFUN((Token)val_peek(1).obj);
                                        triples.add(t);
	                            
	                            LexicalAnalizer.symbolTable.addFNC(((Token)val_peek(1).obj).getLex(), fnc);
								LexicalAnalizer.symbolTable.setType(((Token)val_peek(1).obj).getLex(),((Token)val_peek(2).obj).getLex());
								currentScope=((Token)val_peek(1).obj).getLex();
								LexicalAnalizer.symbolTable.setScope(paramList.getLast(),currentScope);
							}
                            else
                            	yyerror(((Token)val_peek(2).obj).getLine(), "Semantic Error: El id "+ ((Token)val_peek(1).obj).getLex() +" ya fue usado como variable");
                            yyval=val_peek(2);	

							}
break;
case 20:
//#line 94 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta el tipo de retorno de la funcion");}
break;
case 21:
//#line 95 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "No se permiten funciones anonimas");}
break;
case 22:
//#line 98 "gramatica"
{   
                                        permsList.addLast(new Permissions(false,false));
                                        paramList.addLast(((Token) val_peek(1).obj).getLex());
                                        if(!LexicalAnalizer.symbolTable.isUsed(((Token)val_peek(1).obj).getLex())){
                                        	LexicalAnalizer.symbolTable.setType(((Token)val_peek(1).obj).getLex(),((Token)val_peek(2).obj).getLex());
                                        	
                                        }
                                        else
                                        	yyerror(((Token)val_peek(3).obj).getLine(), "Semantic Error: El id "+ ((Token)val_peek(1).obj).getLex() +" ya fue usado como variable");   
                                        yyval=val_peek(2); 
                                     }
break;
case 23:
//#line 109 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta '('");}
break;
case 24:
//#line 110 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta especificar el tipo del parametro de la funcion");}
break;
case 25:
//#line 111 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta ID como parametro de la funcion");}
break;
case 26:
//#line 112 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta ')'");}
break;
case 27:
//#line 113 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta el argumento de la funcion");}
break;
case 28:
//#line 116 "gramatica"
{yyval=val_peek(1);}
break;
case 29:
//#line 117 "gramatica"
{yyval=val_peek(1);}
break;
case 30:
//#line 118 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta '{'");}
break;
case 31:
//#line 119 "gramatica"
{yyerror(((Token) val_peek(4).obj).getLine(), "Falta '}'");}
break;
case 32:
//#line 120 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta sentencia de retorno");}
break;
case 33:
//#line 121 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta sentencia de retorno");}
break;
case 34:
//#line 124 "gramatica"
{Triples t = new TrRET((Operand)val_peek(2).obj);
                                     triples.add(t);
                                     typeRet = ((Operand)val_peek(2).obj).getType();
                                     yyval = new ParserVal(t);}
break;
case 35:
//#line 128 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Retorno de funcion sin 'RETURN'");}
break;
case 36:
//#line 129 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Falta '('");}
break;
case 37:
//#line 130 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta valor de retorno de la funcion");}
break;
case 38:
//#line 131 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta ')'");}
break;
case 39:
//#line 132 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta ','");}
break;
case 44:
//#line 141 "gramatica"
{
                                                                print("Sintaxis: Sentencia IF-ELSE");
                                                                pendingTriples.removeLast();
                                                                triples.remove(triples.size()-2);
                                                                triples.getLast().decreaseId();
                                                            	}
break;
case 45:
//#line 147 "gramatica"
{

                                                                print("Sintaxis: Sentencia IF");
                                                                pendingTriples.removeLast();
                                                                triples.getLast().decreaseId();
                                                                triples.remove(triples.size()-2);}
break;
case 46:
//#line 153 "gramatica"
{yyerror(((Token) val_peek(7).obj).getLine(), "'ELSE' sin 'IF'");}
break;
case 47:
//#line 154 "gramatica"
{yyerror(((Token) val_peek(5).obj).getLine(), "'ENDIF' sin 'IF'");}
break;
case 48:
//#line 155 "gramatica"
{yyerror(((Token) val_peek(7).obj).getLine(), "Falta '(' en condicion de sentencia condicional");}
break;
case 49:
//#line 156 "gramatica"
{yyerror(((Token) val_peek(6).obj).getLine(), "condicion faltante en sentencia condicional");}
break;
case 50:
//#line 157 "gramatica"
{yyerror(((Token) val_peek(7).obj).getLine(), "Falta ')' en la sentencia condicional");}
break;
case 51:
//#line 158 "gramatica"
{yyerror(((Token) val_peek(4).obj).getLine(), "Sentencia/s faltante/s luego de 'IF'");}
break;
case 52:
//#line 159 "gramatica"
{yyerror(((Token) val_peek(4).obj).getLine(), "Falta 'ELSE' o sobran llaves en la declaracion de bloque");}
break;
case 53:
//#line 160 "gramatica"
{yyerror(((Token) val_peek(5).obj).getLine(), "Bloque faltante luego de 'ELSE'");}
break;
case 54:
//#line 161 "gramatica"
{yyerror(((Token) val_peek(6).obj).getLine(), "Falta 'ENDIF'");}
break;
case 55:
//#line 162 "gramatica"
{yyerror(((Token) val_peek(5).obj).getLine(), "Falta ','");}
break;
case 56:
//#line 163 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Falta ','");}
break;
case 57:
//#line 164 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Falta '('");}
break;
case 58:
//#line 165 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Falta condicion en la sentencia condicional");}
break;
case 59:
//#line 166 "gramatica"
{yyerror(((Token) val_peek(4).obj).getLine(), "Falta ')'");}
break;
case 60:
//#line 167 "gramatica"
{yyerror(((Token) val_peek(4).obj).getLine(), "Falta 'ENDIF' en sentencia condicional");}
break;
case 61:
//#line 168 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta bloque de sentencias en sentencia condicional");}
break;
case 62:
//#line 171 "gramatica"
{print("Sintaxis: Sentencia While");
                                                     completeLast();}
break;
case 63:
//#line 174 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Falta'('");}
break;
case 64:
//#line 175 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "Falta ')'");}
break;
case 65:
//#line 176 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Sentencia de control sin bloque de sentencias");}
break;
case 66:
//#line 179 "gramatica"
{Triples label=new Label();
                    triples.add(label);
                    pendingTriples.add(label);}
break;
case 67:
//#line 184 "gramatica"
{
                                    print("Sintaxis: Sentencia print");
                                    Triples aux = new TrPRT((Operand)val_peek(2).obj);
                                    triples.add(aux);}
break;
case 68:
//#line 188 "gramatica"
{yyerror(((Token) val_peek(3).obj).getLine(), "'PRINT' faltante en la sentencia de impresion");}
break;
case 69:
//#line 189 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta '('");}
break;
case 70:
//#line 190 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta ')'");}
break;
case 71:
//#line 191 "gramatica"
{print("Sintaxis: Sentencia print");
                                    Triples aux= new TrPRT();
                                    triples.add(aux);
                                    }
break;
case 72:
//#line 195 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta ','");}
break;
case 73:
//#line 198 "gramatica"
{   if (TypeConverter.isValidBidirectional(((Operand)val_peek(2).obj).getType(),((Operand)val_peek(0).obj).getType())){
									
		                            Triples aux=null;
		                            AtomicReference<Operand> o1=new AtomicReference<>();
		                            AtomicReference<Operand> o2=new AtomicReference<>();

		                            getConversion((Operand)val_peek(2).obj,(Operand)val_peek(0).obj,o1,o2);

	                                String comparator = ((Token)val_peek(1).obj).getLex();
		                            if (comparator.equals("<"))
		                            	aux = new TrLT(o1.get(),o2.get());
		                            else if(comparator.equals(">"))
		                            	aux = new TrGT(o1.get(),o2.get());
		                            else if(comparator.equals("<="))
		                            	aux = new TrLE(o1.get(),o2.get());
		                            else if(comparator.equals(">="))
		                            	aux = new TrGE(o1.get(),o2.get());
		                            else if(comparator.equals("="))
		                            	aux = new TrEQ(o1.get(),o2.get());
		                            else if(comparator.equals("!="))
		                            	aux = new TrNE(o1.get(),o2.get());
	                                triples.add(aux);
	                                Triples t = new TrBF(null, aux);
	                                triples.add(t);
	                                pendingTriples.add(triples.getLast());
								}

                                else
                                	yyerror(((Token)val_peek(1).obj).getLine(),"Semantic Error: Tipos incompatibles en comparacion");
                            }
break;
case 74:
//#line 228 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta expresion del lado izquierdo del comparador");}
break;
case 75:
//#line 229 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta expresion del lado derecho del comparador");}
break;
case 76:
//#line 232 "gramatica"
{blockFound();}
break;
case 77:
//#line 233 "gramatica"
{blockFound();}
break;
case 78:
//#line 234 "gramatica"
{blockFound();}
break;
case 83:
//#line 243 "gramatica"
{	Boolean converted = false;
									if(correctCall(converted, (Operand)val_peek(1).obj,((Token)val_peek(3).obj).getLex(),((Token)val_peek(1).obj).getLex(),(((Token)val_peek(3).obj).getLine()) )){
                                    print("Sintaxis: Invocacion a funcion");
                                    Operand param;
                                    if (converted.booleanValue())
                                    	param=triples.getLast();
                                    else
                                    	param=(Operand)val_peek(1).obj;
                                    Triples t= new TrINV((Token)val_peek(3).obj,param); 
                                    triples.add(t);
                                    if (!paramList.isEmpty()){
                                         if ( ((FNCInformation)LexicalAnalizer.symbolTable.getLexeme(((Token) val_peek(3).obj).getLex())).isWrite() )
                                             permsList.getLast().setWrite(true);
                                          if (((Token) val_peek(3).obj).getLex().equals(paramList.getFirst()))
                                             permsList.getLast().setPass(true);                                   
                                     }   
                                  yyval=new ParserVal(t);                                   
                                 }
                                }
break;
case 84:
//#line 262 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta ')'");}
break;
case 85:
//#line 266 "gramatica"
{yyval=val_peek(2);}
break;
case 86:
//#line 267 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Argumentos mal definidos");}
break;
case 87:
//#line 268 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Llamado a funcion sin argumento");}
break;
case 88:
//#line 269 "gramatica"
{yyerror(((Token) val_peek(1).obj).getLine(), "Falta ';'");}
break;
case 89:
//#line 270 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Especificacion de permisos faltante");}
break;
case 90:
//#line 274 "gramatica"
{
 									if (LexicalAnalizer.symbolTable.isVar(((Token) val_peek(3).obj).getLex(),currentScope)){
	                                    if(TypeConverter.isValid(((Token) val_peek(3).obj).getType(),((Operand) val_peek(1).obj).getType())){
	 										Triples aux;
											if (!((Token) val_peek(3).obj).getType().equals(((Operand) val_peek(1).obj).getType()  )){
	                                            	Triples cnv = new TrCNV((Operand)val_peek(1).obj);
	                                                triples.add(cnv);
	                                                aux = new TrASG((Operand)val_peek(3).obj,cnv);
	                                            }
	                                         else {  
			                                    aux = new TrASG((Operand)val_peek(3).obj,(Operand)val_peek(1).obj);
			                                    }
			                             triples.add(aux);
	                                     if (!paramList.isEmpty())
	                                         if (((Token) val_peek(3).obj).getLex().equals(paramList.getFirst()))
	                                             permsList.getLast().setWrite(true);
	                                     print("Sintaxis: Asignacion");
	                                     }
	                                    else
	                                       yyerror(((Token) val_peek(3).obj).getLine(),"Semantic Error: Tipo invalido en la asignacion :(");
                                      }
                                    else{
                                    	yyerror(((Token) val_peek(3).obj).getLine(),"Semantic Error: variable no definida ");
                               
                                    }
                                    	
                                    	}
break;
case 91:
//#line 301 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Asignacion sin id del lado izq");}
break;
case 92:
//#line 302 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Asignacion sin :=");}
break;
case 93:
//#line 303 "gramatica"
{yyerror(((Token) val_peek(0).obj).getLine(), "Falta expresion del lado derecho del :=");}
break;
case 94:
//#line 304 "gramatica"
{yyerror(((Token) val_peek(2).obj).getLine(), "Falta ','");}
break;
case 95:
//#line 308 "gramatica"
{if (TypeConverter.isValidBidirectional(((Operand)val_peek(2).obj).getType(),((Operand)val_peek(0).obj).getType())){
	                        AtomicReference<Operand> o1=new AtomicReference<>();
	                        AtomicReference<Operand> o2=new AtomicReference<>();

	                        getConversion((Operand)val_peek(2).obj,(Operand)val_peek(0).obj,o1,o2);
	                            
							Triples aux = new TrADD(o1.get(),o2.get());
                            triples.add(aux);
                            yyval = new ParserVal(aux);

							}else{
								yyerror(((Token)val_peek(1).obj).getLine(),"Semantic Error: Tipos incompatibles en suma");
							}
                        }
break;
case 96:
//#line 322 "gramatica"
{if (TypeConverter.isValidBidirectional(((Operand)val_peek(2).obj).getType(),((Operand)val_peek(0).obj).getType())){
	                        AtomicReference<Operand> o1=new AtomicReference<>();
	                        AtomicReference<Operand> o2=new AtomicReference<>();

	                        getConversion((Operand)val_peek(2).obj,(Operand)val_peek(0).obj,o1,o2);
	                            
							Triples aux = new TrSUB(o1.get(),o2.get());
                            triples.add(aux);
                            yyval = new ParserVal(aux);

							}else{
								yyerror(((Token)val_peek(1).obj).getLine(),"Semantic Error: Tipos incompatibles en resta");
							}
							}
break;
case 98:
//#line 339 "gramatica"
{if (TypeConverter.isValidBidirectional(((Operand)val_peek(2).obj).getType(),((Operand)val_peek(0).obj).getType())){
	                        AtomicReference<Operand> o1=new AtomicReference<>();
	                        AtomicReference<Operand> o2=new AtomicReference<>();

	                        getConversion((Operand)val_peek(2).obj,(Operand)val_peek(0).obj,o1,o2);
	                            
							Triples aux = new TrMUL(o1.get(),o2.get());
                            triples.add(aux);
                            yyval = new ParserVal(aux);

							}else{
								yyerror(((Token)val_peek(1).obj).getLine(),"Semantic Error: Tipos incompatibles en multiplicacion");
							}
							}
break;
case 99:
//#line 353 "gramatica"
{if (TypeConverter.isValidBidirectional(((Operand)val_peek(2).obj).getType(),((Operand)val_peek(0).obj).getType()))	{
	                        AtomicReference<Operand> o1=new AtomicReference<>();
	                        AtomicReference<Operand> o2=new AtomicReference<>();

	                        getConversion((Operand)val_peek(2).obj,(Operand)val_peek(0).obj,o1,o2);
	                            
							Triples aux = new TrDIV(o1.get(),o2.get());
                            triples.add(aux);
                            yyval = new ParserVal(aux);

							}else{
								yyerror(((Token)val_peek(1).obj).getLine(),"Semantic Error: Tipos incompatibles en division");
							}}
break;
case 101:
//#line 369 "gramatica"
{functionAllows=Constants.FUNC_ALLOW_READ;}
break;
case 102:
//#line 370 "gramatica"
{functionAllows=Constants.FUNC_ALLOW_WRITE;}
break;
case 103:
//#line 371 "gramatica"
{functionAllows=Constants.FUNC_ALLOW_PASS;}
break;
case 104:
//#line 372 "gramatica"
{functionAllows=Constants.FUNC_ALLOW_WRITEPASS;}
break;
case 108:
//#line 380 "gramatica"
{	if (!LexicalAnalizer.symbolTable.isVar(((Token)val_peek(0).obj).getLex(), currentScope))
						yyerror(((Token)val_peek(0).obj).getLine(),"Semantic Error: La variable (" + ((Token)val_peek(0).obj).getLex() + ") no existe");
				
					yyval=val_peek(0);}
break;
case 109:
//#line 384 "gramatica"
{/*numero es positivo*/
				Object obj = val_peek(0).obj;
				if (!ifUS(((Token) obj)))
					testPos((Token) obj);}
break;
case 110:
//#line 388 "gramatica"
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
                yyval=new ParserVal(obj);}
break;
//#line 1437 "Parser.java"
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
        {yyval=new ParserVal();
        AtomicReference<ParserVal> ref = new AtomicReference<>();
        
        yychar = la.yylex(ref); 
        yylval = ref.get();       //get next character
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
