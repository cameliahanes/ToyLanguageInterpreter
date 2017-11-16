package model;

public class ExitCommand extends Command {

	public ExitCommand(String key, String description){
		super(key, description);
	}
	
	@Override
	public void execute() {
		System.out.println("The program has finished.");
		System.exit(0);
	}

}
