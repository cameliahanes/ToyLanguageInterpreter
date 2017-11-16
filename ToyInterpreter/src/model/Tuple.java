package model;

public class Tuple<f, s> {
	private f first;
	private s second;
	public Tuple(f F, s S){
		this.first = F;
		this.second = S;
	}
	
	public f getFirst(){
		return this.first;
	}
	
	public s getSecond(){
		return this.second;
	}
	
	@Override
	public String toString(){
		return this.first.toString();
	}
}
