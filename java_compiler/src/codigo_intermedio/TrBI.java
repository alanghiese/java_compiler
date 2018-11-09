package codigo_intermedio;

public class TrBI extends Triples {

	public TrBI(Operand o1, Operand o2) {
		super(o1, o2);
	}

	public TrBI(Operand o1) {
		super(o1);
	}

	public TrBI() {
		
	}

	@Override
	public String getType() {
		return "BI";
	}

	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "BI"  + "," + o1.getRef();
	}
}
