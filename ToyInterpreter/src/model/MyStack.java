package model;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
	private Stack<T> stack;
	public MyStack(Stack<T> stack){
		this.stack = stack;
	}

	@Override
	public void push(T elem){
		this.stack.push(elem);
	}
	
	@Override
	public T pop(){
		return this.stack.pop();
	}
	
	@Override
	public T peek(){
		return this.stack.peek();
	}
	
	@Override
	public boolean isEmpty(){
		return this.stack.isEmpty();
	}
	
	@Override
	public String toString(){
		String res = "";
		MyStack<T> st = new MyStack<T>(new Stack<T>());
		boolean ok = false;
		while ( !this.isEmpty() ){
			if ( ok ) 
				res += "\n";
			res += this.peek().toString();
			st.push(this.pop());
			ok = true;
		}
		
		while ( !st.isEmpty() )
			this.push(st.pop());
		
		return res;
	}
	
}
