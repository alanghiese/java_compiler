package codigo_intermedio;

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
		return "BF"  + "," + o1.getRef()  + "," + o2.getRef();
	}

}
