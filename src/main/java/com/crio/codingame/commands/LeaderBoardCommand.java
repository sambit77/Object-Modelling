package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.entities.ScoreOrder;
import com.crio.codingame.entities.User;
import com.crio.codingame.services.IUserService;

public class LeaderBoardCommand implements ICommand{

    private final IUserService userService;
    
    public LeaderBoardCommand(IUserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute getAllUserScoreOrderWise method of IUserService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["LEADERBOARD","ASC"]
    // or
    // ["LEADERBOARD","DESC"]

    @Override
    public void execute(List<String> tokens) {
        String order = tokens.get(1);
        ScoreOrder scoreOrder = null;
        if(order.equalsIgnoreCase("ASC"))
        {
            scoreOrder = ScoreOrder.ASC;
        }
        else{
            scoreOrder = ScoreOrder.DESC;
        }

        List<User> userlist = userService.getAllUserScoreOrderWise(scoreOrder);
        System.out.print("[");
        int i = 0;
        for(User user: userlist)
        {   
            i++;
            System.out.print(user.toString());
            if(i != userlist.size())
            {
                System.out.print(", ");
            }

        }
        System.out.print("]");
    }
    
}
