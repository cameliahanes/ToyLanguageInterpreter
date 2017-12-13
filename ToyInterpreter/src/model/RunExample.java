package model;

import java.io.IOException;
import controller.Controller;
import exceptions.*;

public class RunExample extends Command {
    Controller controller;

    public RunExample(String key, String description, Controller contrl){
        super(key, description);
        this.controller = contrl;
    }

    @Override
    public void execute() {
        try {
            this.controller.allSteps2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
