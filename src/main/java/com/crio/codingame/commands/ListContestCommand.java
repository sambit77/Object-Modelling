package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;
import com.crio.codingame.services.IContestService;

public class ListContestCommand implements ICommand{

    private final IContestService contestService;
    
    public ListContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute getAllContestLevelWise method of IContestService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["LIST_CONTEST","HIGH"]
    // or
    // ["LIST_CONTEST"]

    @Override
    public void execute(List<String> tokens) {
        String lev = "";

        if(tokens.size() == 2)
        {
            lev = tokens.get(1);
        }

        Level level = null;

        if(lev.equalsIgnoreCase("high"))
        {
            level = Level.HIGH;
        }
        else if(lev.equalsIgnoreCase("low"))
        {
            level = Level.LOW;
        }
        else if(lev.equalsIgnoreCase("medium"))
        {
            level = Level.MEDIUM;
        }
        else
        {
            level = null;
        }

        List<Contest> contests = contestService.getAllContestLevelWise(level);
        
        System.out.print("[");
        int i = 0;
        for(Contest c : contests)
        {
            i++;
            System.out.print(c.toString());
            if(i != contests.size())
            {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }
    
}
