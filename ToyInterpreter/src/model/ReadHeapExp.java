package model;

import exceptions.DivideByZeroException;
import exceptions.UnknownOperatorException;
import exceptions.UnknownVariableException;

public class ReadHeapExp extends Exp{

	String id;
	
	public ReadHeapExp(String id){
		this.id = id;
	}
	
	@Override
	public int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer> heap)
			throws UnknownOperatorException, UnknownVariableException, DivideByZeroException {

		/// get the value from symbol table
		Integer var_val = tbl.get(this.id);
		
		/// if not found in sym table
		if ( var_val == null ){
			throw new UnknownVariableException("Unknown variable exception \nError at ReadHeapExp:" + toString());
		}
		
		
		/// get heap value stored at the address obtained from sym table
		Integer heap_val = heap.readAddr(var_val);
		
		/// no address error
		if ( heap_val == null )
			throw new NullPointerException("There is no such memory address \nError ar ReadHeapExp:"+ toString());
		
		/// return the value from that address
		return heap_val;
	}

	@Override
	public String toString() {
		return "ReadHeapExp(" + id + ")";
	}

}
