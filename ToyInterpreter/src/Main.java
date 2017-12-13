import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import controller.Controller;
import model.*;
import repository.IRepository;
import repository.SingleProgramRepository;
import view.TextMenu;

public class Main {

    public static Controller getNewController(IStmt prg){
        MyIStack<IStmt> exeStack = new MyStack<>(new Stack<IStmt>());
        MyIDictionary<String, Integer> symTable = new MyDictionary<>(new HashMap<String, Integer>());
        MyIList<Integer> out = new MyList<>(new ArrayList<Integer>());
        MyIHeap<Integer> heap = new MyHeap<Integer>(new HashMap<Integer, Integer>());
        MyIDictionary<Integer, Tuple<String, BufferedReader>> fileTable = new MyDictionary<>(new HashMap<Integer, Tuple<String, BufferedReader>>());
        PrgState programState = new PrgState(exeStack, symTable, out, prg, fileTable, heap, 1);
        IRepository repository = new SingleProgramRepository(programState, "log.txt");
        Controller controller = new Controller(repository);
        return controller;
    }

    public static void main(String[] args){
        /*
         * lab2ex1
         * v=2;
         * print( v )
         *
         * */

        IStmt lab2ex1 = new CompStmt(new AssignStmt("v", new ConstExp(2)), new PrintStmt(new VarExp("v")));

        /*
         * lab2ex2
         * a = 2 + 3 * 5;
         * b = a + 1;
         * print(b)
         *
         * */

        IStmt lab2ex2 = new CompStmt(new AssignStmt("a", new ArithExp('+', new ConstExp(2), new ArithExp('*', new ConstExp(3), new ConstExp(5)))), new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ConstExp(1))), new PrintStmt(new VarExp("b"))));

        /*
         * lab2ex3
         * a = 2 - 2;
         * if a then v = 2 else v = 3
         * print(v)
         *
         * */

        IStmt lab2ex3 = new CompStmt(new AssignStmt("a", new ArithExp('-', new ConstExp(2), new ConstExp(2))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ConstExp(2)), new AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));

        /*
         * lab3ex1
         * openRFile(var_f, "test.in");
         * readFile(var_f, var_c); print(var_c);
         * if var_c then readFile(var_f, var_c); print(var_c)
         * else print(0)
         * closeRFile(var_f)
         *
         * */

        IStmt lab3ex1 = new CompStmt(new OpenRFileStmt("var_f", "test1.in"),
                new CompStmt(
                        new ReadFileStmt(new VarExp("var_f"), "var_c"),
                        new CompStmt(
                                new PrintStmt(new VarExp("var_c")),
                                new CompStmt(
                                        new IfStmt(
                                                new VarExp("var_c"),
                                                new CompStmt(
                                                        new ReadFileStmt(new VarExp("var_f"), "var_c"),
                                                        new PrintStmt(new VarExp("var_c"))
                                                ),
                                                new PrintStmt(new ConstExp(0))
                                        ),
                                        new CloseRFileStmt(new VarExp("var_f"))
                                )
                        )
                )
        );

        /**
         * lab3ex2
         * openRFile(var_f + 2, var_c); print(var_c);
         * (if var_c then readFile(var_f, var_c); print(var_c)
         * else print(0));
         * closeRFile(var_f)
         *
         */

        IStmt lab3ex2 = new CompStmt(new OpenRFileStmt("var_f", "test.in"),
                new CompStmt(
                        new ReadFileStmt(new ArithExp('+', new VarExp("var_f"), new ConstExp(2)), "var_c"),
                        new CompStmt(
                                new PrintStmt(new VarExp("var_c")),
                                new CompStmt(
                                        new IfStmt(
                                                new VarExp("var_c"),
                                                new CompStmt(
                                                        new ReadFileStmt(new VarExp("var_f"), "var_c"),
                                                        new PrintStmt(new VarExp("var_c"))
                                                ),
                                                new PrintStmt(new ConstExp(0))
                                        ),
                                        new CloseRFileStmt(new VarExp("var_f"))
                                )
                        )
                )
        );


        /*
         * v = 10; new(v, 20); new(a, 22); wH(a, 30); print(a); print(rH(a)); a = 0;
         *
         * */

        IStmt lab4ex1 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
                new CompStmt(
                        new NewStmt("v", new ConstExp(20)),
                        new CompStmt(
                                new NewStmt("a", new ConstExp(22)),
                                new CompStmt(
                                        new WriteHeapStmt("a", new ConstExp(30)),
                                        new CompStmt(
                                                new PrintStmt(new VarExp("a")),
                                                new CompStmt(
                                                        new PrintStmt(new ReadHeapExp("a")),
                                                        new AssignStmt("a", new ConstExp(0))
                                                )
                                        )
                                )
                        )
                )
        );


        /**
         *
         * Lab5ex1: 10 + (2 < 6) = 11
         *
         * */


        IStmt lab5ex1 = new CompStmt(new AssignStmt("P", new ArithExp('+', new ConstExp(10),
                new BooleanExp(new ConstExp(2), new ConstExp(6), "<"))),
                new PrintStmt(new VarExp("P")));


        /*
        *
        * Lab5ex2: (10 + 2) < 6
        *
        * **/

        IStmt lab5ex2 = new CompStmt(new AssignStmt("Q", new BooleanExp(new ArithExp('+',
                new ConstExp(10), new ConstExp(2)), new ConstExp(6), "<")),
                new PrintStmt(new VarExp("Q")));


        /*
        *
        * v = 6;
        * (while (v - 4) print(v); v = v - 1)
        * print(v)
        *
        ***/

        AssignStmt as = new AssignStmt("v", new ConstExp(6));
        IStmt auxlab5ex3 = new CompStmt(new WhileStmt(new BooleanExp(new VarExp("v"),
                new ConstExp(4), ">"), new CompStmt(new PrintStmt(new VarExp("v")),
                new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ConstExp(1))))),
                new PrintStmt(new VarExp("v")));

        IStmt lab5ex3 = new CompStmt(as, auxlab5ex3);


        /**
         *
         * la6ex1
         * v = 10; new(a, 22);
         * fork(wH(a, 30); v = 32; print(v); print(rH(a)));
         * print(v); print(rH(a))
         *
         * */

        IStmt lab6ex1 = new CompStmt(
                new CompStmt(
                        new AssignStmt("v", new ConstExp(10)),
                        new NewStmt("a", new ConstExp(22))
                ),
                new CompStmt(
                        new ForkStmt(
                                new CompStmt(
                                        new WriteHeapStmt("a", new ConstExp(30)),
                                        new CompStmt(
                                                new AssignStmt("v", new ConstExp(32)),
                                                new CompStmt(
                                                        new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new ReadHeapExp("a"))
                                                )
                                        )
                                )
                        ),
                        new CompStmt(
                                new PrintStmt(new VarExp("v")),
                                new PrintStmt(new ReadHeapExp("a"))
                        )
                )
        );

        TextMenu menu = new TextMenu(new MyDictionary<String, Command>(new HashMap<String, Command>()));
        menu.addCommand(new ExitCommand("0", "Exit"));
        menu.addCommand(new RunExample("1", lab2ex1.toString(), getNewController(lab2ex1)));
        menu.addCommand(new RunExample("2", lab2ex2.toString(), getNewController(lab2ex2)));
        menu.addCommand(new RunExample("3", lab2ex3.toString(), getNewController(lab2ex3)));
        menu.addCommand(new RunExample("4", lab3ex1.toString(), getNewController(lab3ex1)));
        menu.addCommand(new RunExample("5", lab3ex2.toString(), getNewController(lab3ex2)));
        menu.addCommand(new RunExample("6", lab4ex1.toString(), getNewController(lab4ex1)));
        menu.addCommand(new RunExample("7", lab5ex1.toString(), getNewController(lab5ex1)));
        menu.addCommand(new RunExample("8", lab5ex2.toString(), getNewController(lab5ex2)));
        menu.addCommand(new RunExample("9", lab5ex3.toString(), getNewController(lab5ex3)));
        menu.addCommand(new RunExample("10", lab6ex1.toString(), getNewController(lab6ex1)));
        menu.show();
    }

}
