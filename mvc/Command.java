package mvc;

abstract public class Command {
	
	protected Model model;
	private Memento memento;
	private boolean undoable;
	
	public void setUndoable(boolean undoable) {
		this.undoable = undoable;
	}

	public void execute(){
		if (undoable){
			memento=model.makeMemento();
		}
	}
	
	public void undo(){
		if (undoable){
		model.accept(memento);
		}
	}

	public boolean isUndoable() {
		// TODO Auto-generated method stub
		return undoable;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
}
