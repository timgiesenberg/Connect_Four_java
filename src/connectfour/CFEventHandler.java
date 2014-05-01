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
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog.Actions;
import org.controlsfx.dialog.Dialogs;

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
			Action response = Dialogs.create().actions(Actions.NO, Actions.YES)
				.owner(cf.root)
				.title("Congratulation!")
				.masthead(p.getName() + " wins the game!")
				.message("Would you like to play again?")
				.showConfirm();
			if (response == Actions.NO) {
			    cf.stage.close();
			} else {
			    cf.deleteGrid();
			    cf.drawGrid();
			}
		    }

		    Test t = new Test(gl.gamefield);
		    t.printGrid();

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
