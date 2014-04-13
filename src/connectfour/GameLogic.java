package connectfour;

import java.lang.Math.*;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class GameLogic {
    
    public GameLogic(){
        
    }
    
    public int getComputerMove(){
        double computerMoveDouble = Math.round(Math.random() * 7);
        int computerMove = (int) computerMoveDouble;
        
        return computerMove;
    }
}
