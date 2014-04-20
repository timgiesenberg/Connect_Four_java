package connectfour;

import java.lang.Math.*;



/**
 *
 * @author tim.giesenberg@me.com
 */
public class GameLogic {

    int[][] gamefield = new int[7][7];

    
    public GameLogic(){
        check();
    }

    
    private void check() {
        for(int zeile = 0; zeile < gamefield.length; zeile++) {
            for(int spalte = Math.max((3-zeile),0); spalte <= Math.min(9-zeile,(gamefield[zeile].length)-1); spalte++) {
                System.out.println("Pruefe "+zeile+"/"+spalte);
            }
        }
        
        
    }
    
    
    public int getComputerMove(){
        double computerMoveDouble = Math.round(Math.random() * 7);
        int computerMove = (int) computerMoveDouble;
        
        return computerMove;
    }
}
