package model;

import exceptions.ComparisonException;
import exceptions.DivideByZeroException;
import exceptions.UnknownOperatorException;
import exceptions.UnknownVariableException;

public class BooleanExp extends Exp {

    private Exp exp1;
    private Exp exp2;
    private String op;

    public BooleanExp(Exp e1, Exp e2, String coper){
        this.exp1 = e1;
        this.exp2 = e2;
        this.op = coper;
    }

    public Exp getExp1(){
        return exp1;
    }

    public Exp getExp2(){
        return exp2;
    }

    public String getOperator(){
        return op;
    }

    public void setExp2(Exp e2){
        this.exp2 = e2;
    }

    public void setExp1(Exp e1){
        this.exp1 = e1;
    }

    public void setOperator(String o){
        this.op = o;
    }

    @Override
    public int eval(MyIDictionary<String, Integer> symTable, MyIHeap<Integer> heap) throws UnknownOperatorException, DivideByZeroException, UnknownVariableException, ComparisonException {
        Integer res1 = exp1.eval(symTable, heap);
        Integer res2 = exp2.eval(symTable, heap);
        Boolean res;

        switch (op){
            case "<":
                res = res1 < res2;
                break;
            case "<=":
                res = res1 <= res2;
                break;
            case ">":
                res = res1 > res2;
                break;
            case ">=":
                res = res1 >= res2;
                break;
            case "==":
                res = res1 == res2;
                break;
            case "!=":
                res = res1 != res2;
            default:
                throw new ComparisonException(op + " is not a valid comparison operator! \n");
        }

        return res ? 1 : 0;
    }

    @Override
    public String toString(){
        return exp1.toString() + " " + op + " " + exp2.toString();
    }

}
