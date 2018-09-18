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
				case ">=": return 257;
				case "<=": return 258;
				case "!=": return 259;
				case ":=": return 260;
				case "if": return 261;
				case "end_if": return 262;
				case "else": return 263;
				case "print": return 264;
				case "while": return 265;
				case "usinteger": return 266;
				case "linteger": return 267;
				case "readonly": return 268;
				case "write": return 269;
				case "pass": return 270;
				case "ID": return 270;
				case "CTE": return 271;
				case "END": return 272;
            }
            
            return -1;
	}
	
}
