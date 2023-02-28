package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

    @FXML
    private Text txtPlayer;
    @FXML
    private ImageView playerImage;
    @FXML
    private ImageView botImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getUserName();
    }

    @FXML
    private void handleRock(ActionEvent actionEvent) {
    }

    @FXML
    private void handlePaper(ActionEvent actionEvent) {
    }

    @FXML
    private void handleScissor(ActionEvent actionEvent) {
    }

    private void getUserName(){
        TextInputDialog getPlayerName = new TextInputDialog();
        getPlayerName.setTitle("Choose Name");
        getPlayerName.setHeaderText("Please enter your name:");
        getPlayerName.setContentText("Name:");
        Optional<String> result = getPlayerName.showAndWait();
        txtPlayer.setText(result.get());
    }
}
