package semantic_actions;

import java_compiler.ParserVal;
import java_compiler.SymbolTable;
import utilities.CTNInformation;
import utilities.Constants;
import utilities.Decoder;
import utilities.Token;

public class SA2 implements SemanticAction {

	@Override
	public void execute(StringBuilder buffer, StringBuilder line, Token token, SymbolTable st, ParserVal yylval) {
		buffer.append(line.charAt(0));
		line.deleteCharAt(0);

		StringBuilder withoutSuffix = new StringBuilder();
		StringBuilder type = null;

		if (buffer.charAt(buffer.length() - 1) == 'i') {
			type = new StringBuilder(Constants.US_INT);
			for (int i = 0; i < buffer.length() - 3; i++)
				withoutSuffix.append(buffer.charAt(i));
			Integer number = Integer.parseInt(withoutSuffix.toString());
			if (number < 0 || number > Constants.MAX_UN) {
				System.out.println("WARNING: variable fuera de rango");
				withoutSuffix = new StringBuilder(Constants.MAX_UN);
			}

		} else if (buffer.charAt(buffer.length() - 1) == 'l') {
			type = new StringBuilder(Constants.L_INT);
			for (int i = 0; i < buffer.length() - 2; i++)
				withoutSuffix.append(buffer.charAt(i));
			Long number = Long.parseLong(withoutSuffix.toString());
			if (number < 0 || number > Integer.MAX_VALUE) {
				System.out.println("WARNING: variable fuera de rango");
				number = (long) Integer.MAX_VALUE;
				withoutSuffix = new StringBuilder(number.toString());
			}
		}

		CTNInformation ctni = new CTNInformation();
		ctni.setType(type.toString());
		st.addCTN(withoutSuffix.toString(), ctni);
		token.setMsg(Constants.CTE);
		token.setToken(Decoder.get(Constants.CTE));
		token.setLex(withoutSuffix.toString());

		//yylval = new ParserVal();

		//yylval = new ParserVal(new String(buffer.toString()),token);
		yylval.obj = token;
		yylval.sval = new String(buffer.toString());

		buffer.setLength(0);

	}

}
