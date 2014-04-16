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
        Stack possible = possibleCoords(gb, MY_COLOR);
        
        Random randomGenerator = new Random();
        while (!possible.empty()){
            returnValue = (Coordinates)possible.pop();
            if(randomGenerator.nextInt(5)<4) break;
        }
        return returnValue;
    }
    
    private Stack possibleCoords(GameBoard gb, int playerColor){
         Stack returnStack = new Stack();
         for(int x = 0; x < gb.getSize(); x++){
            for(int y = 0; y < gb.getSize(); y++){
                Coordinates coords = new Coordinates(x,y);
                if(gb.checkMove(playerColor, coords)) returnStack.push(coords);
            }
        }
        return returnStack;
     }
}
