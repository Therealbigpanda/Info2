package u9a2;

import reversi.Coordinates;
import reversi.GameBoard;
import reversi.ReversiPlayer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Player that replays moves from a logfile.
 * 
 * The player's color decides which moves are replayed.
 * The considered logfile is $HOME/reversi.log
 */
public class LogPlayer implements ReversiPlayer {
	private BufferedReader reader;
	Pattern pattern;

	public void initialize(int myColor, long timeLimit) {
		String color = null;
		if (myColor == GameBoard.RED) {
			color = "Red";
		} else {
			color = "Green";
		}
		pattern = Pattern.compile(".*" + color + "move=(null|([0-9]+),([0-9]+))");

		String logfile = System.getProperty("user.home") 
							  + System.getProperty("file.separator") 
							  + "reversi.log";
		try {
			reader = new BufferedReader(new FileReader(logfile));
		} catch (FileNotFoundException e) {
			AssertionError ae = new AssertionError("Logfile not found");
			ae.initCause(e);
			throw ae;
		}
	}

	public Coordinates nextMove(GameBoard gb) {
		while (true) {
			String line;
			try {
				line = reader.readLine();
			} catch (IOException e) {
				AssertionError ae = new AssertionError(
						"Failed to read from logfile");
				ae.initCause(e);
				throw ae;
			}
			Matcher m = pattern.matcher(line);

			if (m.matches()) {
				if (m.group(1).equals("null")) {
					return null;
				} else {
					int x = Integer.parseInt(m.group(2));
					int y = Integer.parseInt(m.group(3));
					return new Coordinates(x, y);
				}
			}
		}
	}
}
