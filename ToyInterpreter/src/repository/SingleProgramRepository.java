package repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import exceptions.FileNotFoundException;
import model.PrgState;

public class SingleProgramRepository implements IRepository {
	private String logFile;
	private PrgState state;
	private PrintWriter printWriter;
	private boolean firstTime;
	
	public SingleProgramRepository(PrgState state, String logFile){
		this.state = state;
		this.logFile = logFile;
		this.printWriter = null;
		this.firstTime = true;
	}
	
	@Override
	public PrgState getCrtState(){
		return this.state;
	}
	
	@Override
	public void logPrgStateExec() throws IOException {
		if ( firstTime == true ){
			try{
				PrintWriter writer = new PrintWriter(new File(logFile));
				writer.print("");
				writer.close();
			} catch (Exception e){
				e.printStackTrace();
			}
			firstTime = false;
		}
		
		this.printWriter = new PrintWriter(new FileWriter(logFile, true));
		
		this.printWriter.println("Exe Stack: ");
		this.printWriter.println(this.state.getExeStack().toString());
		this.printWriter.println("Symbol Table: ");
		this.printWriter.println(this.state.getSymTable().toString());
		this.printWriter.println("Out: ");
		this.printWriter.println(this.state.getOut().toString());
		// TODO file table
		
		this.printWriter.println("File Table");
		this.printWriter.println(this.state.getFileTable().toString());
		
		// TODO heap
		this.printWriter.println("Heap");
		this.printWriter.println(this.state.getHeap().toString());
		
		this.printWriter.close();
		
	}
	
	
	
	
}

