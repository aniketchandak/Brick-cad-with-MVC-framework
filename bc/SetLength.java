package bc;

import mvc.Command;
import mvc.Utilities;

public class SetLength extends Command {
	
	private Double newLength = null;
	
	public SetLength()  {
		super();
		this.setUndoable(true);
		Double length = null;
		try{
			length = Double.parseDouble(Utilities.askUser("Enter length"));
			if(length<=0) throw new Exception("Dimensiom must be greater than 0");
			
		}
		catch(NumberFormatException e ){
			Utilities.error("Text is not allowed. Please enter integers.");
			this.newLength=null;
		}
		catch(Exception e){
			
			Utilities.error(e.getMessage());
			this.newLength=null;
			
		}
		this.newLength=length;
		
		
	}
	
	public SetLength(Double newLength) {
		super();
		this.setUndoable(true);
		this.newLength = newLength;
	}
	public void execute(){
		if(newLength!=null){
			super.execute(); // make memento
			((Brick) model).setLength(newLength);
		}
	}

}
