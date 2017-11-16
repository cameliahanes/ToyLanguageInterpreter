package model;

import java.io.IOException;

import exceptions.DivideByZeroException;
import exceptions.FileAlreadyOpenedException;
import exceptions.FileNotFoundException;
import exceptions.FileNotOpenedException;
import exceptions.UnknownOperatorException;
import exceptions.UnknownVariableException;

public interface IStmt {
	PrgState execute(PrgState state) throws UnknownOperatorException, UnknownVariableException, DivideByZeroException, FileNotFoundException, FileAlreadyOpenedException, FileNotOpenedException, IOException;
	@Override
	String toString();
}
