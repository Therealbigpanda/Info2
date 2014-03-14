/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BirmiGangster;

import reversi.*;

/**
 *
 * @author andreatuccillo
 */
public class HumanGangster implements reversi.ReversiPlayer {

    int myColor;
    long TIME_LIMIT;
    @Override
    public void initialize(int i, long l) {
        myColor = i;
        TIME_LIMIT = l;
    }

    @Override
    public Coordinates nextMove(GameBoard gb) {
        
        for (int row=1; row<=gb.getSize(); row ++){
            for (int column=1; column<=gb.getSize(); column ++){
                if(gb.checkMove(myColor, new Coordinates(row, column))) return new Coordinates(row, column);
            }
        }
        
        return null;
    }


}
