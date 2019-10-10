package mvc;

import java.util.Observer;

import javax.swing.JPanel;

abstract public class View extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	protected Model model;
	private boolean packable;
	public boolean isPackable() {
		return packable;
	}

	public void setPackable(boolean packable) {
		this.packable = packable;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
		this.model.addObserver(this);
		this.update(model,null);
	}
	
	public View(){
		super();
		packable=false;
		
	}
	public View(Model model) {
		super();
		this.model = model;
		this.model.addObserver(this);  // View will observe model for changes. If there is any change in model, update method will be called
		this.update(model,null);
		packable=false;
	}
	

}
