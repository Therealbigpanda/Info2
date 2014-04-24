package u8a3;

import reversi.GameBoard;
import reversi.BoardFactory;
import reversi.Coordinates;
import reversi.Utils;

import org.junit.Test;
import org.junit.Assert;

public class Tests {

    private final int moves[][] = {
        {6, 5}, {3, 4}, {3, 3}, {5, 6}, {3, 5}, {2, 4}, {5, 3}, {4, 6}, {1, 4}, {1, 3}, {1, 2}, {4, 3}, {1, 5}, {2, 5},
        {2, 6}, {1, 6}, {1, 7}, {3, 6}, {5, 2}, {6, 3}, {3, 7}, {3, 2}, {3, 1}, {2, 1}, {1, 1}, {5, 1}, {4, 2}, {2, 3},
        {4, 1}, {6, 6}, {6, 1}, {2, 8}, {6, 4}, {7, 5}, {2, 2}, {7, 3}, {4, 8}, {2, 7}, {1, 8}, {-1, -1}, {8, 3}, {6, 2},
        {3, 8}, {8, 2}, {5, 7}, {8, 4}, {7, 1}, {4, 7}, {7, 4}, {-1, -1}, {8, 6}, {7, 6}, {8, 7}, {7, 7}, {8, 8}, {7, 8},
        {6, 7}, {5, 8}, {6, 8}, {-1, -1}, {8, 5}, {-1, -1}, {8, 1}, {-1, -1}, {7, 2}, {-1, -1}};

    private void checkMoves(GameBoard gb, int player) {
        ICheckMove checkMove = CheckMoveFactory.create();
        for (int x = 1; x <= gb.getSize(); x++) {
            for (int y = 1; y < gb.getSize(); y++) {
                Coordinates c = new Coordinates(x, y);
                final boolean possible = gb.checkMove(player, c);
                final boolean answer = checkMove.checkMove(gb, player, c);
                String situation = "Spielbrett:\n" + gb + c + ", Spieler: " + Utils.toString(player);
                if (possible) {
                    Assert.assertTrue("Zug wäre möglich: " + situation, answer);
                } else {
                    Assert.assertFalse("Zug ist nicht möglich: " + situation, answer);
                }
            }
        }
    }

    @Test
    public void runGame() {
        GameBoard gb = BoardFactory.create();
        int player = GameBoard.RED;
        for (int move = 0; move < moves.length; move++) {
            checkMoves(gb, player);
            Coordinates c = new Coordinates(moves[move][0], moves[move][1]);
            if (moves[move][0] != -1) {
                gb.checkMove(player, c);
                gb.makeMove(player, c);
            }
            player = Utils.other(player);
        }
    }
}
