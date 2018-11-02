package codigo_intermedio;

public abstract class Triples extends Operand{

	
	protected String type;
	protected Operand o1;
	protected Operand o2;
	
	public static int static_id=0;
	private int id=0;
	
	public Triples(Operand o1, Operand o2) {
		this.id = Triples.static_id;
		Triples.static_id++;
		
		this.o1 = o1;
		this.o2 = o2;
	}
	
	public void decreaseId() {
		this.id--;
		Triples.static_id--;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getRef() {
		return "["+this.getId()+"]";
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
