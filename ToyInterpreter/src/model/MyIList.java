package model;

public interface MyIList<T> {
	void add(T elem);
	T get(int index);
	boolean remove(T elem);
	T remove(int index);
	int size();
}
