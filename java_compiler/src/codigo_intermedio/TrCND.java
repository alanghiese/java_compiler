package codigo_intermedio;

import utilities.Constants;

public class TrCND extends Triples {

	public TrCND(Operand o1, Operand o2) {
		super(o1, o2);
		super.type = Constants.TYPE_BOOLEAN;
	}

	public TrCND(Operand o1) {
		super(o1);
		super.type = Constants.TYPE_BOOLEAN;
	}

	public TrCND() {
		super.type = Constants.TYPE_BOOLEAN;
	}

	@Override
	public String getType() {
		return super.type;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "CND" + o1.toString() + o2.toString();
	}
}
