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
public class Player {
    private int player;	    // Player id
    private int points;	    // Number of matches won
    private int color;	    // Players color (red = 1, yellow = 2);
    private int moves;	    // Number of moves played
    private string name;    // Players name
    
    public Player(int player, string name, int color) {
	this.player = player;
	this.name = name;
	this.points = 0;
	this.color = color;
	this.moves = 0;
	
    }
    
    public String getName() {
	return this.name;
    }
    
    public int getPoints() {
	return this.points;
    }
    
    public void increasePoints() {
	this.points++;
    }
    
    public void increaseMoves() {
	this.moves++;
    }
    
    public void setColor(int color) {
	this.color = color;
    }
    
    public int getColor() {
	return this.color;
    }
    

