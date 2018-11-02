package codigo_intermedio;

public class TrRET extends Triples {

	public TrRET(Operand o1, Operand o2) {
		super(o1, o2);
		super.type = super.o1.getType();
	}

	public TrRET(Operand o1) {
		super(o1);
		super.type = super.o1.getType();
	}

	public TrRET() {
		super.type = super.o1.getType();
	}

	@Override
	public String getType() {
		return super.o1.getType();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "RET"  + "," + o1.getRef()  + "," + o2.getRef();
	}

}
