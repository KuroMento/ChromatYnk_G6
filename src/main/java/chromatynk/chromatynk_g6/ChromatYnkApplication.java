package chromatynk.chromatynk_g6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ChromatYnkApplication extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ChromatYnk_G6 - v.0.1.0");

        initRootLayout();
        showCanvasHistory();
        showInterpreterField();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader fxmlLoader = new FXMLLoader(ChromatYnkApplication.class.getResource("RootLayout.fxml"));
            this.rootLayout = (BorderPane) fxmlLoader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout,1000,800);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the Canvas and the History Console at the center of the root layout.
     */
    public void showCanvasHistory() {
        try {
            // Load root layout from fxml file.
            FXMLLoader fxmlLoader = new FXMLLoader(ChromatYnkApplication.class.getResource("CanvasHistory.fxml"));
            SplitPane canvasHistory = (SplitPane) fxmlLoader.load();

            // Set person overview into the center of root layout.
            this.rootLayout.setCenter(canvasHistory);

            // Give the controller access to the main app.
            // CanvasHistoryController controller = fxmlLoader.getController();
            // controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the Interpreter at the bottom of the root layout.
     */
    public void showInterpreterField() {
        try {
            // Load root layout from fxml file.
            FXMLLoader fxmlLoader = new FXMLLoader(ChromatYnkApplication.class.getResource("InterpreterField.fxml"));
            AnchorPane interpreterField = (AnchorPane) fxmlLoader.load();

            // Set person overview into the center of root layout.
            this.rootLayout.setBottom(interpreterField);

            // Give the controller access to the main app.
            // InterpreterFieldController controller = fxmlLoader.getController();
            // controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
