package bc;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class BrickFactory implements AppFactory{

	@Override
	public Model makeModel() {
		// TODO Auto-generated method stub
		Brick b=new Brick();
		return b;
	}

	@Override
	public View makeView(String type) {
		// TODO Auto-generated method stub
		
		View view = null;
		if(type=="Side View")
			view=new SideView();
		else if(type=="Top View")
			view=new TopView(); 
		else if(type=="Front View")
			view=new FrontView();
		else if(type=="Dimensional View")
			view=new DimensionalView();
		return view;
	}

	@Override
	public Command makeCommand(String type) {
		// TODO Auto-generated method stub
		Command cmmd = null;
		if(type=="Set Height")
			cmmd=new SetHeight();
		else if(type=="Set Width")
			cmmd=new SetWidth();
		else if(type=="Set Length")
			cmmd=new SetLength();
		return cmmd;
	}

	@Override
	public String[] getViews() {
		// TODO Auto-generated method stub
		return new String[]{"Top View","Front View","Side View","Dimensional View"};
	}

	@Override
	public String[] getCommands() {
		// TODO Auto-generated method stub
		return new String[]{"Set Height", "Set Width", "Set Length","Undo","Redo"};  
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Brick CAD";
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "Command: Set height, Set Width, Set Length \nViews: Side View, Front View, Top View, Dimensional View";
	}

	@Override
	public String about() {
		// TODO Auto-generated method stub
		return "BrickCAD v1.0";
	}

}
