package utilities;

public class Decoder {
	
	
	public static Integer get(String s) {
            
            switch (s) {
                case "+": return (int)'+';
                case "-": return (int)'-';
				case "*": return (int)'*';
				case "/": return (int)'/';
				case "<": return (int)'<';
				case ">": return (int)'>';
				case "=": return (int)'=';
				case "(": return (int)'(';
				case ")": return (int)')';
				case "{": return (int)'{';
				case "}": return (int)'}';
				case ",": return (int)',';
				case ";": return (int)';';
			    case "陰": return -58;//(int)'$';
				case ">=": return 257;
				case "<=": return 258;
				case "!=": return 259;
				case ":=": return 260;
				case "if": return 261;
				case "endif": return 262;
				case "else": return 263;
				case "print": return 264;
				case "while": return 265;
				case "usinteger": return 266;
				case "linteger": return 267;
				case "readonly": return 268;
				case "write": return 269;
				case "pass": return 270;
				case "ID": return 271;
				case "CTE": return 272;
				case "return": return 273;
				case "STRING": return 274;
				
            }
            
            return -1;
	}

}
