package codigo_intermedio;

public class TrLT extends TrCND {

	public TrLT(Operand o1, Operand o2) {
		super(o1, o2);
		// TODO Auto-generated constructor stub
	}

	public TrLT(Operand o1) {
		super(o1);
		// TODO Auto-generated constructor stub
	}

	public TrLT() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "<" + o1.toString() + o2.toString();
	}

}
