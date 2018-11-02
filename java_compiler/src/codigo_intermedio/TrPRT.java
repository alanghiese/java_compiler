package codigo_intermedio;

public class TrPRT extends Triples {

	public TrPRT(Operand o1, Operand o2) {
		super(o1, o2);
	}

	public TrPRT(Operand o1) {
		super(o1);
	}

	public TrPRT() {
	}

	@Override
	public String getType() {
		return "PRT";
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Print"  + "," + o1.getRef()  + "," + o2.getRef();
	}

}
