package codigo_intermedio;

import java_compiler.LexicalAnalizer;
import utilities.Constants;
import utilities.SymbolInformation;
import utilities.Token;

public class TrFUN extends Triples {
	
	private String name;

	
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
		code = ((Token)o1).getLex();
		return code;
		
	}
}
