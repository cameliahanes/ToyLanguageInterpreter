package model;

import exceptions.DivideByZeroException;
import exceptions.UnknownOperatorException;
import exceptions.UnknownVariableException;

public class ArithExp extends Exp  {
	private Exp e1;
	private Exp e2;
	private int op;
	
	public ArithExp(char op, Exp e1, Exp e2){
		this.e1 = e1;
		this.e2 = e2;
		if ( op == '+' )
			this.op = 0;
		else if ( op == '-' )
			this.op = 1;
		else if ( op == '*' )
			this.op = 2;
		else if ( op == '/' )
			this.op = 3;
	}
	
	@Override
	public int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer> heap) throws UnknownOperatorException, DivideByZeroException, UnknownVariableException   {
		int res1 = this.e1.eval(tbl, heap);
		int res2 = this.e2.eval(tbl, heap);
		if ( op == 0 )
			return (res1 + res2);
		else if ( op == 1 )
			return (res1 - res2);
		else if ( op == 2 )
			return (res1 * res2);
		else if ( op == 3 ){
			if ( res2 == 0 )
				throw new DivideByZeroException("Division by zero exception at " + this.toString());
			return (res1 / res2);
		}
		else
			throw new UnknownOperatorException("Unknown operator exception occured! \n");
 	}
	
	@Override
	public String toString(){
		String res = this.e1.toString();
		if ( op == 0 )
			res += " + ";
		else if ( op == 1 )
			res += " - ";
		else if ( op == 2 )
			res += " * ";
		else if ( op == 3 )
			res += " / ";
		res += this.e2.toString();
		return res;
	}
	
}
