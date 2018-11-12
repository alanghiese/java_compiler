package codigo_intermedio;

import java_compiler.LexicalAnalizer;
import utilities.Constants;
import utilities.SymbolInformation;

public class TrBF extends Triples {

	public TrBF(Operand o1, Operand o2) {
		super(o1, o2);
		// TODO Auto-generated constructor stub
	}

	public TrBF(Operand o1) {
		super(o1);
		// TODO Auto-generated constructor stub
	}

	public TrBF() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "BF"  + "," + o1.getRef();
	}
	
	@Override
	public String generateAssembler() {
		String code;
		code = ((TrCND)o2).getComp() + " " + o1.getMemRef(); 
		
		return code;
	}

}
