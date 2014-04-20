/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connectfour;

/**
 *
 * @author Zeebo
 */
public class Test {
    
    int[][] gamefield;
    
    public Test(int[][] gamefield) {
	this.gamefield = gamefield;
    }
    
    public void run() {
	
	System.out.println("Building Test-Grid...");
        gamefield[0][4] = 1;
        gamefield[1][3] = 1;
        gamefield[2][2] = 1;
        gamefield[3][1] = 1;
        gamefield[2][2] = 1;
        gamefield[3][3] = 1;
        gamefield[4][4] = 1;
        gamefield[5][5] = 1;
	gamefield[2][1] = 1;
	gamefield[2][2] = 1;
	gamefield[2][3] = 1;
	gamefield[2][4] = 1;
	gamefield[3][6] = 2;
	gamefield[4][6] = 2;
	gamefield[5][6] = 2;
	gamefield[6][6] = 2;
	
        System.out.print("   | 0 | 1 | 2 | 3 | 4 | 5 | 6 |");
        for(int x = 0; x < gamefield.length; x++) {
            
            printlineX();
            System.out.println();
            System.out.print(x +"  ");
            for(int y = 0; y < gamefield[x].length; y++) {
                
                if(gamefield[x][y] == 1) {
                    System.out.print("| X ");
                } else if(gamefield[x][y] == 2) {
                    System.out.print("| O ");
                } else {
                    System.out.print("|   ");
                }
                if(y == 6) System.out.print("|");
            }
        }
        System.out.println();
        
        
        
    }
    
    private void printlineX() {
        System.out.println();
        for(int i = 0; i < gamefield.length+1; i++) {
            System.out.print("----");
        }
        
    }
    
}
