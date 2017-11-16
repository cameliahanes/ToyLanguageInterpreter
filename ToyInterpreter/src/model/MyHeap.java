package model;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<T> implements MyIHeap<T> {
	
	int memory;
	Map<Integer, T> values;
	
	public MyHeap(Map<Integer, T> values){
		this.values = values;
	}
	
	@Override
	public int allocate(T value) {
		++ this.memory;
		this.values.put(this.memory, value);
		return memory;
	}

	@Override
	public T readAddr(int addr) {
		/// get the value at addr address
		return this.values.get(addr);
	}

	@Override
	public void writeAddr(int addr, T value) {
		/// put the value of T type to addr address
		this.values.put(addr, value);
	}

	@Override
	public T deallocate(int addr) {
		/// delete from addr 
		return this.values.remove(addr);
	}

	@Override
	public Map<Integer, T> getMap() {
		/// return the map of type address->value
		return this.values;
	}

	@Override
	public void setMap(Map<Integer, T> map) {
		this.values = map;
	}
	
	@Override
	public String toString(){
		String ret = "";
		boolean ok = false;
		for ( HashMap.Entry<Integer, T> entry : this.values.entrySet()){
			if (ok)
				ret += "\n";
			ret += entry.getKey().toString() + "->" + entry.getValue().toString();
			ok = true;
		}
		return ret;
	}
}
