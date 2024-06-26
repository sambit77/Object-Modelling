package com.crio.codingame.commands;

import java.util.List;
import javax.lang.model.util.ElementScanner6;
import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;
import com.crio.codingame.services.IContestService;

public class CreateContestCommand implements ICommand{

    private final IContestService contestService;

    public CreateContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute create method of IContestService and print the result.
    // Also Handle Exceptions and print the error messsages if any.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_CONTEST","CRIODO2_CONTEST","LOW Monica","40"]
    // or
    // ["CREATE_CONTEST","CRIODO1_CONTEST","HIGH","Ross"]
    // Hint - Use Parameterized Exceptions in the Service class to match with the Unit Tests Output.

    @Override
    public void execute(List<String> tokens) {
        String lev = tokens.get(2);
        Level level = null;
        if(lev.equalsIgnoreCase("high"))
        {
            level = Level.HIGH;
        }
        else if(lev.equalsIgnoreCase("low"))
        {
            level = Level.LOW;
        }
        else
        {
            level = Level.MEDIUM;
        }

        Integer num = null;
        if(tokens.size() <=4)
        {
            num = null;
        }
        else
        {
            num =  Integer.parseInt(tokens.get(4));
        }
       
        Contest contest = null;
        try{
        contest = contestService.create(tokens.get(1), level , tokens.get(3),num);
        System.out.println(contest.toString());

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        


    }
    
}
