/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BirmiGangster;
import java.util.Stack;
import reversi.Coordinates;
import reversi.GameBoard;
import reversi.ReversiPlayer;
/**
 *
 * @author andreatuccillo
 */
class Move {
    public Coordinates coords;
    public int difference;
    Move(){
    }
}
public class BirmiGangster implements ReversiPlayer{
    private long TIME_LIMIT;
    private int MY_COLOR;
    private int OPONENTS_COLOR;
    private int depth;
    @Override
    public void initialize(int i, long l) {
        TIME_LIMIT = l;
        MY_COLOR = i;
        if (MY_COLOR == GameBoard.GREEN) OPONENTS_COLOR = GameBoard.RED;
        else OPONENTS_COLOR = GameBoard.GREEN;
        
    }

    @Override
    public Coordinates nextMove(GameBoard gb) {
        Coordinates returnValue = bestMove(gb, TIME_LIMIT, MY_COLOR).coords;
        return returnValue;
    }
    
    private Move bestMove(GameBoard gb, long timeFraction, int playerColor){
        //System.out.println(depth);
        depth++;
        int oponentColor;
        if (playerColor == GameBoard.GREEN) oponentColor = GameBoard.RED;
        else oponentColor = GameBoard.GREEN;
        
        Move bestMove = new Move();
        
        //start returning before running out of time
        if(timeFraction < 1) {
            depth --;
            return null;
        }
        
        if (playerColor == MY_COLOR) bestMove.difference = -65;
        else bestMove.difference = 65;
        
        Stack possibleCoords = possibleCoords(gb, playerColor);
        int numberOfMoves = possibleCoords.size();
        
        while (!possibleCoords.empty()){
            Coordinates coords = (Coordinates)possibleCoords.pop();
            GameBoard cloneGB = gb.clone();
            if(cloneGB.checkMove(playerColor, coords)) cloneGB.makeMove(playerColor, coords);
            else continue;
            Move moveResult = bestMove(cloneGB, timeFraction/(numberOfMoves),oponentColor);
            
            
            if(moveResult == null){
                    moveResult = bestMove(cloneGB, timeFraction/(numberOfMoves),playerColor);
                    if(moveResult == null){
                        moveResult = new Move();
                        moveResult.difference = cloneGB.countStones(MY_COLOR)-cloneGB.countStones(OPONENTS_COLOR);
                    }
             }
            
            if (playerColor == MY_COLOR){
                if(moveResult.difference >= bestMove.difference) {
                    bestMove.difference = moveResult.difference;
                    bestMove.coords = coords;
                }
             }
                        
            else {
                if(moveResult.difference <= bestMove.difference) {
                    bestMove.difference = moveResult.difference;
                    bestMove.coords = coords;
                }
            }
        }
       
        depth --;
        return bestMove;
    }
    
     private Stack possibleCoords(GameBoard gb, int playerColor){
         Stack returnStack = new Stack();
         for(int x = 1; x <= gb.getSize(); x++){
            for(int y = 1; y <= gb.getSize(); y++){
                Coordinates coords = new Coordinates(x,y);
                if(gb.checkMove(playerColor, coords)) returnStack.push(coords);
            }
        }
        return returnStack;
     }
        
     
}
