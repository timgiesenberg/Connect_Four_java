/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connectfour;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Zeebo
 */
public class Test {
    
    int[][] gamefield;
    
    public Test(int[][] gamefield) {
	this.gamefield = gamefield;
    }
    
    public void printGrid() {
	
	System.out.print("   |");
	for(int i = 0; i < gamefield[0].length; i++) {
	    System.out.print(" " + i + " |");
	}
	
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
	System.out.println();
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
	gamefield[2][6] = 2;
	gamefield[3][6] = 2;
	gamefield[4][6] = 2;
	gamefield[5][6] = 2;
	
	printGrid();
        
        
        
    }
    
    public void clearGrid() {
	for(int i = 0; i < gamefield.length; i++){
	    for(int k = 0; k < gamefield[i].length; k++) {
		gamefield[i][k] = 0;
	    }
	}
    }
    
    public void fillRandom() {
	Random randomPlayer = new Random();
	for(int i = 0; i < gamefield.length; i++){
	    for(int k = 0; k < gamefield[i].length; k++) {
		gamefield[i][k] = randInt(1,2);
	    }
	}
    }
    
    
    public void fillRandom2() {
	Random rando = new Random();
	for(int i = 0; i < gamefield[0].length; i++){
	    for(int k = gamefield.length-1; k >= randInt(0,4); k--) {
		gamefield[k][i] = randInt(1,2);
	    }
	}
    }
    
    private void printlineX() {
        System.out.println();
        for(int i = 0; i < gamefield[0].length+1; i++) {
            System.out.print("----");
        }
        
    }
    public int randInt(int min, int max) {
	Random rand = new Random();
	int randomNum = rand.nextInt((max - min) + 1) + min;
	return randomNum;
    }
    
    public void printResult(ArrayList<FourLine> lines) {
	for (FourLine line : lines) {
	    System.out.println(line.toString());
	}
    }
    
    
}
