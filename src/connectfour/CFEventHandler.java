/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connectfour;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Zeebo
 */
public class CFEventHandler implements EventHandler {

    ConnectFour cf;
    GameLogic gl;
    
    CFEventHandler(ConnectFour aThis) {
	cf = aThis;
    }

    @Override
    public void handle(Event event) {
	Object source = event.getSource();
	if(source.getClass().getSimpleName().equals("Rectangle")) {
	    Rectangle rect = (Rectangle)source;
	    if(rect.getId().equals("clickarea")) {
		int clickedCol;
		clickedCol = getClickedCol((MouseEvent)event, rect);
		System.out.println(clickedCol);
	    }
	}
	//System.err.println("Not supported yet.");
    }
    private int getClickedCol(MouseEvent me, Rectangle rect) {
	    double colWidth = 50;
	    double clickX = 0.0;
	    int clickedCol = -1;
	    GridPane gp = null;
	    clickX = me.getX()-rect.getX();
            clickedCol = (int) Math.floor(clickX / colWidth);
            System.out.println("angeklickte Spalte: " + clickedCol + " " +clickX);
	    
            me.consume();
            
	    
	    
	    int row = gl.getNextRow(clickedCol);
	    gl.gamefield[row][clickedCol] = 1;
	    cf.drawToken(row, clickedCol, 1);
	    
	    return clickedCol;
	    
	    
	    
	}
    }
    
    

