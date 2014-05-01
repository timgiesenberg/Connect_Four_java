/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour;

import javafx.scene.paint.Color;

/**
 *
 * @author Zeebo
 */
public class Player {

    private int player;	    // Player id
    private int points;	    // Number of matches won
    private Color color;	    // Players color (red = 1, yellow = 2);
    private int moves;	    // Number of moves played
    private String name;    // Players name

    public Player(int player, String name, Color color) {
	this.player = player;
	this.name = name;
	this.points = 0;
	this.color = color;
	this.moves = 0;

    }

    public int getId() {
	return this.player;
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

    public void setColor(Color color) {
	this.color = color;
    }

    public Color getColor() {
	return this.color;
    }

}
