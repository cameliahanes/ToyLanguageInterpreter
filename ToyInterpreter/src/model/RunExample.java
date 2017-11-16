package model;

import java.io.IOException;

import controller.Controller;
import exceptions.DivideByZeroException;
import exceptions.FileAlreadyOpenedException;
import exceptions.FileNotFoundException;
import exceptions.FileNotOpenedException;
import exceptions.UnknownOperatorException;
import exceptions.UnknownVariableException;

public class RunExample extends Command {
	Controller controller;
		
	public RunExample(String key, String description, Controller contrl){
		super(key, description);
		this.controller = contrl;
	}
	
	@Override
	public void execute() {
		try {
			/// System.out.println("here\n\n");
			this.controller.allSteps();
		} catch ( UnknownVariableException e){
			System.out.println(e.getMessage());
			return;
		} catch ( DivideByZeroException e){
			System.out.println(e.getMessage());
			return;
		} catch ( FileAlreadyOpenedException e ){
			System.out.println(e.getLocalizedMessage());
			return;
		} catch ( FileNotOpenedException e ){
			System.out.println(e.getMessage());
			return;
		} catch ( IOException e ){
			System.out.println(e.getMessage());
			return;
		} catch ( NullPointerException n){
			System.out.println(n.getMessage());
			return;
		} catch (UnknownOperatorException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());;
		}
		
	}

}
