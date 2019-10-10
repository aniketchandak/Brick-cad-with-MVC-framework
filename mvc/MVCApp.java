package mvc;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;


public class MVCApp extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDesktopPane desktop;
	private AppFactory factory;
	private Model model;
	private CommandProcessor commandProcessor;

	public MVCApp(AppFactory factory) {

		this.factory = factory;
		this.model = factory.makeModel();
		this.commandProcessor = CommandProcessor.makeCommandProcessor();

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultLookAndFeelDecorated(true);
		desktop = new JDesktopPane(); //a specialized layered pane

		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset,screenSize.width  - inset*2,screenSize.height - inset*2);
		
		showView(null);

		setContentPane(desktop);
		setJMenuBar(createMenuBar());

		//Make dragging a little faster but perhaps uglier.
		desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

	}
	
	private void showView(String type) {
		if (type == null) type = factory.getViews()[0];
		View view = factory.makeView(type);
		view.setModel(model);
		ViewFrame vf = new ViewFrame(view);
		vf.setSize(200, 100);
		vf.setTitle(type);
		if (view.isPackable()) {
			vf.pack();
		}
		vf.setVisible(true);
		desktop.add(vf);
		try {
			vf.setSelected(true);
		} catch(Exception e) {
			Utilities.error(e);
		}
	}

	protected JMenuBar createMenuBar() {		
		JMenu fileMenu= Utilities.makeMenu("File", new String[]{"New","Open","Save","SaveAs","Close"}, this);
		ActionListener e=new EditHandler() ;
		ActionListener v=new ViewHandler();
		JMenu editMenu= Utilities.makeMenu("Edit", factory.getCommands(), e);  
		JMenu viewMenu= Utilities.makeMenu("View", factory.getViews(), v);
		JMenu helpMenu= Utilities.makeMenu("Help", new String[]{"Help","About"}, this); // Need to think about use of help and about from factry  here
		// create a menu bar containing File, Edit, View, and Help menus
		//use utility
		JMenuBar menuBar=new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(viewMenu);
		menuBar.add(helpMenu);
		return menuBar;
	}

	public void actionPerformed(ActionEvent e){
    	String cmmd = e.getActionCommand();
    	if (cmmd == "Save") {
    		Utilities.save(model,false);
    	} else if (cmmd == "SaveAs") {
    		Utilities.save(model, true);
    	} else if (cmmd == "Open") {    			
    		this.model=Utilities.open(model);
    		for(JInternalFrame frame: desktop.getAllFrames()) {
    			frame.setVisible(false);
    		}
    		showView(null);
    	} else if (cmmd == "New") {
    		Utilities.saveChanges(model);
    		for(JInternalFrame frame: desktop.getAllFrames()) {
    			frame.setVisible(false);
    		}
    		model = factory.makeModel();
    		showView(null);
    	}  else if (cmmd == "Close") {
    		Utilities.saveChanges(model);
    		System.exit(1);
    	} else if (cmmd == "Help") {
    		Utilities.informUser(factory.getHelp());
    	} else if (cmmd == "About") {
    		Utilities.informUser(factory.about());
    	} else {
    		Utilities.error("Unrecognized command: " + cmmd);
    	}
    }

	// sort of works
	class ViewHandler implements ActionListener {
		public void actionPerformed(ActionEvent e){
			String cmmd = e.getActionCommand();
			showView(cmmd);
//			View panel = factory.makeView(cmmd);
//			panel.setModel(model);
//			ViewFrame vf = new ViewFrame(panel);
//			vf.setVisible(true);
//			desktop.add(vf);
//			try {
//				vf.setSelected(true);
//			} catch (java.beans.PropertyVetoException ex) {}
		}
	}

    class EditHandler implements ActionListener {
    	public void actionPerformed(ActionEvent e){
        	// make a command and ask command processor to execute it
    		String action=e.getActionCommand();
    		if (action == "Undo") {
        		commandProcessor.undo();
        	} else if (action == "Redo") {
        		commandProcessor.redo();
        	} else{
        		Command cmmd= factory.makeCommand(action);
        		cmmd.setModel(model);
        		commandProcessor.execute(cmmd);
        	}
    	}
    }

    public static void run(AppFactory factory) {
    	try {
    		MVCApp app = new MVCApp(factory);
    		app.setSize(800,600);
			app.setTitle(factory.getTitle());
			app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			app.setVisible(true);
    	} catch(Exception e) {
    		Utilities.error("" + e);
    	}

    }

}
