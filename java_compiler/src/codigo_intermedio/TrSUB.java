package codigo_intermedio;

public class TrSUB extends TrOperations{
	
	public TrSUB(Operand o1, Operand o2) {
		super(o1,o2);
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "-" + o1.toString() + o2.toString();
	}

	

}
