package model;

import java.util.List;

public class MyList<T> implements MyIList<T> {

	private List<T> list;
	public MyList(List<T> list){
		this.list = list;
	}
	
	@Override
	public void add( T elem ){
		this.list.add(elem);
	}
	
	@Override
	public boolean remove(T elem){
		return this.list.remove(elem);
	}
	
	@Override
	public T remove(int index){
		return this.list.remove(index);
	}
	
	@Override
	public int size(){
		return this.list.size();
	}
	
	@Override
	public String toString(){
		String res = "";
		boolean ok = false;
		for ( T elem : this.list ){
			if ( ok )
				res += "\n";
			res += elem.toString();
			ok = true;
		}
		return res;
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return this.list.get(index);
	}
	
}
