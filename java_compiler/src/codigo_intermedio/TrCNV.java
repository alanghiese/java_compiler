package codigo_intermedio;

import utilities.Constants;

public class TrCNV extends Triples {

	public TrCNV(Operand o1, Operand o2) {
		super(o1, o2);
		// TODO Auto-generated constructor stub
	}

	public TrCNV(Operand o1) {
		super(o1);
		// TODO Auto-generated constructor stub
	}

	public TrCNV() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return Constants.L_INT;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "CNV"  + "," + this.getType() + "," + o1.getRef();
	}

}
