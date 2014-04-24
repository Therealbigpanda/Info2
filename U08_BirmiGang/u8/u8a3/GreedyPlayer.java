package u8a3;

import reversi.*;

/**
 * GreedyPlayer implementiert das Interface für einen {@link reversi.ReversiPlayer}. Er spielt immer
 * den unmittelbar besten Zug ohne Rücksicht auf spätere Züge.
 *
 * @author Andrea-Pascal Willi
 * @see reversi.ReversiPlayer
 */
public class GreedyPlayer implements ReversiPlayer {

    @Override
    public void initialize(int myColor, long timeLimit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Coordinates nextMove(GameBoard gb) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
