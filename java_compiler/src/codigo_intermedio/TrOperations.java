package codigo_intermedio;

import utilities.TypeConverter;

public abstract class TrOperations extends Triples{

	public TrOperations (Operand o1, Operand o2) {
		super(o1,o2);
		super.type = TypeConverter.getConversion(this.o1.getType(), this.o2.getType());
	}
	
	@Override
	public String getType() {
		return super.type;
	}

}
