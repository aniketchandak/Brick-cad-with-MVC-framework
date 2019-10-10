package mvc;

public interface AppFactory {
	public abstract Model makeModel();
	public abstract View makeView(String type);
	public abstract Command makeCommand(String type);
	public abstract String[] getViews();
	public abstract String[] getCommands();
	public abstract String getTitle();
	public abstract String getHelp();
	public abstract String about();
}
