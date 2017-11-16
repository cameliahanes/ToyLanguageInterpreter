package model;

public class ConstExp extends Exp {
	private int number;
	
	public ConstExp(int number){
		this.number = number;
	}
	
	@Override
	public int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer> heap){
		return number;
	}
	
	@Override
	public String toString(){
		return String.valueOf(number);
	}
}
