package codigo_intermedio;

import java_compiler.LexicalAnalizer;
import utilities.Token;

public class TrINV extends Triples {

	public TrINV(Operand o1, Operand o2) {
		super(o1, o2);
		super.type = LexicalAnalizer.symbolTable.getType(((Token) o1).getLex());
		// TODO Auto-generated constructor stub
	}

	public TrINV(Operand o1) {
		super(o1);
		
		super.type = LexicalAnalizer.symbolTable.getType(((Token) o1).getLex());
		
		// TODO Auto-generated constructor stub
	}

	public TrINV() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "INV" + "," + o1.getRef() + "," + o2.getRef();
	}
	
	

}
