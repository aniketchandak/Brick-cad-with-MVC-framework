package bc;

import mvc.Command;
import mvc.Utilities;

public class SetWidth extends Command {
	
	private Double newWidth = null;
	public SetWidth()  {
		super();
		this.setUndoable(true);
		Double width = null;
		try{
			width = Double.parseDouble(Utilities.askUser("Enter width"));
			if(width<=0) throw new Exception("Dimensiom must be greater than 0");
			
		}
		catch(NumberFormatException e ){
			Utilities.error("Text is not allowed. Please enter integers.");
			this.newWidth=null;
		}
		catch(Exception e){
			Utilities.error(e.getMessage());
			this.newWidth=null;
			
		}
		this.newWidth=width;
		
		
	}

	public SetWidth(Double newWidth) {
		super();
		this.setUndoable(true);
		this.newWidth = newWidth;
	}
	public void execute(){
		if(newWidth!=null){
			super.execute(); // make memento
			((Brick) model).setWidth(newWidth);
		}
	}

}
