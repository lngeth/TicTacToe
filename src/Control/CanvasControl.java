package Control;

import Model.GridTools;
import View.Window;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * CanvasControl is a class used to make connection between the Game and the user.
 * It use MouseEvent to detect the user's click on the canvas and the class has a direct access to the View class.
 * @author lngeth
 */
public class CanvasControl implements EventHandler<MouseEvent> {
    private final Window WINDOW;

    /**
     * Constructor of the class. Used to control interaction between user clicking and the game
     * @param window the View to display
     */
    public CanvasControl(Window window){
        this.WINDOW = window;
    }

    /**
     * Determine each action to do when user click on certain area of the canvas
     * @param mouseEvent mouse click event
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        if (this.WINDOW.game.isSession()){
            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();
            int canvasWidth = (int) this.WINDOW.canvas.getWidth();
            int canvasHeight = (int) this.WINDOW.canvas.getHeight();
            int col = 0;
            int line = 0;
            Image imageToDraw;

            if (x < canvasWidth/3){
                if (y < canvasHeight/3){
                    col = 0;
                    line = 0;
                } else if (y < 2*canvasHeight/3) {
                    col = 0;
                    line = 1;
                } else if (y < canvasHeight) {
                    col = 0;
                    line = 2;
                }
            } else if (x < 2*canvasWidth/3) {
                if (y < canvasHeight/3){
                    col = 1;
                    line = 0;
                } else if (y < 2*canvasHeight/3) {
                    col = 1;
                    line = 1;
                } else if (y < canvasHeight) {
                    col = 1;
                    line = 2;
                }
            } else if (x < canvasWidth) {
                if (y < canvasHeight/3){
                    col = 2;
                    line = 0;
                } else if (y < 2*canvasHeight/3) {
                    col = 2;
                    line = 1;
                } else if (y < canvasHeight) {
                    col = 2;
                    line = 2;
                }
            }

            if (this.WINDOW.game.getGrid().isValidCase(col,line)){
                if (this.WINDOW.game.isTurnTo()){
                    imageToDraw = new Image("images/x.png", 100, 100, true, true);
                    this.WINDOW.game.getGrid().setMove("x", col, line);
                    this.WINDOW.gc.drawImage(imageToDraw,col*100,line*100,100,100);

                    if (GridTools.isWinning(this.WINDOW.game.getGrid())){
                        this.WINDOW.game.setSession(false);
                        this.WINDOW.game.addPointToPlayer1();
                        this.WINDOW.updateScore();
                        this.WINDOW.createRoundFinishAlert(this.WINDOW.getPlayer1Field().getText(), true);
                    } else if (GridTools.isPat(this.WINDOW.game.getGrid())) {
                        this.WINDOW.game.setSession(false);
                        this.WINDOW.createRoundFinishAlert(this.WINDOW.getPlayer1Field().getText(), false);
                    } else {
                        this.WINDOW.game.setTurnTo(false);
                        this.WINDOW.setTextTurnToLabel("Turn to "+ this.WINDOW.getPlayer2Field().getText());
                    }
                } else {
                    imageToDraw = new Image("images/o.png", 100, 100, true, true);
                    this.WINDOW.game.getGrid().setMove("o", col, line);
                    this.WINDOW.gc.drawImage(imageToDraw,col*100,line*100,100,100);

                    if (GridTools.isWinning(this.WINDOW.game.getGrid())){
                        this.WINDOW.game.setSession(false);
                        this.WINDOW.game.addPointToPlayer2();
                        this.WINDOW.updateScore();
                        this.WINDOW.createRoundFinishAlert(this.WINDOW.getPlayer2Field().getText(), true);
                    } else if (GridTools.isPat(this.WINDOW.game.getGrid())) {
                        this.WINDOW.game.setSession(false);
                        this.WINDOW.createRoundFinishAlert(this.WINDOW.getPlayer2Field().getText(), false);
                    } else {
                        this.WINDOW.game.setTurnTo(true);
                        this.WINDOW.setTextTurnToLabel("Turn to "+ this.WINDOW.getPlayer1Field().getText());
                    }
                }
            } else {
                this.WINDOW.setTextTurnToLabel("You can't play this move! Please try again.");
            }
        } else {
            this.WINDOW.setTextTurnToLabel("The game is over.");
        }
    }
}
