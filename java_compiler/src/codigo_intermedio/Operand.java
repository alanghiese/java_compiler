package codigo_intermedio;

public abstract class Operand {

	public static int static_id=0;
	private int id=0;
	
	public Operand() {
		this.id = Triples.static_id;
		Triples.static_id++;
	}
	
	public void decreaseId() {
		this.id--;
		Triples.static_id--;
	}
	
	public abstract String getType();
	
	@Override
	public abstract String toString();
	
	public String getRef() {
		return "["+this.getId()+"]";
	}
	
	public int getId() {
		return this.id;
	}
}
