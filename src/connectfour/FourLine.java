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

/**
 * Directions
 * 
 *            
 *    O -       1
 *  / | \   2 3 4
 * 
 */

public class FourLine {
    private int originRow;
    private int originCol;
    private int direction;
    private int player;
    
    public static int RIGHT = 1;
    public static int BOTTOM = 3;
    public static int BOTTOM_LEFT = 2;
    public static int BOTTOM_RIGHT = 4;
    
    public FourLine(int originRow, int originCol, int direction, int player) {
	this.originCol = originCol;
	this.originRow = originRow;
	this.direction = direction;
	this.player = player;
    }
    
    public String toString() {
	return "Player " + this.player + " makes a line starting at point " + this.originRow + "/" + originCol;
    }
    
}