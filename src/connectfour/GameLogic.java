package connectfour;

import java.util.ArrayList;



/**
 *
 * @author tim.giesenberg@me.com
 */
public class GameLogic {

    
    
    

    
    int[][] gamefield = new int[7][7];


 
    
    public GameLogic(){
	
	
    }

    
    public ArrayList<FourLine> check() {
        boolean found = false;
	
	ArrayList<FourLine> lines = new ArrayList<FourLine>();
	
        for(int zeile = 0; zeile < gamefield.length; zeile++) {
            for(int spalte = 0; spalte < (gamefield[zeile].length); spalte++) {
                found = false;
                
                //System.out.println("Pruefe "+zeile+"/"+spalte);
                if(gamefield[zeile][spalte] == 0) continue;
                int p = gamefield[zeile][spalte];
		
                if(zeile < 4) {
		    
                    // checks diagonal lines from top right to bottom left    
                    if(spalte >= 3) {
                        if(p == gamefield[zeile+1][spalte-1] && p == gamefield[zeile+2][spalte-2] && p == gamefield[zeile+3][spalte-3]) {
			    lines.add(new FourLine(zeile, spalte, FourLine.BOTTOM_LEFT, p));
			    
                        }
                    }
                    // checks diagonal lines from top left to bottom right
                    if(spalte <= (gamefield[zeile].length)-4) {
                        if(p == gamefield[zeile+1][spalte+1] && p == gamefield[zeile+2][spalte+2] && p == gamefield[zeile+3][spalte+3]) {
                            lines.add(new FourLine(zeile, spalte, FourLine.BOTTOM_RIGHT, p));
			    
                        }
                    }
                    
                    // checks vertical lines from top to bottom
                    if(p == gamefield[zeile+1][spalte] && p == gamefield[zeile+2][spalte] && p == gamefield[zeile+3][spalte]) {
			lines.add(new FourLine(zeile, spalte, FourLine.BOTTOM, p));
                    }
                }
		
		// checks horizontal lines from left to right
		if(spalte <= (gamefield.length) - 4) {
                    if(p == gamefield[zeile][spalte+1] && p == gamefield[zeile][spalte+2] && p == gamefield[zeile][spalte+3]) {
			lines.add(new FourLine(zeile, spalte, FourLine.RIGHT, p));
                    }
		}
            }
        }  
	return lines;
    }
    
    
    /*
        private void check() {
        for(int zeile = 0; zeile <= (gamefield.length)-4; zeile++) {
            for(int spalte = 3; spalte < (gamefield[zeile].length); spalte++) {
                System.out.println("Pruefe "+zeile+"/"+spalte);
                if(gamefield[zeile][spalte] == 0) continue;
                int p = gamefield[zeile][spalte];
                if(p == gamefield[zeile+1][spalte-1] && p == gamefield[zeile+2][spalte-2] && p == gamefield[zeile+3][spalte-3]) {
                    System.out.println("FOUND DIAGONAL LINE STARTING AT "+ zeile +"/"+spalte);
                }
            }
            for(int spalte = 0; spalte <= (gamefield[zeile].length)-4; spalte++) {
                System.out.println("Pruefe "+zeile+"/"+spalte);
                if(gamefield[zeile][spalte] == 0) continue;
                int p = gamefield[zeile][spalte];
                if(p == gamefield[zeile+1][spalte+1] && p == gamefield[zeile+2][spalte+2] && p == gamefield[zeile+3][spalte+3]) {
                    System.out.println("FOUND DIAGONAL LINE STARTING AT "+ zeile +"/"+spalte);
                }
            }
        }  
    }
    */
    
    
    public int getComputerMove(){
        double computerMoveDouble = Math.round(Math.random() * 7);
        int computerMove = (int) computerMoveDouble;
        
        return computerMove;
    }
}
