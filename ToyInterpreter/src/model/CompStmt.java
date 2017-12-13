package model;

public class CompStmt implements IStmt{
	private IStmt first;
	private IStmt second;
	
	public CompStmt(IStmt first, IStmt second){
		this.first = first;
		this.second = second;
	}
	
	@Override
	public String toString(){
		return "(" + first.toString() + ";" + second.toString() + ")";
	}

	@Override
	public PrgState execute(PrgState state){
		MyIStack<IStmt> stk = state.getExeStack();
		stk.push(second);
		stk.push(first);
		return null;
	}
}
