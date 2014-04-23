package BirmiGangster;

import reversi.Coordinates;
import reversi.GameBoard;
import reversi.ReversiPlayer;

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
public class IngwerOptimized implements ReversiPlayer {

    private final int WIN = Integer.MAX_VALUE;
    private final int LOOSE = Integer.MIN_VALUE;
    private final int TIE = 0;

    private int player = 0;
    private int enemyPlayer = 0;

    private int boardSize = 8;

    private final int gameOverBonus = 35;
    private final int cornerBonus = 8;
    private final int takeOverBonus = 1;

    private final int beginningDepth = 7;

    private long timeout = 5000;
    private final long timeoutOffset = 10;
    private long oldTime;

    private boolean timeoutHasOccured = false;

    public IngwerOptimized() {
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
        timeoutHasOccured = false;
        boardSize = gb.getSize();
        int alpha = LOOSE;

        Coordinates bestMove = null;
        int bestScore = 0;

        ArrayList<Coordinates> openCoordinatesList = getOpenCoordinates(gb, player);

        if (openCoordinatesList.size() == 0) {
            return null;
        }

        int depth = beginningDepth;

        //try
        //{
        while (!timeoutHasOccured) {
            checkTimeout();
            alpha = LOOSE;

            Coordinates depthBestMove = null;

            for (Coordinates coord : openCoordinatesList) {
                GameBoard gbClone = gb.clone();
                gbClone.checkMove(player, coord);
                gbClone.makeMove(player, coord);

                int score = RecursionStep(gbClone, alpha, WIN, depth - 1, -1);

                //System.out.print(score+": ");
                if (score > alpha) {
                    alpha = score;
                    depthBestMove = coord;
                    //System.out.println("no failure atm");
                }
	                //else
                //System.out.println("failure while trying depth "+depth);
            }

            if (depthBestMove != null) {
                bestMove = depthBestMove;
                bestScore = alpha;
            }

            depth++;
        }
        //}
        //catch(Exception e)
        //{}

        if (timeoutHasOccured) {
            long timer = System.currentTimeMillis() - oldTime;
            if (bestMove != null) {
                System.out.println("Ingwer got till depth "
                        + (depth - 1)
                        + ", and is expecting a approximate score of "
                        + String.valueOf(bestScore)
                        + ". "
                        + "It needed "
                        + (timer)
                        + " milliseconds.");
                return bestMove;
            } else {
                System.out.println("Ingwer couldn't finish lowest depth "
                        + depth + " and is therefore returning a pseudo-random coord. "
                        + "It needed "
                        + (timer)
                        + " milliseconds.");
                return openCoordinatesList.get(0);
            }
        } else {
            return null;
        }
    }

    public int RecursionStep(GameBoard gb, int alpha, int beta, int depth, int MinMax) // MinMax = -1 for Min
    {
        checkTimeout();
        if (depth > 0 && !timeoutHasOccured) {
            // Get player
            int currentPlayer = player;
            if (MinMax == -1) {
                currentPlayer = getEnemyPlayer();
            }
            // Get open coordinates
            ArrayList<Coordinates> openCoordinatesList;
            if (MinMax == 1) {
                openCoordinatesList = getOpenCoordinates(gb, player);
            } else {
                openCoordinatesList = getOpenCoordinates(gb, enemyPlayer);
            }

            // Calculate score for current recursion
            if (!openCoordinatesList.isEmpty()) {
                int expectingValue = 0;
                for (Coordinates c : openCoordinatesList) {
                    GameBoard clone = gb.clone();
                    clone.checkMove(currentPlayer, c);
                    clone.makeMove(currentPlayer, c);
                    expectingValue = RecursionStep(clone, alpha, beta, depth - 1, MinMax * (-1));

                    // Stop checking if a really bad child has been found -> risk is too high
                    if (MinMax == 1) // Max
                    {
                        if (expectingValue > beta) {
                            return expectingValue;
                        } else if (expectingValue > alpha) {
                            alpha = expectingValue;
                        }
                    } else // Min
                    {
                        if (expectingValue < alpha) {
                            return expectingValue;
                        } else if (beta > expectingValue) {
                            beta = expectingValue;
                        }
                    }
                }

                // When all children have been checked
                if (MinMax == 1) {
                    return alpha;
                } else {
                    return beta;
                }
            } else {
                if (!gb.isFull() && !timeoutHasOccured) // Pass once
                {
                    return RecursionStep(gb, alpha, beta, depth - 1, MinMax * (-1));
                } else // Board is full, nothing to do
                {
                    return getFinalGameState(gb);
                }
            }
        } else {
            try {
                return countScoreFromIngwer(gb);
            } catch (Exception e) {
                System.out.println("something bad happened");
            }
        }
        return 0;
    }

    public ArrayList<Coordinates> getOpenCoordinates(GameBoard gb, int player) {
        ArrayList<Coordinates> openCoordinatesList = new ArrayList<Coordinates>(10);
        for (int x = 1; x <= boardSize; x++) {
            for (int y = 1; y <= boardSize; y++) {
                if (gb.checkMove(player, new Coordinates(x, y))) {
                    openCoordinatesList.add(new Coordinates(x, y));
                }
            }
        }
        return openCoordinatesList;
    }

    public int countScoreFromIngwer(GameBoard gb) throws Exception {
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

    public int countBonusFromIngwer(GameBoard gb) throws Exception {
        int bonusCount = 0;
        try {
            bonusCount += getValue(gb, new Coordinates(1, 1));
            bonusCount += getValue(gb, new Coordinates(boardSize, 1));
            bonusCount += getValue(gb, new Coordinates(1, boardSize));
            bonusCount += getValue(gb, new Coordinates(boardSize, boardSize));
            bonusCount *= cornerBonus;

            int edgebonus = 0;
            int val = 0;
            for (int x = 1; x <= boardSize; x++) {
                val = getValue(gb, new Coordinates(x, 1));
                if (val > 0) {
                    edgebonus++;
                }
                val = getValue(gb, new Coordinates(x, boardSize));
                if (val > 0) {
                    edgebonus++;
                }
                val = getValue(gb, new Coordinates(1, x));
                if (val > 0) {
                    edgebonus++;
                }
                val = getValue(gb, new Coordinates(boardSize, x));
                if (val > 0) {
                    edgebonus++;
                }
            }

            bonusCount += edgebonus;
        } catch (Exception e) {
        }

        return bonusCount;
    }

    private int getValue(GameBoard gb, Coordinates coord) throws Exception // O(1)
    {
        if (gb.getOccupation(coord) == player) {
            return 1;
        } else if (gb.getOccupation(coord) == enemyPlayer) {
            return -1;
        } else {
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

    private int getEnemyPlayer() // O(1)
    {
        if (player == GameBoard.RED) {
            return GameBoard.GREEN;
        } else {
            return GameBoard.RED;
        }
    }

    private void checkTimeout() {
        if (System.currentTimeMillis() > oldTime + timeout - timeoutOffset) {
            timeoutHasOccured = true;
        }
    }
}
