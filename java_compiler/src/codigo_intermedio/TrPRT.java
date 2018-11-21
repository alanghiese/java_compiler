package codigo_intermedio;

import java_compiler.LexicalAnalizer;
import utilities.CTNInformation;
import utilities.Token;

public class TrPRT extends Triples {

	public TrPRT(Operand o1, Operand o2) {
		super(o1, o2);
	}

	public TrPRT(Operand o1) {
		super(o1);
	}

	public TrPRT() {
		super();
	}

	@Override
	public String getType() {
		return "PRT";
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Print"  + "," + o1.getRef();
	}

	
	@Override
	public String generateAssembler() {
		String code;
		code = "invoke MessageBox, NULL, addr " + "@STRING" + ((CTNInformation)LexicalAnalizer.symbolTable.getLexeme(((Token)o1).getLex())).getMyId() + ", addr " + "@STRING" + ((CTNInformation)LexicalAnalizer.symbolTable.getLexeme(((Token)o1).getLex())).getMyId() + ", MB_OK";
		return code;
	}
}
