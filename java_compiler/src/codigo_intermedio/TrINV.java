package codigo_intermedio;

import java_compiler.LexicalAnalizer;
import utilities.Constants;
import utilities.SymbolInformation;
import utilities.Token;

public class TrINV extends Triples {

	public TrINV(Operand o1, Operand o2) {
		super(o1, o2);
		super.type = LexicalAnalizer.symbolTable.getType(((Token) o1).getLex());
		this.destiny = "@ret"+o1.getRef();
		// TODO Auto-generated constructor stub
	}

	public TrINV(Operand o1) {
		super(o1);
		
		super.type = LexicalAnalizer.symbolTable.getType(((Token) o1).getLex());
		
		
		// TODO Auto-generated constructor stub
	}

	public TrINV() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "INV" + "," + o1.getRef() + "," + o2.getRef();
	}
	
	
	
	
	@Override
	public String generateAssembler() {
		String code;
		StringBuilder name = new StringBuilder("@");
		name.append(o1.getMemRef());
		String paramType = new String();
		
		if (LexicalAnalizer.symbolTable.getType(o2.getMemRef()).equals(Constants.US_INT))
			paramType = "AX";
		else
			paramType = "EAX";
		if (super.type.equals(Constants.US_INT)) {
			
			code = "MOV "+paramType+", " + o2.getMemRef() + '\n' +
					"MOV " + LexicalAnalizer.symbolTable.getParamName(o1.getMemRef()) + ", " + paramType + '\n' +
					"CALL " + name + '\n' +
					"MOV "+paramType+", " + LexicalAnalizer.symbolTable.getParamName(o1.getMemRef()) + '\n' +
					"MOV " + o2.getMemRef() +  ", " + paramType + '\n' +
					"MOV AX, @RETu" + '\n' + 
					"MOV " + this.destiny + ", AX"; 
		}
		else{
			code ="MOV "+paramType+", " + o2.getMemRef() + '\n' +
					"MOV " + LexicalAnalizer.symbolTable.getParamName(o1.getMemRef()) + ", " + paramType + '\n' +
					"CALL " + name + '\n' +
					"MOV "+paramType+", " + LexicalAnalizer.symbolTable.getParamName(o1.getMemRef()) + '\n' +
					"MOV " + o2.getMemRef() +  ", " + paramType + '\n' +
					"MOV EAX, @RETi" + '\n' + 
					"MOV " + this.destiny + ", EAX"; 
			}
		SymbolInformation symb = new SymbolInformation();
		symb.setType(super.type);
		symb.setAuxiliary();
		LexicalAnalizer.symbolTable.addAUX(destiny, symb);
		return code;
	}
	
	
 
}
