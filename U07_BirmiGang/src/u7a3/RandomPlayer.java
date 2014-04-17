package u7a3;

import java.util.Stack;
import reversi.*;
import java.util.Random;

/**
 * RandomPlayer implementiert auf einfache Art das Interface für einen
 * Reversi-Spieler ({@link reversi.ReversiPlayer}), der unter allen möglichen Zügen 
 * einen zufälligen wählt.
 * 
 * @see reversi.ReversiPlayer
 */
public class RandomPlayer implements ReversiPlayer {

    private int myColor;
    private long timeLimit;
    
    @Override
    public void initialize(int myColor, long timeLimit) {
        this.myColor = myColor;
        this.timeLimit = timeLimit;
    }

    @Override
    public Coordinates nextMove(GameBoard gb) {
        Coordinates nextMove = null;
        
        Stack possibleMoves = possibleMoves(gb);
        Random randomInt = new Random();
        if (possibleMoves.size() > 0) {
            int b = randomInt.nextInt(possibleMoves.size());
            for (int i = 0; i <= b; ++i) {
                nextMove = (Coordinates)possibleMoves.pop();
            }
            return nextMove;
        } else {
            return null;
        }
            
    }   

    private Stack possibleMoves(GameBoard gb) {
        Stack possibleMoves = new Stack();
        Coordinates coords;
        
        for(int x = 1; x <= gb.getSize(); ++x) {
            for(int y = 1; y <= gb.getSize(); ++y) {
                coords = new Coordinates(x, y);
                if(gb.checkMove(myColor, coords)) possibleMoves.push(coords);
            }
        }   
        return possibleMoves; 
    }
}