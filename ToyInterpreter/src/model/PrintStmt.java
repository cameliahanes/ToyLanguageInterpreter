package model;

import exceptions.DivideByZeroException;
import exceptions.UnknownOperatorException;
import exceptions.UnknownVariableException;

public class PrintStmt implements IStmt {
	private Exp exp;
	
	public PrintStmt(Exp e){
		this.exp = e;
	}
	
	@Override
	public String toString(){
		return "print(" + exp.toString() + ")";
	}
	
	@Override
	public PrgState execute(PrgState state) throws UnknownOperatorException, UnknownVariableException, DivideByZeroException{
		MyIList<Integer> out = state.getOut();
		out.add(exp.eval(state.getSymTable(), state.getHeap()));
		return state;
	}
}
