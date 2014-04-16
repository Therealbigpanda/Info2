package u7a3;

import reversi.*;

/**
 * HumanPlayer implementiert auf einfache Art das Interface für einen
 * Reversi-Spieler ({@link reversi.ReversiPlayer}). Er berechnet einen zufälligen Zug und
 * führt diesen aus.
 * 
 * @see reversi.ReversiPlayer
 */
public class RandomPlayer implements ReversiPlayer {

    @Override
    public void initialize(int myColor, long timeLimit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Coordinates nextMove(GameBoard gb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}