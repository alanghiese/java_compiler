package codigo_intermedio;

public class TrFUN extends Triples {

	public TrFUN(Operand o1, Operand o2) {
		super(o1, o2);
		// TODO Auto-generated constructor stub
	}

	public TrFUN(Operand o1) {
		super(o1);
		// TODO Auto-generated constructor stub
	}

	public TrFUN() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Comienzo de Funcion";
	}
	
	@Override
	public String toString() {

		return "FNC";
	}

}
