package codigo_intermedio;

public class Label extends Triples {

	public Label(Operand o1, Operand o2) {
		super(o1, o2);
		// TODO Auto-generated constructor stub
	}

	public Label(Operand o1) {
		super(o1);
		// TODO Auto-generated constructor stub
	}
	

	public Label() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Label";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Label [" + this.getId() + "]" ;
	}

}
