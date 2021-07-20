package Control;

import View.Window;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * ControlMenuButton is a Controller class used to manage interaction between menu and the user.
 * It is used to connect the component of the view with the Game setting.
 * @author lngeth
 */
public class ControlMenuButton implements EventHandler {
    private final Window WINDOW;

    public ControlMenuButton(Window window){
        this.WINDOW = window;
    }

    /**
     * Specifies each action to do for a specific component clicked on
     * @param event onClick event
     */
    @Override
    public void handle(Event event) {
        String sourceId;
        if (event.getSource() instanceof Button){
            sourceId = ((Button) event.getSource()).getId();
        } else {
            sourceId = ((MenuItem) event.getSource()).getId();
        }
        switch (sourceId){
            case "submitName":
                if (this.WINDOW.getPlayer1Field().getText().equals("") || this.WINDOW.getPlayer2Field().getText().equals("")){
                    if (this.WINDOW.getPlayer1Field().getText().equals("")){
                        this.WINDOW.getPlayer1Field().setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(4), Insets.EMPTY)));
                    } else {
                        this.WINDOW.getPlayer1Field().setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, new CornerRadii(4), Insets.EMPTY)));
                    }
                    if (this.WINDOW.getPlayer2Field().getText().equals("")){
                        this.WINDOW.getPlayer2Field().setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(4), Insets.EMPTY)));
                    } else {
                        this.WINDOW.getPlayer2Field().setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, new CornerRadii(4), Insets.EMPTY)));
                    }
                } else {
                    this.WINDOW.initGame();
                    this.WINDOW.createGame();
                }
                break;
            case "newGame":
                this.WINDOW.newGame();
                this.WINDOW.updateScore();
                break;
            case "resetRound":
                this.WINDOW.resetRound();
                break;
            case "changePlayer":
                this.WINDOW.root.getChildren().clear();
                this.WINDOW.initLauncher();
                this.WINDOW.createLauncher();
        }
    }
}
