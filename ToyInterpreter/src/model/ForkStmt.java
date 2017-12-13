package model;
import exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.chrono.IsoChronology;
import java.util.Stack;


public class ForkStmt implements IStmt {
    private IStmt stmt;

    public  ForkStmt(IStmt istmt){
        this.stmt = istmt;
    }

    @Override
    public PrgState execute(PrgState state) throws UnknownOperatorException, UnknownVariableException, DivideByZeroException, FileNotFoundException, FileAlreadyOpenedException, FileNotOpenedException, IOException, ComparisonException {
        PrgState forkPrgState = new PrgState(new MyStack<>(new Stack<>()), state.getSymTable().clone(), state.getOut(), this.stmt, state.getFileTable(), state.getHeap(), state.getID() * 10);
        return forkPrgState;
    }
}
