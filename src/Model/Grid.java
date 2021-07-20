package Model;

/**
 * Grid class contains the 2D String array of the TicTacToe game.
 * @author lngeth
 */
public class Grid {
    private String[][] tab;

    /**
     * Constructor of Grid class
     */
    public Grid(){
        this.tab = new String[3][3];
    }

    /**
     * Get the 2D array of the game
     * @return 2D String array of the TicTacToe board
     */
    public String[][] getTab() {
        return tab;
    }

    /**
     * Set the move of the player
     * @param pattern the symbol of the player
     * @param x the column
     * @param y the line
     */
    public void setMove(String pattern, int x, int y){
        this.tab[x][y] = pattern;
    }

    /**
     * Check if the box selected is not already drawn
     * @param x the column
     * @param y the line
     * @return true if available box, false if not
     */
    public boolean isValidCase(int x, int y){
        return this.tab[x][y] == null;
    }

    /**
     * Reset the 2D arrays of the game
     */
    public void resetGrid(){
        this.tab = new String[3][3];
    }
}
