package codigo_intermedio;

import java_compiler.LexicalAnalizer;
import utilities.Constants;
import utilities.SymbolInformation;

public class TrASG extends Triples {

	public TrASG(Operand o1, Operand o2) {
		super(o1, o2);
		super.type = this.o1.getType();
	}

	public TrASG(Operand o1) {
		super(o1);
		super.type = this.o1.getType();
	}

	public TrASG() {
		super.type = this.o1.getType();
	}

	@Override
	public String getType() {
		return super.type;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ":="  + "," + o1.getRef()  + "," + o2.getRef();
	}
	
	
	@Override
	public String generateAssembler() {
		String code;
		if (super.type.equals(Constants.US_INT)) {
			code = "MOV AX," + o2.getMemRef() + '\n' +
					"MOV " + o1.getMemRef() + ",AX" ;
		}
		else{
			code = "MOV EAX," + o2.getMemRef() + '\n' +
					"MOV " + o1.getMemRef() + ",EAX" ;
			}
		return code;
	}

}
