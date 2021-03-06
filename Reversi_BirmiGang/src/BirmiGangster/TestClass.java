package BirmiGangster;

import java.io.File;
import java.io.IOException;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea-Pascal Willi & Andrea Tuccillo
 */
public class TestClass {

    public static void main(String[] args) {

        File file = new File("reversilog");

        if (file.delete()) {
            System.out.println(file.getName() + " is deleted!");
        } else {
            System.out.println("Delete operation is failed.");
        }

        for (int i = 0; i < 20; i++) {
            try {
                reversi.Arena.main(args);
            } catch (NullPointerException e) {
                //do nothing
            }
        }
        Path path = FileSystems.getDefault().getPath(file.getAbsolutePath());
        List<String> stringList;

        int redWins = 0;
        int greenWins = 0;

        int draw = 0;

        int reds = 0;
        int greens = 0;
        try {
            stringList = Files.readAllLines(path, UTF_8);
            for (String line : stringList) {
                if (line.contains("finished")) {
                    int firstRedDigit = Integer.parseInt(line.substring(line.indexOf("reds=") + 5, line.indexOf("reds=") + 6));

                    try {
                        int secondRedDigit = Integer.parseInt(line.substring(line.indexOf("reds=") + 6, line.indexOf("reds=") + 7));
                        reds = 10 * firstRedDigit + secondRedDigit;
                    } catch (java.lang.NumberFormatException e) {
                        reds = firstRedDigit;
                    } catch (StringIndexOutOfBoundsException x) {
                        //do nothing
                    }

                    int firstGreenDigit = Integer.parseInt(line.substring(line.indexOf("greens=") + 7, line.indexOf("greens=") + 8));

                    try {
                        int secondGreenDigit = Integer.parseInt(line.substring(line.indexOf("greens=") + 8, line.indexOf("greens=") + 9));
                        greens = 10 * firstGreenDigit + secondGreenDigit;
                    } catch (java.lang.NumberFormatException e) {
                        greens = firstGreenDigit;
                    } catch (StringIndexOutOfBoundsException x) {
                        // do nothing
                    }

                    if (reds > greens) {
                        redWins++;
                    }
                    if (reds == greens) {
                        draw++;
                    }
                    if (reds < greens) {
                        greenWins++;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Red Wins: " + redWins + " Green Wins: " + greenWins + " Draws: " + draw);
    }

}
