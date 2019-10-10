package bc;
import mvc.Memento;
import mvc.Model;
public class Brick extends Model{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double height,width,length; 

	public Brick(Double height, Double width, Double length) {
		super();
		this.height = height;
		this.width = width;
		this.length = length;
	}
	public Brick(){
		this(10.0,20.0,30.0);
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
		changed();
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
		changed();
	}
	public Double getLength() {
		return length;
	}
	public void setLength(Double length) {
		this.length = length;
		changed();
	}
	
	private class BrickMemento implements Memento{
	 Double height,width,length;

	public BrickMemento(Brick b) {
		super();
		this.height = b.getHeight();
		this.width = b.getWidth();
		this.length = b.getLength();
	}
	 
	}
	
	public Memento makeMemento(){
		return new BrickMemento(this);
	}
	
	public void accept(Memento m){
		if (m instanceof BrickMemento){
			// Change all three fields and then call changed 
			this.height=((BrickMemento) m).height;
			this.length=((BrickMemento) m).length;
			this.width=((BrickMemento) m).width;
			changed();
		}
	}
	
	
}
