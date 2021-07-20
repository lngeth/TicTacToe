package Model;

/**
 * Player class contains characteristics of players
 * @author lngeth
 */
public class Player {
    private String name;
    private String pattern;
    private int score;

    /**
     * Constructor of Player
     * @param name name of the player
     * @param pattern the sign used to identify the player's move
     */
    public Player(String name, String pattern){
        this.name = name;
        this.pattern = pattern;
        this.score = 0;
    }

    /**
     * Get the score of the player
     * @return the score of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * Increment by one the player's score
     */
    public void addScore(){
        this.score++;
    }
}
