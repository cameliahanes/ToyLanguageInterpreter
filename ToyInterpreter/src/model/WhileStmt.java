package model;

import exceptions.*;

import java.io.IOException;

public class WhileStmt implements IStmt {

    private Exp exp;
    private IStmt stmt;

    public WhileStmt(Exp e, IStmt s){
        this.stmt = s;
        this.exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws UnknownOperatorException, UnknownVariableException, DivideByZeroException, FileNotFoundException, FileAlreadyOpenedException, FileNotOpenedException, IOException, ComparisonException {
        MyIStack<IStmt> exeStack = state.getExeStack();
        if (exp.eval(state.getSymTable(), state.getHeap()) != 0){
            exeStack.push(this);
            exeStack.push(stmt);
        }
        return null;
    }

    @Override
    public String toString(){
        return "while(" + this.exp.toString() + ") do " + this.stmt.toString() + " end";
    }
}
