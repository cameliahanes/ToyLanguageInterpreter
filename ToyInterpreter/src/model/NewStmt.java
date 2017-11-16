package model;

import java.io.IOException;

import exceptions.DivideByZeroException;
import exceptions.FileAlreadyOpenedException;
import exceptions.FileNotFoundException;
import exceptions.FileNotOpenedException;
import exceptions.UnknownOperatorException;
import exceptions.UnknownVariableException;

public class NewStmt implements IStmt {

	private String var;
	private Exp exp;
	public NewStmt(String var, Exp exp){
		this.exp = exp;
		this.var = var;
	}
	
	@Override
	public PrgState execute(PrgState state)
			throws UnknownOperatorException, UnknownVariableException, DivideByZeroException, FileNotFoundException,
			FileAlreadyOpenedException, FileNotOpenedException, IOException {
		
		/// evaluate current expression to a value
		int res = this.exp.eval(state.getSymTable(), state.getHeap());
		int location = state.getHeap().allocate(res);
		state.getSymTable().put(var,  location);
		return state;
	}

	@Override
	public String toString(){
		return "NewStmt(" + this.var + "," + this.exp.toString() + ")";
	}
	
}

