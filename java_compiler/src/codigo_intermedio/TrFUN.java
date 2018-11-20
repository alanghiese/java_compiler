package codigo_intermedio;


public class TrFUN extends Triples {
	
	public TrFUN(Operand o1) {
		super(o1);
		Triples.static_isFunction = true;
		this.isFunction = true;
		// TODO Auto-generated constructor stub
	}

	public TrFUN() {
		super();
		Triples.static_isFunction = true;
		this.isFunction = true;
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

	@Override
	public String generateAssembler() {
		
		String code;
		StringBuilder name = new StringBuilder("@");
		name.append(o1.getMemRef());
		code = name.toString() + ":";
		return code;
		
	}
}
