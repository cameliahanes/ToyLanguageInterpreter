package model;

public interface MyIStack<T> {
	void push(T elem);
	T pop();
	T peek();
	boolean isEmpty();
}
