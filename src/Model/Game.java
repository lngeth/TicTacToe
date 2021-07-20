package Model;

/**
 * Game class contains the setting of the TicTacToe game
 * @author lngeth
 */
public class Game {
    private boolean session;
    private Grid grid;
    private Player player1;
    private Player player2;
    private boolean turnTo;

    /**
     * Constructor of the Game class
     * @param name1 First player's name
     * @param name2 Second player's name
     */
    public Game(String name1, String name2){
        this.session = true;
        this.grid = new Grid();
        this.player1 = new Player(name1, "x");
        this.player2 = new Player(name2, "o");
        randomBool();
    }

    /**
     * Check if the actual session is on
     * @return true if on, false if not
     */
    public boolean isSession() {
        return session;
    }

    /**
     * Set the session to a specific state
     * @param session true if the session is on, false if not
     */
    public void setSession(boolean session) {
        this.session = session;
    }

    /**
     * Get the 2D array of the game
     * @return the Grid
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * Get the Player 1
     * @return the Player
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Get the Player 2
     * @return the Player
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Check which Player's turn is to play
     * @return true if it is the Player 1's turn, false if the second's turn
     */
    public boolean isTurnTo() {
        return turnTo;
    }

    /**
     * Set the turn to a specific Player
     * @param turnTo true if it is the Player 1's turn, false if the second's turn
     */
    public void setTurnTo(boolean turnTo) {
        this.turnTo = turnTo;
    }

    /**
     * Method to generate a random boolean
     */
    public void randomBool(){
        this.turnTo = Math.random() < 0.5;
    }

    /**
     * Increment a point to Player 1
     */
    public void addPointToPlayer1(){
        this.player1.addScore();
    }

    /**
     * Increment a point to Player 2
     */
    public void addPointToPlayer2(){
        this.player2.addScore();
    }
}