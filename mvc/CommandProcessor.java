package mvc;

import java.util.EmptyStackException;
import java.util.Stack;



public class CommandProcessor {
	public Stack<Command> undoStack=new Stack<Command>();
	public Stack<Command> redoStack=new Stack<Command>();

	
	//Multiple command processor is confusing use singleton pattern
	private static CommandProcessor theCommandProcessor=null;
	public static CommandProcessor makeCommandProcessor(){
		if (theCommandProcessor==null){
			theCommandProcessor=new CommandProcessor();
		}
		return theCommandProcessor;
	}
	
	//By default there is default constructor but its public so anyone can call it and make multiple command Processor hence declare=ing private default constructor
	private CommandProcessor(){
		
	}
	public void execute(Command cmmd){
		cmmd.execute();
		//Clear redo stack
		redoStack.clear();
		if(cmmd.isUndoable()){
			undoStack.push(cmmd);
		}
	}
	
	public void undo(){

		try{
			Command cmmd=undoStack.pop();
			cmmd.undo();
			redoStack.push(cmmd);
		}
		catch(EmptyStackException e){
			Utilities.error("No operations to undo");
		}
		
	}
	
	public void redo(){
		try{
			Command cmmd=redoStack.pop();
			cmmd.execute();
			if(cmmd.isUndoable()){
				undoStack.push(cmmd);
			}
		}
		catch(EmptyStackException e){
			Utilities.error("No operations to redo");
		}
		
		
	}

}
