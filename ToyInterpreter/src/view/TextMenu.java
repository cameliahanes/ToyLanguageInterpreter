package view;

import java.util.Scanner;

import model.Command;
import model.MyIDictionary;

public class TextMenu {
	private MyIDictionary<String, Command> commands;
	public TextMenu(MyIDictionary<String, Command> cmds){
		this.commands = cmds;
	}
	
	public void addCommand(Command c){
		this.commands.put(c.getKey(), c);
	}
	
	public void printMenu(){
		System.out.println("Available commands: ");
		for ( Command cm : this.commands.values() ){
			String newLine = String.format("Command %4s: %s", cm.getKey(), cm.getDescription());
			System.out.println(newLine);
		}
	}
	
	public void show(){
		Scanner scanner = new Scanner(System.in);
		while ( true ){
			printMenu();
			System.out.println("Enter the command: ");
			Command cmd = commands.get(scanner.nextLine());
			if ( cmd == null ){
				System.out.println("Ivalid command! \n");
				continue;
			}
			cmd.execute();
		}
	}
}
