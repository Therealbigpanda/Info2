/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BirmiGangster;

import java.util.Stack;
import java.util.Random;
import reversi.Coordinates;
import reversi.GameBoard;
import reversi.ReversiPlayer;

/**
 *
 * @author andreatuccillo
 */

public class RageBot implements ReversiPlayer{

    int MY_COLOR; 
    
    @Override
    public void initialize(int i, long l){
        MY_COLOR = i;
    }

    @Override
    public Coordinates nextMove(GameBoard gb) {
        Coordinates returnValue = null;
        Stack possible = Utils.possibleMoves(gb, MY_COLOR);
        Random randomGenerator = new Random();
        while (!possible.empty()){
            int b = randomGenerator.nextInt(possible.size());
            returnValue = (Coordinates)possible.pop();
            if(b==0) break;
        }
        return returnValue;
    }
}
