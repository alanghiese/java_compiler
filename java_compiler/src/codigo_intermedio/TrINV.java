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
		if (super.type.equals(Constants.US_INT)) {
			code = "MOV AX, " + o2.getMemRef() + '\n' +
					"MOV " + LexicalAnalizer.symbolTable.getParamName(o1.getMemRef()) + ", AX" + '\n' +
					"CALL " + name + '\n' +
					"MOV AX, " + LexicalAnalizer.symbolTable.getParamName(o1.getMemRef()) + '\n' +
					"MOV " + o2.getMemRef() + ", AX" + '\n' +
					"MOV AX, @RETu" + '\n' + 
					"MOV " + this.destiny + ", AX"; 
		}
		else{
			code ="MOV EAX, " + o2.getMemRef() + '\n' +
					"MOV " + LexicalAnalizer.symbolTable.getParamName(o1.getMemRef()) + ", EAX" + '\n' +
					"CALL " + name + '\n' + 
					"MOV EAX, " + LexicalAnalizer.symbolTable.getParamName(o1.getMemRef()) + '\n' +
					"MOV " + o2.getMemRef() + ", EAX" + '\n' +
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
