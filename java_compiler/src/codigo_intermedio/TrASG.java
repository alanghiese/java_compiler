package codigo_intermedio;

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
		return ":=" + o1.toString() + o2.toString();
	}

}
