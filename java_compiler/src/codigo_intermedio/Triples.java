package codigo_intermedio;

public abstract class Triples extends Operand{

	
	protected String type;
	protected Operand o1;
	protected Operand o2;
	
	public Triples(Operand o1, Operand o2) {
		
		
		this.o1 = o1;
		this.o2 = o2;
	}
	
	
	
	
	public Triples(Operand o1) {
		this.o1 = o1;
	}
	
	public Triples() {
		
	}
	
	public Operand getO1() {
		return this.o1;
	}
	
	public Operand getO2() {
		return this.o2;
	}
	public void setO1(Operand o1) {
		this.o1 = o1;
	}
	
	
	@Override
	public abstract String getType();
	
	@Override
	public abstract String toString();
	
}
