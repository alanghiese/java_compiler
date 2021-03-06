package codigo_intermedio;

import java_compiler.LexicalAnalizer;
import utilities.Constants;
import utilities.SymbolInformation;

public class TrSUB extends TrOperations{
	
	public TrSUB(Operand o1, Operand o2) {
		super(o1,o2);
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "-"  + "," + o1.getRef()  + "," + o2.getRef();
	}

	
	@Override
	public String generateAssembler() {
		String code;
		if (super.type.equals(Constants.US_INT)) {
			code = "MOV AX," + o1.getMemRef() + '\n' +
					"SUB AX," + o2.getMemRef() + '\n' +
					"MOV " + destiny + ",AX" + '\n' + 
					"JC NEGSUB";
			SymbolInformation symb = new SymbolInformation();
			symb.setType(Constants.US_INT);
			symb.setAuxiliary();
			LexicalAnalizer.symbolTable.addAUX(destiny, symb);
		}
		else{
			code = "MOV EAX," + o1.getMemRef() + '\n' +
					"SUB EAX," + o2.getMemRef() + '\n' +
					"MOV " + destiny + ",EAX";
			SymbolInformation symb = new SymbolInformation();
			symb.setType(Constants.L_INT);
			symb.setAuxiliary();
			LexicalAnalizer.symbolTable.addAUX(destiny, symb);
			}
		return code;
	}
	

}
