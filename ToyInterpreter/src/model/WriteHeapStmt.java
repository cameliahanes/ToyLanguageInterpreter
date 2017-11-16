package model;

import java.io.IOException;

import exceptions.DivideByZeroException;
import exceptions.FileAlreadyOpenedException;
import exceptions.FileNotFoundException;
import exceptions.FileNotOpenedException;
import exceptions.UnknownOperatorException;
import exceptions.UnknownVariableException;

public class WriteHeapStmt implements IStmt {

	String id;
	Exp exp;
	
	public WriteHeapStmt(String id, Exp exp){
		this.id = id;
		this.exp = exp;
	}
	
	@Override
	public PrgState execute(PrgState state)
			throws UnknownOperatorException, UnknownVariableException, DivideByZeroException, FileNotFoundException,
			FileAlreadyOpenedException, FileNotOpenedException, IOException {
		/// get value from sym table
		Integer var_val = state.getSymTable().get(id);
		
		/// if no value was found
		if ( var_val == null )
			throw new UnknownVariableException("Unknown variable exception \nError at:" + toString());
		
		/// evaluate the expression to a value
		int val = this.exp.eval(state.getSymTable(), state.getHeap());
		
		/// store the new vale at the address
		state.getHeap().writeAddr(var_val,  val);
		
		return state;
	}

	@Override
	public String toString(){
		return "WriteHeap(" + this.id + "," + this.exp.toString();
	}
	
}
