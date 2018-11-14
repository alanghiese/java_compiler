package codigo_intermedio;

import utilities.Constants;
import utilities.TypeConverter;

public class TrCND extends Triples {

	public TrCND(Operand o1, Operand o2) {
		super(o1, o2);
		super.type = TypeConverter.getConversion(o1.getType(), o2.getType());
		
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
		return "CND"  + "," + o1.getRef()  + "," + o2.getRef();
	}
	
	public String getComp() {
		return "";
	}

	@Override
	public String generateAssembler() {
		String code;
		if (super.type.equals(Constants.US_INT)) {
			code = "MOV AX," + o1.getMemRef() + '\n' +
					"CMP AX," + o2.getMemRef() ;/*+ '\n'; +
					"MOV " + destiny + ",AX";
			SymbolInformation symb = new SymbolInformation();
			symb.setType(Constants.US_INT);
			LexicalAnalizer.symbolTable.addAUX(destiny, symb);*/
		}
		else{
			code = "MOV EAX," + o1.getMemRef() + '\n' +
					"CMP EAX," + o2.getMemRef() ;/*+ '\n' +
					"MOV " + destiny + ",EAX";
			SymbolInformation symb = new SymbolInformation();
			symb.setType(Constants.L_INT);
			LexicalAnalizer.symbolTable.addAUX(destiny, symb);*/
			}
		return code;
	}
}
