package rps.gui.controller;

// Java imports
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

    @FXML
    private ImageView playerImage;
    @FXML
    private ImageView botImage;

    private Image rockPlayer = new Image("RockPlayer.png");
    private Image rockPlayerR10 = new Image("RockPlayerR10.png");
    private Image rockPlayerR20 = new Image("RockPlayerR20.png");
    private Image paperPlayer = new Image("RockPlayer.png");
    private Image scissorPlayer = new Image("ScissorPlayer.png");

    private Image rockBot = new Image("RockBot.png");
    private Image rockBotR10 = new Image("RockBotR10.png");
    private Image rockBotR20 = new Image("RockBotR20.png");
    private Image paperBot = new Image("RockBot.png");
    private Image scissorBot = new Image("ScissorBot.png");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    @FXML
    private void handleRock(ActionEvent actionEvent) {
        climax();
        playerImage.setImage(rockPlayer);
    }

    @FXML
    private void handlePaper(ActionEvent actionEvent) {
        climax();
        playerImage.setImage(paperPlayer);
    }

    @FXML
    private void handleScissor(ActionEvent actionEvent) {
        climax();
        playerImage.setImage(scissorPlayer);
    }

    private void climax(){

        Timeline timeline = new Timeline();
        List<Image> playerImages = List.of(rockPlayer, rockPlayerR10, rockPlayerR20, rockPlayerR10, rockPlayer);
        List<Image> botImages = List.of(rockBot, rockBotR10, rockBotR20, rockBotR10, rockBot);
        for (int i = 0; i < 4; i++){
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
}
