package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
}
