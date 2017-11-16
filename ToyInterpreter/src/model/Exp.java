package model;

import exceptions.DivideByZeroException;
import exceptions.UnknownOperatorException;
import exceptions.UnknownVariableException;

abstract class Exp  {
	abstract public int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer> heap) throws UnknownOperatorException, UnknownVariableException, DivideByZeroException;
	
	@Override
	public abstract String toString();

}
