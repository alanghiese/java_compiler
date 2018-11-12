package codigo_intermedio;



public class TrPRT extends Triples {

	public TrPRT(Operand o1, Operand o2) {
		super(o1, o2);
	}

	public TrPRT(Operand o1) {
		super(o1);
	}

	public TrPRT() {
		super();
	}

	@Override
	public String getType() {
		return "PRT";
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Print"  + "," + o1.getRef();
	}

	
	@Override
	public String generateAssembler() {
		String code;
		code = "invoke MessageBox, NULL, addr " + o1.getMemRef().replaceAll(" ", "_") + ", addr " + o1.getMemRef().replaceAll(" ", "_") + ", MB_OK";
		return code;
	}
}
