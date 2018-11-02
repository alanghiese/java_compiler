package codigo_intermedio;

public class TrGE extends TrCND {

	public TrGE(Operand o1, Operand o2) {
		super(o1, o2);
		// TODO Auto-generated constructor stub
	}

	public TrGE(Operand o1) {
		super(o1);
		// TODO Auto-generated constructor stub
	}

	public TrGE() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ">="  + "," + o1.getRef()  + "," + o2.getRef();
	}
}
