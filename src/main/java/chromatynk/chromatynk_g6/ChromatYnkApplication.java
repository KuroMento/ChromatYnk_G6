package chromatynk.chromatynk_g6;
import chromatynk.chromatynk_g6.interpreter.CursorManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ChromatYnkApplication extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private CursorManager cursorManager;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ChromatYnk_G6 - v.0.1.0");
        this.cursorManager = new CursorManager();

        initRootLayout();
        showCanvas();
        showInstructionField();
        showInterpreterField();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader fxmlLoader = new FXMLLoader(ChromatYnkApplication.class.getResource("view/RootLayout.fxml"));
            this.rootLayout = fxmlLoader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout,1000,800);
            this.primaryStage.setScene(scene);
            this.primaryStage.setMinHeight(800);
            this.primaryStage.setMinWidth(1000);

            this.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the Canvas and the History Console at the center of the root layout.
     */
    public void showCanvas() {
        try {
            // Load root layout from fxml file.
            FXMLLoader fxmlLoader = new FXMLLoader(ChromatYnkApplication.class.getResource("view/CanvasOverview.fxml"));
            BorderPane canvasHistory = fxmlLoader.load();

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
    public void showInstructionField() {
        try {
            // Load root layout from fxml file.
            FXMLLoader fxmlLoader = new FXMLLoader(ChromatYnkApplication.class.getResource("view/InstructionOverview.fxml"));
            BorderPane instructionField = fxmlLoader.load();

            // Set person overview into the center of root layout.
            this.rootLayout.setBottom(instructionField);

            // Give the controller access to the main app.
            // InterpreterFieldController controller = fxmlLoader.getController();
            // controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showInterpreterField(){
        try {
            //Load fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(ChromatYnkApplication.class.getResource("view/InterpreterOverview.fxml"));
            BorderPane interpreterField = fxmlLoader.load();

            this.rootLayout.setBottom(interpreterField);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
