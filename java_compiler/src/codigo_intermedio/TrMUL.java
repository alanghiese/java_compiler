package codigo_intermedio;

public class TrMUL extends TrOperations {

	public TrMUL(Operand o1, Operand o2) {
		super(o1, o2);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "*"  + "," + o1.getRef()  + "," + o2.getRef();
	}
}
