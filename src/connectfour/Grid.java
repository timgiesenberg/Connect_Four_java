/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connectfour;



/**
 *
 * @author zeebo
 */
public class Grid {
    private int columns = 7;
    private int rows = 6;
    private int gamemode;

    public static final int GAMEMODE_LOCAL = 1;
    public static final int GAMEMODE_REMOTE = 2;
    
    public Grid(int rows, int columns, int gamemode) {
        
        this.gamemode = (gamemode == GAMEMODE_REMOTE) ? GAMEMODE_REMOTE : GAMEMODE_LOCAL;
        int min = (this.gamemode == GAMEMODE_LOCAL) ? 4 : 3;
        
        this.columns = (columns < min) ? min : columns;
        this.rows = (rows < min) ? min : rows;
    }
    
    public Grid setColumns(int columns) {
        int min = (this.gamemode == GAMEMODE_LOCAL) ? 4 : 3;
        this.columns = (columns < min) ? min : columns;
        return this;
    }
    
    public Grid setRows(int rows) {
        int min = (this.gamemode == GAMEMODE_LOCAL) ? 4 : 3;
        this.rows = (rows < min) ? min : rows;
        return this;
    }
    
    public void setGameMode(int gamemode) {
        this.gamemode = (gamemode == GAMEMODE_REMOTE) ? GAMEMODE_REMOTE : GAMEMODE_LOCAL;
    }
    
    public int getColumns() {
        return this.columns;
    }
    
    public int getRows() {
        return this.rows;
    }
    
    public int getGameMode() {
        return this.gamemode;
    }
}