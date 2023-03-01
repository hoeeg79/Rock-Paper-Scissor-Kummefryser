package rps.gui.controller;

// Java imports
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;

import javafx.scene.control.TextInputDialog;
import rps.bll.game.GameManager;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;

import java.util.Optional;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

    @FXML
    private Label lblWin;
    @FXML
    private Label lblBotWin;
    @FXML
    private Label txtPlayer;
    @FXML
    private ImageView playerImage;
    @FXML
    private ImageView botImage;

    private Image rockPlayer = new Image("RockPlayer.png");
    private Image rockPlayerR10 = new Image("RockPlayerR10.png");
    private Image rockPlayerR20 = new Image("RockPlayerR20.png");
    private Image paperPlayer = new Image("PaperPlayer.png");
    private Image scissorPlayer = new Image("ScissorPlayer.png");

    private Image rockBot = new Image("RockBot.png");
    private Image rockBotR10 = new Image("RockBotR10.png");
    private Image rockBotR20 = new Image("RockBotR20.png");
    private Image paperBot = new Image("PaperBot.png");

    private Image scissorBot = new Image("ScissorBot.png");
    private String playerName = "";
    private int playerWins = 0;
    private int botWins = 0;

    IPlayer human = new Player(playerName, PlayerType.Human);
    IPlayer bot = new Player("Kummefryser Bot 3000", PlayerType.AI);
    private GameManager gm = new GameManager(human, bot);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String playerName = getUserName();
        lblWin.setText(String.valueOf(playerWins));
        lblBotWin.setText(String.valueOf(botWins));
    }
    @FXML
    private void handleRock(ActionEvent actionEvent) {
        gm.playRound(Move.Rock);
        Result result = getLatestResult();

        if (result.getWinnerPlayer().getPlayerType() == PlayerType.AI){
            climax(rockPlayer, paperBot);
            botWin();
        } else if (result.getWinnerPlayer().getPlayerType() == PlayerType.Human) {
            climax(rockPlayer,scissorBot);
            playerWin();
        }else {
            climax(rockPlayer, rockBot);
        }
    }

    @FXML
    private void handlePaper(ActionEvent actionEvent) {
        gm.playRound(Move.Paper);
        Result result = getLatestResult();

        if (result.getWinnerPlayer().getPlayerType() == PlayerType.AI){
            climax(paperPlayer, scissorBot);
            botWin();
        } else if (result.getWinnerPlayer().getPlayerType() == PlayerType.Human) {
            climax(paperPlayer,rockBot);
            playerWin();
        }else {
            climax(paperPlayer, paperBot);
        }
    }

    @FXML
    private void handleScissor(ActionEvent actionEvent) throws InterruptedException {
        gm.playRound(Move.Scissor);
        Result result = getLatestResult();

        if (result.getWinnerPlayer().getPlayerType() == PlayerType.AI){
            climax(scissorPlayer, rockBot);
            botWin();
        } else if (result.getWinnerPlayer().getPlayerType() == PlayerType.Human) {
            climax(scissorPlayer,paperBot);
            playerWin();
        }else {
            climax(scissorPlayer, scissorBot);
        }
    }

    private Result getLatestResult(){
        List<Result> listResult = (List) gm.getGameState().getHistoricResults();
        Result result = listResult.get(listResult.size() - 1);

        return result;
    }

    private void climax(Image chosenPlay, Image botPlay){

        Timeline timeline = new Timeline();
        List<Image> playerImages = List.of(rockPlayer, rockPlayerR10, rockPlayerR20, rockPlayerR10, chosenPlay);
        List<Image> botImages = List.of(rockBot, rockBotR10, rockBotR20, rockBotR10, botPlay);
        for (int i = 0; i < 5; i++){
            timeline.getKeyFrames().add(
                    new KeyFrame(
                            Duration.millis(i * 200),
                            new KeyValue(playerImage.imageProperty(), playerImages.get(i)),
                            new KeyValue(botImage.imageProperty(), botImages.get(i))
                    )
            );
        }
        timeline.setCycleCount(3);
        timeline.play();
    }

    private String getUserName(){
        TextInputDialog getPlayerName = new TextInputDialog();
        getPlayerName.setTitle("Choose Name");
        getPlayerName.setHeaderText("Please enter your name:");
        getPlayerName.setContentText("Name:");
        Optional<String> result = getPlayerName.showAndWait();
        txtPlayer.setText(result.get());
        if(!result.get().isEmpty()){
            txtPlayer.setText(result.get());
            return result.get();
        }else{
            txtPlayer.setText("Spejderen");
            return "Spejderen";
        }
    }

    public String getResultAsString(Result result) {
        String statusText = result.getType() == ResultType.Win ? "wins over " : "ties ";

        return "Round #" + result.getRoundNumber() + ":" +
                result.getWinnerPlayer().getPlayerName() +
                " (" + result.getWinnerMove() + ") " +
                statusText + result.getLoserPlayer().getPlayerName() +
                " (" + result.getLoserMove() + ")!";

    }
    private void botWin(){
        botWins++;
        lblBotWin.setText(String.valueOf(botWins));
    }

    private void playerWin(){
        playerWins++;
        lblWin.setText(String.valueOf(playerWins));
    }
}
