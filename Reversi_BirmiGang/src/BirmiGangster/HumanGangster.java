/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BirmiGangster;

import reversi.*;

/**
 *
 * @author andreatuccillo
 */
class Move{
    
    private Coordinates coordinates;
    private GameBoard gb;
    
    public Move(Coordinates coord, GameBoard gb){
         this.coordinates = coord;
         this.gb = gb;
    }
    
    public void setCoordinates(Coordinates coords){
        this.coordinates = coords;
    }
    public void setGb(GameBoard gb){
        this.gb = gb;
    }
    
    public Coordinates getCoordinates(){
        return this.coordinates;
    }
    public GameBoard getGb(){
        return this.gb;
    }
}

public class HumanGangster implements reversi.ReversiPlayer {

    int myColor;
    long TIME_LIMIT;
    long endOfTurn;
    
    @Override
    public void initialize(int i, long l) {
        myColor = i;
        TIME_LIMIT = l;
    }

    @Override
    public Coordinates nextMove(GameBoard gb) {
        
        Move bestMove = new Move(null,null);
        
        endOfTurn = System.currentTimeMillis() + TIME_LIMIT;
        
        Move[] validMoves = createArrayOfValidMoves(gb);
        
        bestMove = moveEfficiency(validMoves, TIME_LIMIT*8/10);
        
        if(bestMove != null) return bestMove.getCoordinates();
        return null;
    }

    private Move[] createArrayOfValidMoves(GameBoard gb){
        
        
        //counts valid moves
        int numberOfValidMoves = 0;
        for (int row=1; row<=gb.getSize(); row ++){
            for (int column=1; column<=gb.getSize(); column ++){
                
                Coordinates coords = new Coordinates(row, column);
                if(gb.checkMove(myColor, coords)) numberOfValidMoves++;
            }
        }
        
        //creates array
        Move[] validMoves = new Move[numberOfValidMoves];
        
        //fills array
        for (int row=1; row<=gb.getSize(); row ++){
            for (int column=1; column<=gb.getSize(); column ++){
                
                Coordinates coords = new Coordinates(row, column);
                Move move = new Move(coords, gb);
                if(gb.checkMove(myColor, coords)) validMoves[--numberOfValidMoves] = move;
                
            }
        }
        
        return validMoves;
    }
    
    private Move moveEfficiency(Move[] moves, long remainingTime){
        if(moves.length!=0) return moves[0];
        return null;
    }
}
