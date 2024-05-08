package chromatynk.chromatynk_g6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChromatYnkApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("root.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 360);
        primaryStage.setTitle("ChromatYnk_G6");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
