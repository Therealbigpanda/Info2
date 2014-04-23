package u9a3; // Aus Übung 3: Muss angepasst werden.

import reversi.Coordinates;
import reversi.GameBoard;
import reversi.ReversiPlayer;
import reversi.OutOfBoundsException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Math;


/**
 * IngwerPlayer is a ReversiPlayer implementation based on alpha-beta-search, heuristic modifications and presorting.
 *
 * Credits go to: Ginger, salt, pepper, chilly, rice, chink sticks, wok ... and GladOS.
 *
 * To-Dos:
 * - Better presort-algorithm?
 * - Memory management
 * - State-Saving (Tree-Structure)
 *
 * Done:
 *
 * - alpha-beta, min-max
 * - Termination managing
 * - Pass moves
 * - GameOver bonus
 * - min-max-choice bonus
 * - Corner bonus
 * - Corner reaching move supply bonus
 * - ...
 *
 * @author (c) 2011 atok (Markus Wegmann), decryphe (Hans Sj�kvist). All rights reserved.
 * @version 1.2.2, 05/25/2011
 */
public class Ingwer2 implements ReversiPlayer {

    private final int WIN = Integer.MAX_VALUE;
    private final int LOOSE = Integer.MIN_VALUE;
    private final int TIE = 0;
    
    private int player = 0;
    private int enemyPlayer = 0;
    
    private int boardSize = 8;
    private ArrayList<Coordinates> unusedCoordinatesList = new ArrayList<Coordinates>(61);
    private LinkedList<GameTreeNode> nextNodes = new LinkedList<GameTreeNode>();
    private GameTreeNode rootNode = null;
    
    private final int gameOverBonus = 41;
    private final int cornerBonus = 10;
    private final int takeOverBonus = 1;
    
    private final int beginningDepth = 5;
    
    private long timeout = 5000;
    private final long timeoutOffset = 10;
    private long oldTime;
    
    private boolean timeoutHasOccured = false;
    
    
    public Ingwer2()
    {
        System.out.println("Ingwer2 joins the arena. Now it's paradox time!");
    }
    
    @Override
    public void initialize(int player, long timeout)
    {
        this.player = player;
        this.enemyPlayer = getEnemyPlayer();
        
        for(int x=1; x<=boardSize; x++)
        {
        	for(int y=1; y<=boardSize; y++)
        	{
        		// Note: starting layout will be updated at first nextMove() call.
        		unusedCoordinatesList.add(new Coordinates(x,y));
        	}
        }
        
        if (timeout != 0 && timeout < 100000)
            this.timeout = timeout;
    }

    @Override
    public Coordinates nextMove(GameBoard gb)
    {
    	// Set necessary variables
        oldTime = System.currentTimeMillis() - timeoutOffset;
        timeoutHasOccured = false;
        boardSize = gb.getSize();
        int alpha = LOOSE;
        
        Coordinates bestMove = null;
        int bestScore = 0;
        
        // Initialize root GameBoard or update if empty
        if(rootNode == null)
        	rootNode = new GameTreeNode(gb, null);
        else
        {
        	rootNode = rootNode.findEquivalentBoard(gb);
        	if(rootNode == null)
        		rootNode = new GameTreeNode(gb, null);
        }
        
        // First node to iterate over
        nextNodes.add(rootNode);
        
        // Update unusedCoordinatesList to represent current state of the board.
        try
        {
        	ArrayList<Coordinates> toRemove = new ArrayList<Coordinates>(2);
	        for(Coordinates c: unusedCoordinatesList)
	        {
	        	if(gb.getOccupation(c)!=GameBoard.EMPTY)
	        		toRemove.add(c);
	        }
	        unusedCoordinatesList.removeAll(toRemove);
	        toRemove.clear();
        }
        catch(OutOfBoundsException e)
        {}

        // Get fields where a stone can be placed
        ArrayList<Coordinates> openCoordinatesList = getOpenCoordinates(gb, player);
        if (openCoordinatesList.size() == 0)
            return null; // Pass if nothing's possible
        
        int depth = beginningDepth;
        
        // Iterate over queue
        while (!timeoutHasOccured)
        {
        	checkTimeout();
        	
        	GameTreeNode currentNode = nextNodes.pop();
        	nextNodes.addAll(currentNode.examine()); // Create openCoordinatesList if necessary and then get its children
        	
//            alpha = LOOSE;
//            
//            Coordinates depthBestMove = null;
//            
//            for(Coordinates coord: openCoordinatesList)
//            {
//                GameBoard gbClone = gb.clone();
//                gbClone.checkMove(player, coord);
//                gbClone.makeMove(player, coord);
//                
//                int score = Min(gbClone, alpha, WIN, depth-1);
//                
//                if (score > alpha)
//                {
//                    alpha = score;
//                    depthBestMove = coord;
//                }
//            }
//            
//            bestMove = depthBestMove;
//            bestScore = alpha;
//            
            if(nextNodes.isEmpty())
            	break;
        }
        
        if(timeoutHasOccured)
        {
            long timer = System.currentTimeMillis() - oldTime;
            if(bestMove != null)
            {
                System.out.println("Ingwer got till depth " +
                        (depth-1) +
                        ", and is expecting a approximate score of " +
                        String.valueOf(bestScore) +
                        ". " +
                        "It needed " +
                        (timer) +
                        " milliseconds.");
                return bestMove;
            }
            else
            {
                System.out.println("Ingwer couldn't finish lowest depth " +
                        depth + " and is therefore returning a pseudo-random coord. " +
                        "It needed " +
                        (timer) +
                        " milliseconds.");
                return openCoordinatesList.get(0);
            }
        }
        else
        	return null;
    }
    
    public int Max(GameBoard gb, int alpha, int beta, int depth)
    {
    	checkTimeout();
    	
        ArrayList<Coordinates> openCoordinatesList = getOrderedOpenCoordinates(gb, player);
        
        if (depth > 0)
        {
            if (!openCoordinatesList.isEmpty())
            {    
                for (Coordinates coord: openCoordinatesList)
                {
                    GameBoard gbClone = gb.clone();
                    gbClone.checkMove(player, coord);
                    gbClone.makeMove(player, coord);
                    
                    int expectingValue = Min(gbClone, alpha, beta, depth-1);
                    
                    if(expectingValue > beta)
                        return expectingValue;
                    else if (expectingValue > alpha)
                            alpha = expectingValue;
                    
                    if(timeoutHasOccured)
                    	break;
                }
            
                return alpha;
            }
            else
            {
                if(!gb.isFull() && !timeoutHasOccured) // Pass once
                    return Min(gb, alpha, beta, depth-1);
                else
                    return getFinalGameState(gb);
            }
        }
        else
        {
            if (openCoordinatesList.isEmpty())
            {
                if (!gb.isFull())
                    return countScoreFromIngwer(gb) - takeOverBonus;
                else
                    return getFinalGameState(gb);
            }
            else
                return countScoreFromIngwer(gb) + takeOverBonus;
        }
    }
    
    public int Min(GameBoard gb, int alpha, int beta, int depth)
    {
        checkTimeout();
        
        ArrayList<Coordinates> openCoordinatesList = getOrderedOpenCoordinates(gb, enemyPlayer);
        
        if (depth > 0)
        {
            if (!openCoordinatesList.isEmpty())
            {
                for (Coordinates coord: openCoordinatesList)
                {
                    GameBoard gbClone = gb.clone();
                    gbClone.checkMove(enemyPlayer, coord);
                    gbClone.makeMove(enemyPlayer, coord);
                    
                    int expectingValue = Max(gbClone, alpha, beta, depth-1);
                    
                    if(expectingValue < alpha)
                        return expectingValue;
                    else if (beta > expectingValue)
                        beta = expectingValue;
                    
                    if(timeoutHasOccured)
                    	break;
                }
                
                return beta;
            }
            else
            {
                if(!gb.isFull() && !timeoutHasOccured) // Pass one round
                    return Max(gb, alpha, beta, depth-1);
                else
                    return getFinalGameState(gb);
            }
        }
        else
        {
            if (openCoordinatesList.isEmpty())
            {
                if (!gb.isFull())
                    return countScoreFromIngwer(gb) + takeOverBonus;
                else
                    return getFinalGameState(gb);
            }
            else
            {
                return countScoreFromIngwer(gb) - takeOverBonus;
            }
        }
    }
    
    public int countScoreFromIngwer(GameBoard gb)
    {
        int scoreCount = gb.countStones(player);
        scoreCount -= gb.countStones(enemyPlayer);
        scoreCount += countBonusFromIngwer(gb);        

        return scoreCount;
    }
    
    public int countScoreFromIngwerWithout(GameBoard gb)
    {
        int scoreCount = gb.countStones(player);
        scoreCount -= gb.countStones(enemyPlayer);

        return scoreCount;
    }
    
    public int countBonusFromIngwer(GameBoard gb)
    {    
        int bonusCount = 0;
        bonusCount += getValue(gb, new Coordinates(1, 1));
        bonusCount += getValue(gb, new Coordinates(boardSize, 1));
        bonusCount += getValue(gb, new Coordinates(1, boardSize));
        bonusCount += getValue(gb, new Coordinates(boardSize, boardSize));
        
        bonusCount *= cornerBonus;
        return bonusCount;
    }
    
    private int getValue(GameBoard gb, Coordinates coord) // O(1)
    {
        try
        {
            if (gb.getOccupation(coord) == player)
                return 1;
            else if (gb.getOccupation(coord) == enemyPlayer)
                return -1;
            else
                return 0;
        }
        catch (OutOfBoundsException e)
        {
            return 0;
        }
    }
    
    public int getFinalGameState(GameBoard gb)
    {
        int gameState = countScoreFromIngwerWithout(gb);
        
        if(gameState > 0)
            return gameOverBonus + gameState;
        else if(gameState < 0)
            return -gameOverBonus + gameState;
        else
            return TIE;
    }
    
    private int getDistanceFromCenter(int size, Coordinates coord) // O(1)
    {
        int row = coord.getRow()-size/2;
        int column = coord.getCol()-size/2;
        int distance = Math.abs(row) + Math.abs(column);
        
        return distance;
    }
    
    private int getEnemyPlayer() // O(1)
    {
        if (player == GameBoard.RED)
            return GameBoard.GREEN;
        else
            return GameBoard.RED;
    }
    
    private void checkTimeout()
    {    
        if (System.currentTimeMillis() >  oldTime + timeout - timeoutOffset)
        	timeoutHasOccured = true;
    }
}