package model;
import java.io.BufferedReader;

public class PrgState {
	private MyIStack<IStmt> exeStack;
	private MyIDictionary<String, Integer> symTable;
	private MyIList<Integer> out;
	private IStmt originalProgram;
	
	/// file table as a dictionary of
	/// tuples as values (mapping from a int (file descriptor) to a buffered reader for the file)
	private MyIDictionary<Integer, Tuple<String, BufferedReader>> fileTable;
	private MyIHeap<Integer> heap;
	
	public PrgState(MyIStack<IStmt> exeStack, MyIDictionary<String, Integer> symTable, MyIList<Integer> out, IStmt program, MyIDictionary<Integer, Tuple<String, BufferedReader>> fileTable, MyIHeap<Integer> heap){
		this.exeStack = exeStack;
		this.symTable = symTable;
		this.out = out;
		this.originalProgram = program;
		this.exeStack.push(this.originalProgram);
		this.fileTable = fileTable;
		this.heap = heap;
	}
	
	public MyIHeap<Integer> getHeap(){
		return heap;
	}
	
	public MyIDictionary<String, Integer> getSymTable() {
		return symTable;
	}

	public MyIList<Integer> getOut() {
		return out;
	}

	public MyIStack<IStmt> getExeStack() {
		return exeStack;
	}

	@Override
	public String toString(){
		String res = "";
		res += exeStack.toString();
		res += symTable.toString();
		res += out.toString();
		res += fileTable.toString();
		res += heap.toString();
		return res;
	}

	public MyIDictionary<Integer, Tuple<String, BufferedReader>> getFileTable() {
		return fileTable;
	}	
}
