package codigo_intermedio;

public abstract class Triples extends Operand{

	
	protected String type;
	protected Operand o1;
	protected Operand o2;
	protected String destiny;
	protected static boolean static_isFunction = false;
	public boolean isFunction = false;
	
	public static int static_id=0;
	protected int id=0;
	
	
	public Triples(Operand o1, Operand o2) {
		if (Triples.static_isFunction)
			this.isFunction = true;
		this.id = Triples.static_id;
		Triples.static_id++;
		this.destiny = "@AUX" + this.id; 
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
		if (Triples.static_isFunction)
			this.isFunction = true;
		this.o1 = o1;
		this.id = Triples.static_id;
		Triples.static_id++;
		this.destiny = "@AUX" + this.id;
	}
	
	public Triples() {
		if (Triples.static_isFunction)
			this.isFunction = true;
		this.id = Triples.static_id;
		Triples.static_id++;
		this.destiny = "@AUX" + this.id;
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
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public abstract String getType();
	
	@Override
	public abstract String toString();
	
	public String getMemRef() {
		return this.destiny;
	}
	
	public abstract String generateAssembler();
	
}
