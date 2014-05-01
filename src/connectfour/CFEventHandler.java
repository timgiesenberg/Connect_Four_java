/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour;

import java.util.ArrayList;
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
	if (source.getClass().getSimpleName().equals("Rectangle")) {
	    Rectangle rect = (Rectangle) source;
	    if (rect.getId().equals("clickarea")) {
		int clickedCol;
		clickedCol = getClickedCol((MouseEvent) event, rect);
		int row = gl.getNextRow(clickedCol);
		if (row >= 0) {
		    Player p = cf.getActivePlayer();
		    gl.gamefield[row][clickedCol] = p.getId();
		    cf.drawToken(row, clickedCol, p.getId(), p.getColor());
		    cf.togglePlayer();
		    ArrayList lines = (ArrayList) gl.check();
		    if (lines.size() > 0) {
			System.out.println("PLAYER " + p.getName() + "WINS");
		    }
		}
	    }
	}
    }

    private int getClickedCol(MouseEvent me, Rectangle rect) {
	double colWidth = 50;
	double clickX;
	int clickedCol;

	GridPane gp = null;
	clickX = me.getX() - rect.getX();
	clickedCol = (int) Math.floor(clickX / colWidth);
	me.consume();
	return clickedCol;
    }
}
