package connectfour;

import java.util.ArrayList;

/**
 * @author sebastian.gohres@gmail.com
 * @author tim.giesenberg@me.com
 */
public class GameLogic {

    int[][] gamefield;

    public GameLogic(Grid grid) {
	this.gamefield = new int[grid.getRows()][grid.getColumns()];
    }

    /**
     * Checks gamegrid for four tokens in a line
     *
     * @return ArrayList List of lines found in grid
     */
    public ArrayList<FourLine> check() {
	ArrayList<FourLine> lines = new ArrayList<>();

	for (int zeile = 0; zeile < gamefield.length; zeile++) {
	    System.out.println("-----------------------------------------------------");
	    for (int spalte = 0; spalte < (gamefield[zeile].length); spalte++) {
		System.out.println("Spalte: " + spalte + " Zeile: " + zeile + " length: " + gamefield[zeile].length);
		if (gamefield[zeile][spalte] == 0) {
		    continue;
		}
		int p = gamefield[zeile][spalte];

		if (zeile <= (gamefield.length) - 4) {
		    // checks diagonal lines from top right to bottom left
		    System.out.println("if " + spalte + " > " + gamefield[zeile].length + "-4 ");
		    if (spalte >= 3) {
			if (p == gamefield[zeile + 1][spalte - 1] && p == gamefield[zeile + 2][spalte - 2] && p == gamefield[zeile + 3][spalte - 3]) {
			    lines.add(new FourLine(zeile, spalte, FourLine.BOTTOM_LEFT, p));
			}
		    }
		    // checks diagonal lines from top left to bottom right
		    if (spalte <= (gamefield[zeile].length) - 4) {
			if (p == gamefield[zeile + 1][spalte + 1] && p == gamefield[zeile + 2][spalte + 2] && p == gamefield[zeile + 3][spalte + 3]) {
			    lines.add(new FourLine(zeile, spalte, FourLine.BOTTOM_RIGHT, p));
			}
		    }
		    // checks vertical lines from top to bottom
		    if (p == gamefield[zeile + 1][spalte] && p == gamefield[zeile + 2][spalte] && p == gamefield[zeile + 3][spalte]) {
			lines.add(new FourLine(zeile, spalte, FourLine.BOTTOM, p));
		    }
		}
		// checks horizontal lines from left to right
		if (spalte <= (gamefield[zeile].length) - 4) {
		    if (p == gamefield[zeile][spalte + 1] && p == gamefield[zeile][spalte + 2] && p == gamefield[zeile][spalte + 3]) {
			lines.add(new FourLine(zeile, spalte, FourLine.RIGHT, p));
		    }
		}
	    }
	}

	return lines;
    }

    public int[][] getGameGrid() {
	return gamefield;
    }

    public int getNextRow(int column) {
	for (int zeile = gamefield.length - 1; zeile >= 0; zeile--) {
	    if (gamefield[zeile][column] == 0) {
		return zeile;
	    }
	}
	// column is full
	return -1;
    }

    public int getComputerMove() {
	double computerMoveDouble = Math.round(Math.random() * 7);
	int computerMove = (int) computerMoveDouble;
	return computerMove;
    }
}
