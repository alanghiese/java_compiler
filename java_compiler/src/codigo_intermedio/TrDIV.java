package codigo_intermedio;

import java_compiler.LexicalAnalizer;
import utilities.Constants;
import utilities.SymbolInformation;

public class TrDIV extends TrOperations {

	public TrDIV(Operand o1, Operand o2) {
		super(o1, o2);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "/"  + "," + o1.getRef()  + "," + o2.getRef();
	}
	
	
	@Override
	public String generateAssembler() {
		String code;
		if (super.type.equals(Constants.US_INT)) {
			code = "ADD " + o2.getMemRef() + ",0" + '\n' +
					"JZ DIVZERO" + '\n' + 
					"MOV AX," + o1.getMemRef() + '\n' +
					"CWD" + '\n' + 
					"DIV " + o2.getMemRef() + '\n' +
					"MOV " + destiny + ",AX";
			SymbolInformation symb = new SymbolInformation();
			symb.setType(Constants.US_INT);
			LexicalAnalizer.symbolTable.addAUX(destiny, symb);
		}
		else{
			code = "ADD " + o2.getMemRef() + ",0" + '\n' +
					"JZ DIVZERO" + '\n' + 
					"MOV EAX," + o1.getMemRef() + '\n' +
					"CDQ" + '\n' + 
					"IDIV " + o2.getMemRef() + '\n' +
					"MOV " + destiny + ",EAX";
			SymbolInformation symb = new SymbolInformation();
			symb.setType(Constants.L_INT);
			LexicalAnalizer.symbolTable.addAUX(destiny, symb);
			}
		return code;
	}
	

}
