package bc;

import mvc.Command;
import mvc.Utilities;

public class SetHeight extends Command {
	
	private Double newHeight = null;
	public SetHeight()  {
		super();
		this.setUndoable(true);
		Double height = null;
		try{
			height = Double.parseDouble(Utilities.askUser("Enter height"));
			if(height<=0) throw new Exception("Dimensiom must be greater than 0");
			
			
		}
		catch(NumberFormatException e ){
			Utilities.error("Text is not allowed. Please enter integers.");
			this.newHeight=null;
		}
		catch(Exception e){
			Utilities.error(e.getMessage());
			this.newHeight=null;
			
			
		}
		this.newHeight=height;	
	}
	
	public SetHeight(Double newheight) {
		super();
		this.setUndoable(true);
		this.newHeight = newheight;	
	}


	public void execute(){
		if(newHeight!=null){
			super.execute(); // make memento
			((Brick) model).setHeight(newHeight);
		}
			
	}

}
