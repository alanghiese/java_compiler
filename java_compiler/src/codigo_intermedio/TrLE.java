package codigo_intermedio;

import utilities.Constants;
import utilities.TypeConverter;

public class TrLE extends TrCND {

	public TrLE(Operand o1, Operand o2) {
		super(o1, o2);
		// TODO Auto-generated constructor stub
	}

	public TrLE(Operand o1) {
		super(o1);
		// TODO Auto-generated constructor stub
	}

	public TrLE() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "<="  + "," + o1.getRef()  + "," + o2.getRef();
	}
	
	public String getComp() {
		if (TypeConverter.getConversion(o1.getType(), o2.getType()).equals(Constants.L_INT))
			return "JNLE";
		
		return "JNBE";
	}

}
