package chromatynk.chromatynk_g6.controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class RootController {
    private Stage primaryStage;

    @FXML
    private TextArea textArea;

    @FXML
    private MenuItem exitItem;

    @FXML
    private void openFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null){
            //display text file
        }
    }

    @FXML
    private void exitApplication(){
        System.exit(0);
    }
}
