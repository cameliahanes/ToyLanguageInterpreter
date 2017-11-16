package model;

import java.util.Map;

public interface MyIHeap<T> {
	int allocate(T value);
	T readAddr(int addr);
	void writeAddr(int addr, T value);
	T deallocate(int addr);
	
	Map<Integer, T> getMap();
	void setMap(Map<Integer, T> map);
}
