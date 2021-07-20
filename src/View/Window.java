package View;

import Control.ControlMenuButton;
import Control.CanvasControl;
import Model.Game;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * The Window class is the view for the TicTacToe game.
 * It contains all the variable and method for the visual of the game.
 * @author lngeth
 */
public class Window extends Application {
    private Stage primaryStage;
    private TextField player1Field;
    private TextField player2Field;
    private Label player1Name;
    private Label player2Name;
    private Label turnToLabel;

    public Group root;
    public Game game;
    public Canvas canvas;
    public GraphicsContext gc;

    private ControlMenuButton controlMenuButton;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initLauncher();
        createLauncher();
        primaryStage.show();
    }

    /** Get the TextField of Player1
     * @return TextField
     */
    public TextField getPlayer1Field() {
        return player1Field;
    }

    /** Get the TextField of Player1
     * @return TextField
     */
    public TextField getPlayer2Field() {
        return player2Field;
    }

    /** Set the text of the label on top of canvas
     * @param text String information to set for the game
     */
    public void setTextTurnToLabel(String text) {
        this.turnToLabel.setText(text);
    }

    /**
     * Init the component of the launcher
     */
    public void initLauncher(){
        this.root = new Group();
        this.player1Field = new TextField();
        this.player1Field.setPromptText("Add the player 1's name");
        this.player1Field.setFont(new Font(20));
        this.player2Field = new TextField();
        this.player2Field.setPromptText("Add the player 2's name");
        this.player2Field.setFont(new Font(20));

        this.controlMenuButton = new ControlMenuButton(this);
    }

    /**
     * Create the launcher by adding all the component in the Stage
     */
    public void createLauncher() {
        Scene scene = new Scene(this.root,500,500);
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("TicTacToe");
        this.primaryStage.setResizable(false);
        this.primaryStage.getIcons().add(new Image("images/tic-tac-toe.png"));

        Text player1Text = new Text("Player 1 : ");
        Text player2Text = new Text("Player 2 : ");

        HBox player1HBox = new HBox();
        player1HBox.setAlignment(Pos.CENTER);
        player1HBox.setSpacing(10);
        player1HBox.getChildren().addAll(player1Text,player1Field);

        HBox player2HBox = new HBox();
        player2HBox.setAlignment(Pos.CENTER);
        player2HBox.setSpacing(10);
        player2HBox.getChildren().addAll(player2Text,player2Field);

        Button submitNameButton = new Button("Play");
        submitNameButton.setId("submitName");
        submitNameButton.setOnAction(this.controlMenuButton);
        VBox playerNameVBox = new VBox();
        playerNameVBox.setAlignment(Pos.CENTER);
        playerNameVBox.setSpacing(10);
        playerNameVBox.getChildren().addAll(player1HBox, player2HBox, submitNameButton);

        ImageView ticTacToeImage = new ImageView(new Image("images/tic-tac-toe.png",80,80,true,true));

        Text title = new Text("Tic Tac Toe");
        title.setFont(Font.font(50));
        VBox titleVBox = new VBox();
        titleVBox.setAlignment(Pos.CENTER);
        titleVBox.getChildren().addAll(ticTacToeImage, title);

        VBox mainVBox = new VBox();
        mainVBox.setSpacing(50);
        mainVBox.setMinWidth(500);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.getChildren().addAll(titleVBox, playerNameVBox);

        this.root.getChildren().add(mainVBox);
    }

    /**
     * Init the component for the game
     */
    public void initGame(){
        this.root.getChildren().clear();
        this.game = new Game(player1Field.getText(), player2Field.getText());
        this.player1Name = new Label(this.player1Field.getText() + " : " + this.game.getPlayer1().getScore());
        this.player2Name = new Label(this.player2Field.getText() + " : " + this.game.getPlayer2().getScore());
        this.canvas = new Canvas(300,300);
        this.gc = this.canvas.getGraphicsContext2D();
        if (this.game.isTurnTo()){
            this.turnToLabel = new Label("Turn to " + this.player1Field.getText());
        } else {
            this.turnToLabel = new Label("Turn to " + this.player2Field.getText());
        }
        this.turnToLabel.setFont(new Font(20));
    }

    /**
     * Create all the component of the game in the Stage
     */
    public void createGame(){
        drawGridCanvas();

        CanvasControl canvasControl = new CanvasControl(this);
        this.canvas.setOnMouseClicked(canvasControl);

        MenuItem newGame = new MenuItem("New Game");
        newGame.setOnAction(this.controlMenuButton);
        newGame.setId("newGame");
        MenuItem resetRound = new MenuItem("Reset Round");
        resetRound.setOnAction(this.controlMenuButton);
        resetRound.setId("resetRound");
        MenuItem changePlayer = new MenuItem("Change Player");
        changePlayer.setOnAction(this.controlMenuButton);
        changePlayer.setId("changePlayer");
        Menu optionMenu = new Menu("options");
        optionMenu.getItems().addAll(newGame, resetRound, changePlayer);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(optionMenu);

        HBox player1Box = new HBox();
        player1Box.setAlignment(Pos.CENTER_LEFT);
        player1Box.getChildren().add(this.player1Name);
        HBox player2Box = new HBox();
        player2Box.setAlignment(Pos.CENTER_RIGHT);
        player2Box.getChildren().add(this.player2Name);

        Label scoreTitle = new Label("Score");
        scoreTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        HBox bottomPane = new HBox(player1Box, scoreTitle, player2Box);
        bottomPane.setAlignment(Pos.CENTER);
        HBox.setHgrow(player1Box, Priority.ALWAYS);
        HBox.setHgrow(player2Box, Priority.ALWAYS);
        HBox.setHgrow(scoreTitle, Priority.ALWAYS);
        bottomPane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
                BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
                CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));

        VBox gameGridVBox = new VBox(this.turnToLabel, this.canvas);
        gameGridVBox.setAlignment(Pos.CENTER);
        gameGridVBox.setSpacing(30);

        BorderPane gamePane = new BorderPane();
        gamePane.setMinWidth(500);
        gamePane.setMinHeight(500);
        gamePane.setBottom(bottomPane);
        gamePane.setCenter(gameGridVBox);
        gamePane.setTop(menuBar);

        this.root.getChildren().add(gamePane);
    }

    /**
     * Draw lines of the TicTacToe game into the Canvas
     */
    public void drawGridCanvas(){
        this.gc.strokeLine(100,0,100,300);
        this.gc.strokeLine(200,0,200,300);
        this.gc.strokeLine(0,100,300,100);
        this.gc.strokeLine(0,200,300,200);
    }

    /**
     * Reset a new game, all scores are reset
     */
    public void newGame(){
        this.gc.clearRect(0,0,300,300);
        drawGridCanvas();
        this.game = new Game(player1Field.getText(), player2Field.getText());
        if (this.game.isTurnTo()){
            this.turnToLabel.setText("Turn to " + this.player1Field.getText());
        } else {
            this.turnToLabel.setText("Turn to " + this.player2Field.getText());
        }
    }

    /**
     * Reset the grid of the actual round
     */
    public void resetRound(){
        this.gc.clearRect(0,0,300,300);
        drawGridCanvas();
        this.game.getGrid().resetGrid();
        this.game.randomBool();
        if (this.game.isTurnTo()){
            this.turnToLabel.setText("Turn to " + this.player1Field.getText());
        } else {
            this.turnToLabel.setText("Turn to " + this.player2Field.getText());
        }
        this.game.setSession(true);
    }

    /**
     * Create a Dialog window with information about the end of the round
     * @param playerWon the player who won the round
     * @param isAWinner true if there is a winner, false if it is a draw
     */
    public void createRoundFinishAlert(String playerWon, boolean isAWinner){
        Alert finishAlert = new Alert(Alert.AlertType.INFORMATION);
        finishAlert.setTitle("End of Round");
        if (isAWinner){
            finishAlert.setHeaderText(playerWon + " has won!");
            finishAlert.setContentText("+ 1 point");
        } else {
            finishAlert.setHeaderText("It is a draw!");
            finishAlert.setContentText("No point assigned.");
        }
        finishAlert.getButtonTypes().clear();

        ButtonType nextRound = new ButtonType("Next round");
        finishAlert.getButtonTypes().add(nextRound);
        Optional<ButtonType> option = finishAlert.showAndWait();
        if (option.get() == nextRound){
            this.resetRound();
        }
    }

    /**
     * Update the score in the visual interface
     */
    public void updateScore(){
        this.player1Name.setText(this.player1Field.getText() + " : " + this.game.getPlayer1().getScore());
        this.player2Name.setText(this.player2Field.getText() + " : " + this.game.getPlayer2().getScore());
    }

    /**
     * Main used to launch start method of Application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
