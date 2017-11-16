package model;

public abstract class Command {
	private String key, description;
	
	public Command(String key, String desc){
		this.key = key;
		this.description = desc;
	}
	
	public String getKey() {
		return this.key;
	}

	public String getDescription() {
		return this.description;
	}

	public abstract void execute();
}
