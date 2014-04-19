package u8a3;

import reversi.Coordinates;
import reversi.GameBoard;

public interface ICheckMove {
	/**
	 * Checks if a move is valid
	 * 
	 * @param gb the GameBoard of the current situation
	 * @param player the player which makes the move
	 * @param coord the position where the new stone shall be placed
	 * @return true iff according to Reversi's rules the given move is valid
	 */
	public  boolean checkMove(GameBoard gb, int player, Coordinates coord);
}
