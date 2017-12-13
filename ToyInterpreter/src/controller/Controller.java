package controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import exceptions.*;
import model.IStmt;
import model.PrgState;
import repository.IRepository;

public class Controller {
    IRepository repo;
    private ExecutorService executor;

    public Controller(IRepository r){
        this.repo = r;
    }


    Map<Integer, Integer> conservativeGarbageCollector(Collection <Integer> symTableValues, Map<Integer, Integer> heap){
        return heap.entrySet()
                .stream()
                .filter(e->symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public PrgState oneStep(PrgState state) throws UnknownVariableException, DivideByZeroException, FileAlreadyOpenedException, FileNotOpenedException, IOException, UnknownOperatorException, FileNotFoundException, ComparisonException {
        IStmt currentState = state.getExeStack().pop();
        return currentState.execute(state);
    }

    public void allSteps2() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
        while(prgList.size() > 0){
            oneStepForAllPrg(prgList);
            /// remove completed programs
            prgList = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        /// update repo state
        repo.setPrgList(prgList);
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream().filter(p->p.isNotCompleted()).collect(Collectors.toList());
    }

    void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        prgList.forEach(prg-> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        List<Callable<PrgState>> callList = prgList.stream().
                map((PrgState p)->(Callable<PrgState>)(()->{
                    try {
                        return p.oneStep();
                    } catch (ComparisonException e) {
                        e.printStackTrace();
                    }
                return null;
                })).
                collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList).stream().
                map( future-> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                return null;
                }).filter(p->p!=null).collect(Collectors.toList());

        prgList.addAll(newPrgList);

        /// after the execution print the PrgState List into the log file

        prgList.forEach(prg-> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        /// save the current programs in the repository
        repo.setPrgList(prgList);
    }




}
