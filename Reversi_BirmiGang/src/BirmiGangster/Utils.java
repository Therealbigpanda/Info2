package BirmiGangster;

import java.util.Stack;
import reversi.Coordinates;
import reversi.GameBoard;

/**
 * General Methods for all Players
 *
 * @author Andrea-Pascal Willi
 */
public class Utils {
    
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
}
