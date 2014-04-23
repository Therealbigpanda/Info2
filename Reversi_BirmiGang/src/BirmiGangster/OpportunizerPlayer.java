package BirmiGangster;

import reversi.Coordinates;
import reversi.GameBoard;
import reversi.ReversiPlayer;
import reversi.OutOfBoundsException;

import java.util.ArrayList;

/**
 *
 * @author Markus Wegmann, Hans Sj�kvist
 * @version 1.1
 *
 */
public class OpportunizerPlayer implements ReversiPlayer {

    private int player = 0;
    private int enemyPlayer = 0;

    public OpportunizerPlayer() {
        System.out.println("OpportunizerPlayer joins the arena.");
    }

    @Override
    public void initialize(int player, long timeout) {
        this.player = player;

        if (player == GameBoard.RED) {
            enemyPlayer = GameBoard.GREEN;
        } else {
            enemyPlayer = GameBoard.RED;
        }
    }

    @Override
    public Coordinates nextMove(GameBoard gb) {
        ArrayList<Coordinates> openCoordinatesList = getOpenCoordinates(gb);

        int bestValue = Integer.MIN_VALUE;
        Coordinates bestMove = null;

        for (Coordinates coord : openCoordinatesList) {
            GameBoard gbClone = gb.clone();

            gbClone.checkMove(player, coord);
            gbClone.makeMove(player, coord);

            int countValue = countStonesFromPlayer(gbClone, player) - countStonesFromPlayer(gbClone, enemyPlayer);

            if (bestValue < countValue) {
                bestValue = countValue;
                bestMove = coord;
            }
        }

        return bestMove;
    }

    public ArrayList<Coordinates> getOpenCoordinates(GameBoard gb) {
        ArrayList<Coordinates> openCoordinatesList = new ArrayList<Coordinates>();

        int boardSize = gb.getSize();

        for (int i = 1; i <= boardSize; i++) {
            for (int j = 1; j <= boardSize; j++) {
                Coordinates openCoordinates = new Coordinates(i, j);

                if (gb.checkMove(player, openCoordinates)) {
                    openCoordinatesList.add(openCoordinates);
                }
            }
        }

        return openCoordinatesList;
    }

    public int countStonesFromPlayer(GameBoard gb, int player) {
        try {
            int stoneCount = 0;

            int boardSize = gb.getSize();

            for (int i = 1; i <= boardSize; i++) {
                for (int j = 1; j <= boardSize; j++) {
                    Coordinates coord = new Coordinates(i, j);

                    if (gb.getOccupation(coord) == player) {
                        stoneCount++;
                    }
                }
            }

            return stoneCount;
        } catch (OutOfBoundsException e) // Cannot happen, cause coords must all be proper
        {
            return 0;
        }
    }
}
