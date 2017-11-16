package model;

import exceptions.DivideByZeroException;
import exceptions.UnknownOperatorException;
import exceptions.UnknownVariableException;

public class AssignStmt implements IStmt{
	private String id;
	private Exp exp;
	
	// TODO
	
	public AssignStmt(String id, Exp ex){
		this.exp = ex;
		this.id = id;
	}
	
	@Override 
	public String toString(){
		return id + " = " + exp.toString();
	}
	
	@Override
	public PrgState execute(PrgState state) throws UnknownOperatorException, UnknownVariableException, DivideByZeroException{
		MyIDictionary<String, Integer> symTable = state.getSymTable();
		symTable.put(this.id, this.exp.eval(symTable, state.getHeap()));
		return state;
	}
	
	// TODO
}
