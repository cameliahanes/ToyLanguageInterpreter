package model;

import java.io.BufferedReader;
import java.io.IOException;

import exceptions.DivideByZeroException;
import exceptions.FileAlreadyOpenedException;
import exceptions.FileNotFoundException;
import exceptions.FileNotOpenedException;
import exceptions.UnknownOperatorException;
import exceptions.UnknownVariableException;

public class CloseRFileStmt implements IStmt{

	private Exp exp_file_id;
	public CloseRFileStmt(Exp exp){
		this.exp_file_id = exp;
	}
	
	@Override
	public PrgState execute(PrgState state)
			throws UnknownOperatorException, UnknownVariableException, DivideByZeroException, FileNotFoundException,
			FileAlreadyOpenedException, FileNotOpenedException, IOException {
		
		/// the expression evaluated to a value to obtain file descriptor
		int fd = this.exp_file_id.eval(state.getSymTable(), state.getHeap());
		
		/// get the entry in FileTable and get the associated buffered reader object
		/// if there is not any entry in FileTable for that value we stop the execution with 
		/// an appropriate error message
		Tuple<String, BufferedReader> entry = state.getFileTable().remove(fd);
		if ( entry == null )
			throw new FileNotOpenedException("File not opened exception at:" + this.toString() + "\nThere is no opened file with file id: " + fd );
		
		/// the corresponding buffered reader is found as a second member of our tuple
		entry.getSecond().close();
		return state;
	}
	
	@Override
	public String toString(){
		return "closeRFileStmt(" + this.exp_file_id.toString() + ")";
	}
	
}
