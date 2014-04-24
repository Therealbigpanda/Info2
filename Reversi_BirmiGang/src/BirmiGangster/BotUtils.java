package BirmiGangster;

import java.util.Stack;
import reversi.Coordinates;
import reversi.GameBoard;

/**
 * General Methods for all Players
 *
 * @author Andrea-Pascal Willi
 */
public class BotUtils {
    
    /**
     *
     * @param gb for which moves are evaluated
     * @param color for which moves are evaluated
     * @return A Stack with all possible Moves
     */
    public static Stack<Coordinates> possibleMoves(GameBoard gb, int color) {
        Stack returnStack = new Stack();
        for (int x = 1; x <= gb.getSize(); x++) {
            for (int y = 1; y <= gb.getSize(); y++) {
                Coordinates coords = new Coordinates(x, y);
                if (gb.checkMove(color, coords)) {
                    returnStack.push(coords);
                }
            }
        }
        return returnStack;
    }
    
    /**
     * 
     * @param oldTime time since when the bot is thinking
     * @param timeLimit amount of time the bot can think
     * @param timeoutOffset offset before timeout
     * @return true if only <code>timeoutOffset</coe> (ms) is left
     */
    private boolean checkTimeout(int oldTime, int timeLimit, int timeoutOffset) {
        return System.currentTimeMillis() > oldTime + timeLimit - timeoutOffset;
    }
}
