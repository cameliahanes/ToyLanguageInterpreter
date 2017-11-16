package controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import exceptions.DivideByZeroException;
import exceptions.FileAlreadyOpenedException;
import exceptions.FileNotFoundException;
import exceptions.FileNotOpenedException;
import exceptions.UnknownOperatorException;
import exceptions.UnknownVariableException;
import model.IStmt;
import model.PrgState;
import repository.IRepository;

public class Controller {
	IRepository repo;
	public Controller(IRepository r){
		this.repo = r;
	}
	
	Map<Integer, Integer> conservativeGarbageCollector(Collection <Integer> symTableValues, Map<Integer, Integer> heap){
		return heap.entrySet()
				.stream()
				.filter(e->symTableValues.contains(e.getKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
	
	public PrgState oneStep(PrgState state) throws UnknownVariableException, DivideByZeroException, FileAlreadyOpenedException, FileNotOpenedException, IOException, UnknownOperatorException, FileNotFoundException{
		IStmt currentState = state.getExeStack().pop();
		return currentState.execute(state);
	}
	
	public void allSteps() throws UnknownVariableException, DivideByZeroException, FileAlreadyOpenedException, FileNotOpenedException, IOException, UnknownOperatorException, FileNotFoundException {
		PrgState current = repo.getCrtState();
		
		while ( ! current.getExeStack().isEmpty() ){
			
			System.out.println(current.toString());
			oneStep(current);
			
			current.getHeap().setMap(this.conservativeGarbageCollector(
					current.getSymTable().values(),
					current.getHeap().getMap()));
			try{
			repo.logPrgStateExec();
			} catch ( IOException e ){
				System.out.println("Cannot log program state to file. Program exiting...");
				return;
			}
		}
	}
	
}
