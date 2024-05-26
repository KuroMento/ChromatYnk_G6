package chromatynk.chromatynk_g6;

import chromatynk.chromatynk_g6.controller.Controller;
import chromatynk.chromatynk_g6.interpreter.LYnkConsole;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.prefs.Preferences;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private LYnkConsole console;

    /**
     * Constructor
     */
    public MainApp(){
        console = new LYnkConsole();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     *This method is called by launch() and sets the application's properties;
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Chromatynk G6 - v.0.1.0");
        initRootLayout();

    }

    /**
     * Add the FXML content on the stage and shows it
     */
    public void initRootLayout() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("view/Overview.fxml"));
            this.rootLayout = fxmlLoader.load();

            Controller controller = fxmlLoader.getController();
            controller.setMainApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(1000);
            primaryStage.setMinHeight(850);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void showSpeedPopUp(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainApp.class.getResource("view/SpeedPopUp.fxml"));
            AnchorPane pane = fxmlLoader.load();

            //Create the execution speed pop up
            Stage stage = new Stage();
            stage.setTitle("Edit Execution Speed");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            Controller controller = fxmlLoader.getController();
            controller.setSpeedPopup(stage);

            stage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

     /**
     *  Returns the path on the file selected by the user.
     *  @return the selected file or null if the file doesn't exist
     */
    public File getFilePath(){
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if(filePath != null){
            return new File(filePath);
        }else{
            return null;
        }
    }
    
    /**
     * Sets the file path.
     * Adds the file to the recent opened files
     * @param file
     */
    public void setFilePath(File file){
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if(file != null){
            prefs.put("filePath", file.getPath());
            primaryStage.setTitle("Chromatynk G6 -" + file.getName());
        }else {
            prefs.remove("filePath");

            primaryStage.setTitle("Chromatynk G6 - v.0.1.0");
        }
    }

    /**
     * This method is the getter of primaryStage.
     * @return primaryStage;
     */
    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    /**
     * This method is the setter of primaryStage
     * @param primaryStage
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
