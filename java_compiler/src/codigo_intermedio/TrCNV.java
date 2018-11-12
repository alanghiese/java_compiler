package codigo_intermedio;

import java_compiler.LexicalAnalizer;
import utilities.Constants;
import utilities.SymbolInformation;

public class TrCNV extends Triples {

	public TrCNV(Operand o1, Operand o2) {
		super(o1, o2);
		// TODO Auto-generated constructor stub
	}

	public TrCNV(Operand o1) {
		super(o1);
		// TODO Auto-generated constructor stub
	}

	public TrCNV() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return Constants.L_INT;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "CNV"  + "," + this.getType() + "," + o1.getRef();
	}

	@Override
	public String generateAssembler() {
		String code;
		code = "MOV EAX, 0" + '\n' +
				"ADD AX," + o1.getMemRef() + '\n' +
				"MOV " + this.destiny + ",EAX";
		SymbolInformation symb = new SymbolInformation();
		symb.setType(Constants.L_INT);
		symb.setAuxiliary();
		LexicalAnalizer.symbolTable.addAUX(this.destiny, symb);
		return code;
	}

}
