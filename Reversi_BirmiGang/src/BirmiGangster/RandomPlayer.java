package BirmiGangster;

import java.util.Stack;
import reversi.*;
import java.util.Random;

/**
 * RandomPlayer implementiert auf einfache Art das Interface für einen
 * {@link reversi.ReversiPlayer}, der unter allen möglichen Zügen einen zufälligen wählt.
 *
 * @see reversi.ReversiPlayer
 */
public class RandomPlayer implements ReversiPlayer {

    private int myColor;
    private long timeLimit;
    Random randomInt = new Random();

    @Override
    public void initialize(int myColor, long timeLimit) {
        this.myColor = myColor;
        this.timeLimit = timeLimit;
    }

    @Override
    public Coordinates nextMove(GameBoard gb) {
        Stack<Coordinates> possibleMoves = BotUtils.possibleMoves(gb, myColor);
        if (possibleMoves.size() > 0) {
            return possibleMoves.get(randomInt.nextInt(possibleMoves.size()));
        }
        return null;
    }
}