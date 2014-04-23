package BirmiGangster;

import reversi.Coordinates;
import reversi.GameBoard;
import reversi.ReversiPlayer;
import reversi.OutOfBoundsException;

import java.util.concurrent.TimeoutException;
import java.util.ArrayList;

/**
 * IngwerPlayer is a ReversiPlayer implementation based on alpha-beta-search, heuristic
 * modifications and presorting.
 *
 * Credits go to: Ginger, salt, pepper, chilly, rice, chink sticks, wok ... and GladOS.
 *
 * To-Dos: - Better presort-algorithm? - Memory management - State-Saving (Tree-Structure)
 *
 * Done:
 *
 * - alpha-beta, min-max - Termination managing - Pass moves - GameOver bonus - min-max-choice bonus
 * - Corner bonus - Corner reaching move supply bonus - ...
 *
 * @author (c) 2011 atok (Markus Wegmann), decryphe (Hans Sj�kvist). All rights reserved.
 * @version 1.2.2, 05/25/2011
 */
public class IngwerPlayer implements ReversiPlayer {

    private final int WIN = Integer.MAX_VALUE;
    private final int LOOSE = Integer.MIN_VALUE;
    private final int TIE = 0;

    private int player = 0;
    private int enemyPlayer = 0;

    private int boardSize = 8;
    //private ArrayList<Coordinates> = new ArrayList<>

    private final int gameOverBonus = 41;
    private final int cornerBonus = 10;
    //private final int cornerReachBonus = 1;
    private final int takeOverBonus = 1;

    private final int beginningDepth = 5;

    private long timeout = 5000;
    private final long timeoutOffset = 2;
    private long oldTime;

    public IngwerPlayer() {
        System.out.println("IngwerPlayer joins the arena. Now it's paradox time!");
    }

    @Override
    public void initialize(int player, long timeout) {
        this.player = player;
        this.enemyPlayer = getEnemyPlayer();

        if (timeout != 0 && timeout < 100000) {
            this.timeout = timeout;
        }
    }

    @Override
    public Coordinates nextMove(GameBoard gb) {
        oldTime = System.currentTimeMillis() - timeoutOffset;
        boardSize = gb.getSize();
        int alpha = LOOSE;

        Coordinates bestMove = null;
        int bestScore = 0;

        ArrayList<Coordinates> openCoordinatesList = getOrderedOpenCoordinates(gb, player);

        if (openCoordinatesList.size() == 0) {
            return null;
        }

        int depth = beginningDepth;

        try {
            while (true) {
                alpha = LOOSE;

                Coordinates depthBestMove = null;

                for (Coordinates coord : openCoordinatesList) {
                    GameBoard gbClone = gb.clone();
                    gbClone.checkMove(player, coord);
                    gbClone.makeMove(player, coord);

                    int score = Min(gbClone, alpha, WIN, depth - 1);

                    if (score > alpha) {
                        alpha = score;
                        depthBestMove = coord;
                    }
                }

                bestMove = depthBestMove;
                bestScore = alpha;

                depth++;
            }
        } catch (Exception e) {
            checkTimeout();
            if (bestMove != null) {
                /*System.out.println("Ingwer got till depth " +
                 (depth-1) +
                 ", and is expecting a approximate score of " +
                 String.valueOf(bestScore) +
                 ". " +
                 "It needed " +
                 (timeout - timer - timeoutOffset) +
                 " milliseconds.");*/
                return bestMove;
            } else {
                /*System.out.println("Ingwer couldn't finish lowest depth " +
                 depth + " and is therefore returning a pseudo-random coord. " +
                 "It needed " +
                 (timeout - timer - timeoutOffset) +
                 " milliseconds.");*/
                return openCoordinatesList.get(0);
            }
        }
    }

    public int Max(GameBoard gb, int alpha, int beta, int depth) throws TimeoutException {
        if (!checkTimeout()) {
            throw new TimeoutException();
        }

        ArrayList<Coordinates> openCoordinatesList = getOrderedOpenCoordinates(gb, player);

        if (depth > 0) {
            if (!openCoordinatesList.isEmpty()) {
                for (Coordinates coord : openCoordinatesList) {
                    GameBoard gbClone = gb.clone();
                    gbClone.checkMove(player, coord);
                    gbClone.makeMove(player, coord);

                    int expectingValue = Min(gbClone, alpha, beta, depth - 1);

                    if (expectingValue > beta) {
                        return expectingValue;
                    } else if (expectingValue > alpha) {
                        alpha = expectingValue;
                    }
                }

                return alpha;
            } else {
                if (!gb.isFull()) {
                    return Min(gb, alpha, beta, depth - 1);
                } else {
                    return getFinalGameState(gb);
                }
            }
        } else {
            if (openCoordinatesList.isEmpty()) {
                if (!gb.isFull()) {
                    return countScoreFromIngwer(gb) - takeOverBonus;
                } else {
                    return getFinalGameState(gb);
                }
            } else {
                return countScoreFromIngwer(gb) + takeOverBonus;
            }
        }
    }

    public int Min(GameBoard gb, int alpha, int beta, int depth) throws TimeoutException {
        if (!checkTimeout()) {
            throw new TimeoutException();
        }

        ArrayList<Coordinates> openCoordinatesList = getOrderedOpenCoordinates(gb, enemyPlayer);

        if (depth > 0) {
            if (!openCoordinatesList.isEmpty()) {
                for (Coordinates coord : openCoordinatesList) {
                    GameBoard gbClone = gb.clone();
                    gbClone.checkMove(enemyPlayer, coord);
                    gbClone.makeMove(enemyPlayer, coord);

                    int expectingValue = Max(gbClone, alpha, beta, depth - 1);

                    if (expectingValue < alpha) {
                        return expectingValue;
                    } else if (beta > expectingValue) {
                        beta = expectingValue;
                    }
                }

                return beta;
            } else {
                if (!gb.isFull()) {
                    return Max(gb, alpha, beta, depth - 1);
                } else {
                    return getFinalGameState(gb);
                }
            }
        } else {
            if (openCoordinatesList.isEmpty()) {
                if (!gb.isFull()) {
                    return countScoreFromIngwer(gb) + takeOverBonus;
                } else {
                    return getFinalGameState(gb);
                }
            } else {
                return countScoreFromIngwer(gb) - takeOverBonus;
            }
        }
    }

    /*
     public ArrayList<Coordinates> getBlubOrderedOpenCoordinates(GameBoard gb, int player) // TO-DO: Better algorithm than insert-sort for small n?
     {
     ArrayList<Coordinates> openCoordinatesList = getOpenCoordinates(gb, player);
        
     ArrayList<Coordinates> openOrderedCoordinatesList = new ArrayList<Coordinates>();
        
     for (Coordinates coord: openCoordinatesList)
     {
     boolean couldInsert = false;
            
     for (int i = 0; i < openOrderedCoordinatesList.size(); i++)
     {
     if(getDistanceFromCenter(gb.getSize(), coord) >= getDistanceFromCenter(gb.getSize(), openOrderedCoordinatesList.get(i)))
     {
     openOrderedCoordinatesList.add(i, coord);
     couldInsert = true;
     break;
     }
     }
            
     if (!couldInsert)
     openOrderedCoordinatesList.add(coord);
     }
        
     return openOrderedCoordinatesList;
     }
     */
    public ArrayList<Coordinates> getOrderedOpenCoordinates(GameBoard gb, int player) {
        ArrayList<Coordinates> openCoordinatesList = new ArrayList<Coordinates>(24);

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

    public int countScoreFromIngwer(GameBoard gb) {
        int scoreCount = gb.countStones(player);
        scoreCount -= gb.countStones(enemyPlayer);
        scoreCount += countBonusFromIngwer(gb);

        return scoreCount;
    }

    public int countScoreFromIngwerWithout(GameBoard gb) {
        int scoreCount = gb.countStones(player);
        scoreCount -= gb.countStones(enemyPlayer);

        return scoreCount;
    }

    public int countBonusFromIngwer(GameBoard gb) {
        int bonusCount = 0;
        bonusCount += getValue(gb, new Coordinates(1, 1));
        bonusCount += getValue(gb, new Coordinates(boardSize, 1));
        bonusCount += getValue(gb, new Coordinates(1, boardSize));
        bonusCount += getValue(gb, new Coordinates(boardSize, boardSize));

        bonusCount *= cornerBonus;
        return bonusCount;
    }

    private int getValue(GameBoard gb, Coordinates coord) {
        try {
            if (gb.getOccupation(coord) == player) {
                return 1;
            } else if (gb.getOccupation(coord) == enemyPlayer) {
                return -1;
            } else {
                return 0;
            }
        } catch (OutOfBoundsException e) {
            return 0;
        }
    }

    public int getFinalGameState(GameBoard gb) {
        int gameState = countScoreFromIngwerWithout(gb);

        if (gameState > 0) {
            return gameOverBonus + gameState;
        } else if (gameState < 0) {
            return -gameOverBonus + gameState;
        } else {
            return TIE;
        }
    }

    private int getDistanceFromCenter(int size, Coordinates coord) {
        int row = coord.getRow() - size / 2;
        int column = coord.getCol() - size / 2;
        int distance = Math.abs(row) + Math.abs(column);

        return distance;
    }

    private int getEnemyPlayer() {
        if (player == GameBoard.RED) {
            return GameBoard.GREEN;
        } else {
            return GameBoard.RED;
        }
    }

    private boolean checkTimeout() {
        if (System.currentTimeMillis() < timeout + oldTime) {
            return true;
        } else {
            return false;
        }
    }
}
