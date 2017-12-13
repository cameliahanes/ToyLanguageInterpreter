package model;

import exceptions.DivideByZeroException;
import exceptions.UnknownOperatorException;
import exceptions.UnknownVariableException;

public class IfStmt implements IStmt{
	private Exp exp;
	private IStmt thenS;
	private IStmt elseS;
	
	// TODO
	
	public IfStmt(Exp e, IStmt then, IStmt elsee){
		this.exp = e;
		this.thenS = then;
		this.elseS = elsee;
	}
	
	@Override
	public String toString(){
		return "IF(" + exp.toString() + ") THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + ")";
	}
	
	@Override
	public PrgState execute(PrgState state) throws UnknownOperatorException, UnknownVariableException, DivideByZeroException{
		if ( exp.eval(state.getSymTable(), state.getHeap()) == 0 ){
			state.getExeStack().push(elseS);
		} else 
			state.getExeStack().push(thenS);
		return null;
	}
}
