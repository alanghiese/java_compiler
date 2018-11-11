package codigo_intermedio;

import java_compiler.LexicalAnalizer;
import utilities.Constants;
import utilities.SymbolInformation;
import utilities.TypeConverter;

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
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ">="  + "," + o1.getRef()  + "," + o2.getRef();
	}
	
	public String getComp() {
		if (TypeConverter.getConversion(o1.getType(), o2.getType()).equals(Constants.L_INT))
			return "JNGE";
		
		return "JNAE";
	}
	
}
