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

    private final Coordinates[] allDirections = new Coordinates[8];
    int player = 0;
    GameBoard gb;

    public CheckMove() {
        allDirections[0] = new Coordinates(-1, -1);
        allDirections[1] = new Coordinates(-1, 0);
        allDirections[2] = new Coordinates(-1, 1);
        allDirections[3] = new Coordinates(0, -1);
        allDirections[4] = new Coordinates(0, 1);
        allDirections[5] = new Coordinates(1, -1);
        allDirections[6] = new Coordinates(1, 0);
        allDirections[7] = new Coordinates(1, 1);
    }

    @Override
    public boolean checkMove(GameBoard gb, int player, Coordinates coord) {
        this.player = player;
        this.gb = gb;

        try {
            if (gb.getOccupation(coord) != EMPTY) {
                return false;
            }
        } catch (OutOfBoundsException e) {
            return false;
        }
        for (Coordinates i : allDirections) {
            if (isLineTurnable(coord, i)) {
                return true;
            }
        }
        return false;
    }

    private boolean isLineTurnable(Coordinates coord, Coordinates direction) {
        boolean hasEnemyStonesInLine = false;

        Coordinates nextCoord = nextCoord(coord, direction);

        try {
            while (gb.getOccupation(nextCoord) != player) {
                if (!gb.validCoordinates(nextCoord)) {
                    return false;
                }
                if (gb.getOccupation(nextCoord) == EMPTY) {
                    return false;
                } else {
                    hasEnemyStonesInLine = true;
                    nextCoord = nextCoord(nextCoord, direction);
                }
            }
        } catch (OutOfBoundsException e) {
            return false;
        }

        return hasEnemyStonesInLine;
    }

    private Coordinates nextCoord(Coordinates coord, Coordinates direction) {
        return new Coordinates(coord.getRow() + direction.getRow(), coord.getCol() + direction.getCol());
    }
}
