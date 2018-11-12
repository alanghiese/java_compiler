package codigo_intermedio;

import java_compiler.LexicalAnalizer;
import utilities.Constants;
import utilities.SymbolInformation;

public class TrRET extends Triples {

	public TrRET(Operand o1, Operand o2) {
		super(o1, o2);
		super.type = super.o1.getType();

		Triples.static_isFunction = false;
		this.isFunction = true;
	}

	public TrRET(Operand o1) {
		super(o1);
		super.type = super.o1.getType();

		Triples.static_isFunction = false;
		this.isFunction = true;
	}

	public TrRET() {
		super();
		super.type = super.o1.getType();

		Triples.static_isFunction = false;
		this.isFunction = true;
	}

	@Override
	public String getType() {
		return super.o1.getType();
	}
	
	@Override
	public String toString() {
		return "RET"  + "," + o1.getRef();
	}
	
	
	
	@Override
	public String generateAssembler() {
		String code;
		if (super.type.equals(Constants.US_INT)) {
			code = "MOV AX, " + o1.getMemRef() + '\n' +
					"MOV @RETu, AX" + '\n' +
					"RET";
		}
		else{
			code = "MOV EAX, " + o1.getMemRef() + '\n' +
					"MOV @RETi, EAX" + '\n' +
					"RET";
			}
		return code;
	}

}
