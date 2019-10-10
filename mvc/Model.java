package mvc;

import java.io.Serializable;
import java.util.Observable;

public abstract class Model extends Observable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName ; 
	private boolean unsavedChanges ;  //We change this to false when user saves or save as
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public boolean getUnsavedChanges() {
		return unsavedChanges;
	}
	public void setUnsavedChanges(boolean unsavedChanges) {
		this.unsavedChanges = unsavedChanges;
	}

	public void changed(){
		this.setUnsavedChanges(true);
		setChanged();
		notifyObservers();
		clearChanged();
	}
	
	
	abstract public Memento makeMemento();
	
	abstract public void accept(Memento m); // restore to memento state
	
	
	
}
