package BirmiGangster;

import java.util.Stack;
import reversi.*;

/**
 * GreedyPlayer implementiert das Interface für einen {@link reversi.ReversiPlayer}. Er spielt immer
 * den unmittelbar besten Zug ohne Rücksicht auf spätere Züge.
 *
 * @author Andrea-Pascal Willi
 * @see reversi.ReversiPlayer
 */
public class GreedyPlayer implements ReversiPlayer {

    int myColor;
    
    @Override
    public void initialize(int myColor, long timeLimit) {
        this.myColor = myColor;
    }

    @Override
    public Coordinates nextMove(GameBoard gb) {
        Stack<Coordinates> possibleMoves = BotUtils.possibleMoves(gb, myColor);

        int nPlayerStones = gb.countStones(myColor);
        Coordinates bestMove = null;
        
        for(Coordinates i : possibleMoves) {
            GameBoard testGb = gb.clone();
            testGb.checkMove(myColor, i);
            testGb.makeMove(myColor, i);
            if (testGb.countStones(myColor) > nPlayerStones) {
                nPlayerStones = testGb.countStones(myColor);
                bestMove = i;
            }   
        }
        return bestMove;
    }  
}