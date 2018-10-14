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
			    case "陰": return Constants.FINAL_TOKEN;//(int)'$';
				case ":=": return 257;
				case "if": return 258;
				case "endif": return 259;
				case "else": return 260;
				case "print": return 261;
				case "while": return 262;
				case "usinteger": return 263;
				case "linteger": return 264;
				case "readonly": return 265;
				case "write": return 266;
				case "pass": return 267;
				case "ID": return 268;
				case "CTE": return 269;
				case "return": return 270;
				case "STRING": return 271;
				case ">=": return 275;
				case "<=": return 276;
				case "!=": return 277;
				
            }
            
            return -1;
	}
	

}
