package u8a3;

import reversi.Coordinates;
import reversi.GameBoard;
import static reversi.GameBoard.EMPTY;
import reversi.OutOfBoundsException;

/**
 *
 * @author Andrea-Pascal Willi
 */
public class CheckMove implements ICheckMove {

    @Override
    public boolean checkMove(GameBoard gb, int player, Coordinates coord) {
        try {
            if (gb.getOccupation(coord) != EMPTY)
                return false;
        } catch (OutOfBoundsException e) {
            return false;
        }
            
        for (int i = -1; i <= 1; ++i)
            for (int j = -1; j <= 1; ++j)
                if (i != 0 || j != 0)
                    if (checkDirection(gb, player, coord, i, j)) 
                        return true; 
        return false;
    }

    private boolean checkDirection(GameBoard gb, int player, Coordinates coord, int x, int y) {
        try {
            Coordinates newCoord = new Coordinates(coord.getRow() + x, coord.getCol() + y);
            if (gb.getOccupation(newCoord) == EMPTY) {
                return false;
            }
            if (gb.getOccupation(coord) == reversi.Utils.other(player) && gb.getOccupation(newCoord) == player) {
                return true;
            }
            if (gb.getOccupation(newCoord) == reversi.Utils.other(player)) {
                return checkDirection(gb, player, newCoord, x, y);
            }
        } catch (OutOfBoundsException e) {
            return false;
        }
        return false;
    }
}
