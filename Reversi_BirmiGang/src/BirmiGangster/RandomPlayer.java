package BirmiGangster;

import java.util.Stack;
import reversi.*;
import java.util.Random;

/**
 * HumanPlayer implementiert auf einfache Art das Interface für einen
 * Reversi-Spieler ({@link reversi.ReversiPlayer}), der unter allen möglichen Zügen 
 * einen zufälligen wählt.
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
        Stack<Coordinates> possibleMoves = possibleMoves(gb);
        if (possibleMoves.size() > 0) {
            return possibleMoves.get(randomInt.nextInt(possibleMoves.size()));
        }
        return null;
    }

    private Stack<Coordinates> possibleMoves(GameBoard gb) {
        Stack possibleMoves = new Stack();
        Coordinates coords;

        for (int x = 1; x <= gb.getSize(); ++x) {
            for (int y = 1; y <= gb.getSize(); ++y) {
                if (gb.checkMove(myColor, coords = new Coordinates(x, y))) {
                    possibleMoves.push(coords);
                }
            }
        }
        return possibleMoves;
    }
}