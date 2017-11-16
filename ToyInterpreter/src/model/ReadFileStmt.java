package model;

import java.io.BufferedReader;
import java.io.IOException;

import exceptions.DivideByZeroException;
import exceptions.FileAlreadyOpenedException;
import exceptions.FileNotFoundException;
import exceptions.FileNotOpenedException;
import exceptions.UnknownOperatorException;
import exceptions.UnknownVariableException;

public class ReadFileStmt implements IStmt{
	private Exp exp_file_id;
	private String var_name;
	
	public ReadFileStmt(Exp id, String var_name){
		this.var_name = var_name;
		this.exp_file_id = id;
	}
	
	@Override
	public PrgState execute(PrgState state) throws UnknownOperatorException, UnknownVariableException,
			DivideByZeroException, FileNotFoundException, FileAlreadyOpenedException, FileNotOpenedException, IOException {
		
		/// evaluate exp file to a value
		int fd = this.exp_file_id.eval(state.getSymTable(), state.getHeap());
		/// get the buffered reader object associated in the file table
		/// if there is not any entry associated to this value in the
		/// file table we stop execution with an appropriate error message
		
		Tuple<String, BufferedReader> br = state.getFileTable().get(fd);
		if ( br == null )
			throw new FileNotOpenedException("File not opened exception at: " + this.toString() + "\nNo such file descriptor: " + String.valueOf(fd));
		
		/// read line from the file
		String line = br.getSecond().readLine();
		int value = 0;
		if ( line != null )
			value = Integer.valueOf(line);
		/// add a new mapping (var_name int value computed) 
		/// into symbol table
		state.getSymTable().put(this.var_name, value);
		return state;
	}

	
	@Override
	public String toString(){
		return "readFileStmt(" + this.exp_file_id.toString() + "," + this.var_name + ")";
	}
}
