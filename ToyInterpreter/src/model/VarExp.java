package model;

import exceptions.UnknownVariableException;

public class VarExp extends Exp{
	private String id;
	
	public VarExp(String id){
		this.id = id;
	}
	
	@Override
	public int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer> heap) throws UnknownVariableException{
		Integer x = tbl.get(id);
		if ( x == null )
			throw new UnknownVariableException("Unknown variable exception at " + this.toString() + "\n There is no such " + this.id + " variable \n");
		return tbl.get(id);
	}
	
	@Override
	public String toString(){
		String res = id;
		return res;
	}
}
