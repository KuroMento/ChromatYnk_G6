package chromatynk.chromatynk_g6.controller;

import chromatynk.chromatynk_g6.MainApp;
import chromatynk.chromatynk_g6.diagnostic.LYnkIssue;
import chromatynk.chromatynk_g6.exceptions.NegativeNumberException;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.CursorAlreadyExistingException;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.CursorException;
import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;
import chromatynk.chromatynk_g6.interpreter.CursorManager;
import chromatynk.chromatynk_g6.interpreter.LYnkConsole;
import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.statements.*;
import chromatynk.chromatynk_g6.statements.Statement;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class Controller {

    @FXML
    private Canvas canvas;

    @FXML
    private ListView<String> listView;

    @FXML
    private TextArea inputArea;

    @FXML
    private Pane pane;

    @FXML
    private TextFlow  historyFlow;


    @FXML
    private MenuItem  saveItem;

    @FXML
    private MenuItem  saveAsItem;

    @FXML MenuItem  exportItem;

    private double stepperSpeed;

    private MainApp mainApp;

    private boolean isCanvaModified = false;

    private Stage speedPopup;

    private double widthImage;

    private double heightImage;

    private double currentPress;

    @FXML
    private TextField speedField;

    private ImageView imageView;

    private Image imageCursor;

    private LYnkConsole console;

    private CursorManager cursorManager;

    private LYnkVariableImpl varContext = new LYnkVariableImpl();


    private final String[] instructionsList = {"FWD", "BWD", "TURN", "MOV", "POS", "HIDE", "SHOW", "PRESS", "COLOR", "THICK",
            "LOOKAT", "CURSOR", "SELECT", "REMOVE", "IF", "FOR", "WHILE", "MIMIC", "MIRROR", "NUM", "STR", "BOOL",
            "DEL"};

    /**
     * mainApp is our starting point.
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setSpeedPopup(Stage speedPopup) {
        this.speedPopup = speedPopup;
    }

    /**
     * Constructor
     */
    public Controller(){
        this.currentPress = 1;
        this.imageCursor = new Image("file:src/main/resources/chromatynk/chromatynk_g6/cursor.png", 32, 32, true, true);
        this.imageView = new ImageView(imageCursor);
        this.listView = new ListView<String>();
        this.pane = new Pane();
        this.historyFlow = new TextFlow();
        this.speedField = new TextField();

        this.commandList = new ArrayList<>();
        this.statementBuffer = new ArrayList<>();

        this.cursorManager = new CursorManager();
        this.console = new LYnkConsole();

        this.widthImage = imageView.getImage().getWidth()/2; //change how the cursor image is centered around the drawing
        this.heightImage = imageView.getImage().getHeight()/2;
    }

    /**
     * Initialize the list of command and draw a cursor on origin
     */
    @FXML
    public void initialize() {
        listView.getItems().addAll(instructionsList);
        listView.getItems().sort(String::compareTo);

        pane.widthProperty().addListener((observable, oldValue, newValue) -> {
            canvas.setWidth(newValue.doubleValue());
            this.console.setWidthAndHeight((int) this.canvas.getWidth(), (int) this.canvas.getHeight());
        });

        pane.heightProperty().addListener((observable, oldValue, newValue) -> {
            canvas.setHeight(newValue.doubleValue());
            this.console.setWidthAndHeight((int) this.canvas.getWidth(), (int) this.canvas.getHeight());
        });

        pane.getChildren().add(imageView);
        drawCursorImage(0,0);
        setStepperSpeed(1);
        showSpeedExecution();
    }

    /**
     * Print a prompt Text on the textArea
     */
    @FXML
    private void selectCommand(){
        String command = listView.getSelectionModel().getSelectedItem();
        if (command != null) {
            switch (command){
                case "FWD":
                    inputArea.setPromptText("FWD value [%]");
                    break;

                case "BWD":
                    inputArea.setPromptText("BWD value [%]");
                    break;

                case "TURN":
                    inputArea.setPromptText("TURN value");
                    break;

                case "MOV":
                    inputArea.setPromptText("MOV x [%] y [%] ");
                    break;

                case "POS":
                    inputArea.setPromptText("POS x [%] y [%]");
                    break;

                case "HIDE":
                    inputArea.setPromptText("HIDE");
                    break;

                case "SHOW":
                    inputArea.setPromptText("SHOW");
                    break;

                case "PRESS":
                    inputArea.setPromptText("PRESS value [%]");
                    break;

                case "COLOR":
                    inputArea.setPromptText("COLOR #RRGGBB \n COLOR red green blue");
                    break;

                case "THICK":
                    inputArea.setPromptText("THICK value");
                    break;

                case "LOOKAT":
                    inputArea.setPromptText("LOOKAT cursorID \n LOOKAT x [%] y [%]");
                    break;

                case "CURSOR":
                    inputArea.setPromptText("CURSOR cursorID ");
                    break;

                case "SELECT":
                    inputArea.setPromptText("SELECT cursorID");
                    break;

                case "REMOVE":
                    inputArea.setPromptText("REMOVE cursorID");
                    break;

                case "IF":
                    inputArea.setPromptText("IF boolean {}");
                    break;

                case "FOR":
                    inputArea.setPromptText("FOR  name [FROM v1] TO v2 [STEP v3] {}");
                    break;

                case "WHILE":
                    inputArea.setPromptText("WHILE boolean {}");
                    break;

                case "MIMIC":
                    inputArea.setPromptText("MIMIC cursorID {}");
                    break;

                case "MIRROR":
                    inputArea.setPromptText("MIRROR  x1[%], y1[%], x2[%], y2[%] {} \n MIRROR  x1[%], y1[%] {}");
                    break;

                case "NUM":
                    inputArea.setPromptText("NUM name [= value]");
                    break;

                case "STR":
                    inputArea.setPromptText("STR name [= value]");
                    break;

                case "BOOL":
                    inputArea.setPromptText("BOOL name [= value]");
                    break;

                case "DEL":
                    inputArea.setPromptText("DEL name ");
                    break;

                default:
                    inputArea.setPromptText("Enter your instructions here");
                    break;
            }
        }
    }

    /**
     * Run one command
     * @param command Statement describing the command
     */
    public void nextCommand(Statement command) {
        switch (command.getKeyword()) {
            case "FWD":
                double value = ((ForwardStatement) command).getExpression().evaluate();
                showInfo("Forward: " + value + "px" + "\n");
                cursorManager.getSelectedCursor().forward(value);
                drawLine(cursorManager.getSelectedCursor().getPosX(),cursorManager.getSelectedCursor().getPosY());
                long id = cursorManager.getSelectedCursorId();
                for(Cursor mimicCursor : cursorManager.getSelectedCursor().getMimics()){
                    select(mimicCursor.getId());
                    cursorManager.getSelectedCursor().forward(value);
                    drawLine(mimicCursor.getPosX(), mimicCursor.getPosY());
                }
                for(Cursor mirrorCursor : cursorManager.getSelectedCursor().getMirror()){
                    select(mirrorCursor.getId());
                    cursorManager.getSelectedCursor().forward(value);
                    drawLine(mirrorCursor.getPosX(), mirrorCursor.getPosY());
                }
                select(id);
                break;
            case "BWD":
                cursorManager.forward(-((BackwardStatement) command).getExpression().evaluate());
                break;
            case "TURN":
                cursorManager.turn((float) ((RotationStatement) command).getExpression().evaluate());
                break;
            case "MOV":
                cursorManager.move((int) ((MoveStatement) command).getX().evaluate(), (int) ((MoveStatement) command).getY().evaluate());
                break;
            case "POS":
                cursorManager.position((int) ((MoveStatement) command).getX().evaluate(), (int) ((MoveStatement) command).getY().evaluate());
                break;
            case "HIDE":
                showInfo("Hiding selected cursor: " + cursorManager.getSelectedCursor() + "\n");
                cursorManager.hide();
                break;
            case "SHOW":
                showInfo("Showing selected cursor: " + cursorManager.getSelectedCursor() + "\n");
                cursorManager.show();
                break;
            case "PRESS":
                showInfo("Pressing cursor n°" + cursorManager.getSelectedCursor() + " : " + ((PressStatement) command).getExpression().evaluate() + "\n" );
                cursorManager.press(((PressStatement) command).getExpression().evaluate());
                break;
            case "COLOR":
                ColorStatement color = ((ColorStatement) command);
                if (color.isHexCode()) {
                    String hexCode = color.getHexcode();
                    int resultRed = Integer.valueOf(hexCode.substring(0, 2), 16);
                    int resultGreen = Integer.valueOf(hexCode.substring(2, 4), 16);
                    int resultBlue = Integer.valueOf(hexCode.substring(4, 6), 16);
                    cursorManager.color(new Color(resultRed, resultGreen, resultBlue, cursorManager.getSelectedCursor().getOpacity()));
                    break;
                }
                if (color.isRGB()) {
                    cursorManager.color(new Color(color.getRed().evaluate(), color.getGreen().evaluate(), color.getGreen().evaluate(), cursorManager.getSelectedCursor().getOpacity()));
                    break;
                }
                if (color.isHSV()) {
                    double[] rgb = hsvToRgb(color.getRed().evaluate(), color.getGreen().evaluate(), color.getBlue().evaluate());
                    cursorManager.color(new Color(rgb[0], rgb[1], rgb[2], cursorManager.getSelectedCursor().getOpacity()));
                    break;
                }
                break;
            case "THICK":
                try {
                    cursorManager.thick((float) ((ThickStatement) command).getExpression().evaluate());
                } catch (NegativeNumberException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "LOOKAT":
                LookAtStatement look = ((LookAtStatement) command);
                if (look.isIdLookAt()) {
                    cursorManager.lookAt(cursorManager.getCursors().get(look.getCursorId()).getPosX(), cursorManager.getCursors().get(look.getCursorId()).getPosY());
                    showInfo("Looking at cursor n°" + look.getCursorId() + "\n");
                } else {
                    cursorManager.lookAt((int) look.getX().evaluate(), (int) look.getY().evaluate());
                }
                break;
            case "CURSOR":
                try {
                    cursorManager.addCursor(((CursorStatement) command).getCursorId());
                } catch (CursorAlreadyExistingException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "SELECT":
                long cursorId = ((SelectStatement) command).getCursorId();
                select(cursorId);
                showInfo(String.format("Select Cursor n° %d \n", cursorId));
                break;
            case "REMOVE":
                try {
                    cursorManager.removeCursor(((RemoveStatement) command).getCursorId());
                } catch (CursorException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "IF":
                IfStatement ifStatement = (IfStatement) command;
                showInfo("Entering IF : " + String.valueOf(ifStatement.getExpression().evaluate()) + "\n");
                if (ifStatement.getExpression().evaluate()) {
                    showInfo("Executing IF block \n");
                    for (Statement statement : ifStatement.getBlock().getBlock()) {
                        nextCommand(statement);
                    }
                }
                break;
            case "FOR":
                ForStatement forStatement = ((ForStatement) command);
                int from = forStatement.getFrom();
                int to = forStatement.getTo();
                int step = forStatement.getStep();
                showInfo("Entering FOR : " + forStatement.getVariableName() + " FROM:" + from + ", TO:" + to + ", STEP:" + step + "\n");
                if (from <= to && step > 0) {
                    for (int var = from; var < to; var = var + step) {
                        varContext.setNumVarValue(forStatement.getVariableName(), (double) var);
                        forStatement.getVarContext().setNumVarValue(forStatement.getVariableName(), (double) var);
                        for (Statement statement : forStatement.getBlock().getBlock()) {
                            nextCommand(statement);
                        }
                    }
                }
                if (from >= to && step < 0) {
                    for (int var = from; var > to; var = var + step) {
                        varContext.setNumVarValue(forStatement.getVariableName(), (double) var);
                        forStatement.getVarContext().setNumVarValue(forStatement.getVariableName(), (double) var);
                        for (Statement statement : forStatement.getBlock().getBlock()) {
                            nextCommand(statement);
                        }
                    }
                }
                break;
            case "WHILE":
                WhileStatement whileStatement = (WhileStatement) command;
                while (whileStatement.getExpression().evaluate()) {
                    showInfo("Executing WHILE block \n");
                    for (Statement statement : whileStatement.getBlock().getBlock()) {
                        nextCommand(statement);
                    }
                }
                break;
            case "MIMIC":
                MimicStatement mimicStatement = (MimicStatement) command;
                showInfo("Mimic cursor n°" + mimicStatement.getCursorId() + "\n");
                cursorManager.addMimics(mimicStatement.getCursorId());
                showInfo("Executing MIMIC block \n");
                for (Statement statement : mimicStatement.getBlock().getBlock()) {
                    nextCommand(statement);
                }
                break;
            case "MIRROR":
                MirrorStatement mirrorStatement = (MirrorStatement) command;
                if (mirrorStatement.isCentralMirror()) {
                    showInfo("Central Mirror: x=" + (int) mirrorStatement.getX1().evaluate() + ", y=" + (int) mirrorStatement.getY1().evaluate() + "\n");
                    cursorManager.addMirror((int) mirrorStatement.getX1().evaluate(), (int) mirrorStatement.getY1().evaluate());
                } else {
                    cursorManager.addMirror((int) mirrorStatement.getX1().evaluate(), (int) mirrorStatement.getY1().evaluate(), (int) mirrorStatement.getX2().evaluate(), (int) mirrorStatement.getY2().evaluate());
                }
                showInfo("Executing MIRROR block \n");
                for (Statement statement : mirrorStatement.getBlock().getBlock()) {
                    nextCommand(statement);
                }
                break;
            case "NUM":
                NumberDeclaration numberDeclaration = (NumberDeclaration) command;
                showInfo("Number variable: " + numberDeclaration.getVariableName() + " = " + numberDeclaration.getExpression().evaluate()+ "\n");
                varContext.setNumVarValue(numberDeclaration.getVariableName(), numberDeclaration.getExpression().evaluate());
            case "STR":
                StringDeclaration stringDeclaration = (StringDeclaration) command;
                varContext.setBoolVarValue(stringDeclaration.getVariableName(), stringDeclaration.getExpression());
                break;
            case "BOOL":
                BoolDeclaration boolDeclaration = (BoolDeclaration) command;
                showInfo("Number variable: " + boolDeclaration.getVariableName() + " = " + boolDeclaration.getExpression().evaluate() + "\n");
                varContext.setBoolVarValue(boolDeclaration.getVariableName(), boolDeclaration.getExpression().evaluate());
                break;
            case "DEL":
                try {
                    varContext.delete(((DeleteDeclaration) command).getIdentification());
                    showInfo("Deleting variable: " + ((DeleteDeclaration) command).getIdentification() + "\n");
                } catch (VariableDoesNotExistException e) {
                    throw new RuntimeException(e);
                }
            default:
                break;
        }
    }

    /**
     * Convert a hsv color to rgb
     * @param H double hue
     * @param S double saturation
     * @param V double value
     * @return rgb values
     */
    public static double[] hsvToRgb(double H, double S, double V) {

        double R, G, B;
        double[] rgb = new double[3];
        H /= 360f;
        S /= 100f;
        V /= 100f;
        if (S == 0) {
            rgb[0] = V * 255;
            rgb[1] = V * 255;
            rgb[2] = V * 255;
        } else {
            double var_h = H * 6;
            if (var_h == 6)
                var_h = 0;
            int var_i = (int) Math.floor(var_h);
            double var_1 = V * (1 - S);
            double var_2 = V * (1 - S * (var_h - var_i));
            double var_3 = V * (1 - S * (1 - (var_h - var_i)));

            double var_r;
            double var_g;
            double var_b;
            switch (var_i){
                case 0:
                    var_r = V;
                    var_g = var_3;
                    var_b = var_1;
                    break;
                case 1:
                    var_r = var_2;
                    var_g = V;
                    var_b = var_1;
                    break;
                case 2:
                    var_r = var_1;
                    var_g = V;
                    var_b = var_3;
                    break;
                case 3:
                    var_r = var_1;
                    var_g = var_2;
                    var_b = V;
                    break;
                case 4:
                    var_r = var_3;
                    var_g = var_1;
                    var_b = V;
                    break;
                default:
                    var_r = V;
                    var_g = var_1;
                    var_b = var_2;
            }
            rgb[0] = var_r * 255;
            rgb[1] = var_g * 255;
            rgb[2] = var_b * 255;
        }
        return rgb;
    }

    /**
     * Enable all file options the canvas isn't empty.
     */
    private void enabledMenu(){
        if(this.isCanvaModified){
            saveItem.setDisable(false);
            saveAsItem.setDisable(false);
            exportItem.setDisable(false);
        }
    }

    /**
     * disableMenu disables the menu when the canvas is empty.
     */
    private void disableMenu(){
        saveItem.setDisable(true);
        saveAsItem.setDisable(true);
        exportItem.setDisable(true);
    }

    /**
     * Sends the drawing instruction to the console
     */
    @FXML
    private void submitCommand(){
        String command = inputArea.getText();
        if(!command.trim().isEmpty()){
            inputArea.clear();
            inputArea.setPromptText("Enter your instructions here");
            console.setCursorContext(this.cursorManager);
            List<Statement> statements = console.verifyInput(command);
            statementBuffer.addAll(statements);
            for(Statement currentCommand : statements){
                nextCommand(currentCommand);
            }
            statements.clear();
            showIssues();
        }
    }

    /**
     * setColorCursor sets cursor's color by entering a RGB code or a Hexadecimal code.
     * @param color the current color of the drawing line which is black initially.
     */
    @FXML
    private void setColorCursor(Color color){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.color(color.getRed(), color.getGreen(), color.getBlue(), this.currentPress));
        //Informs the user of the situation
        showInfo(String.format("Color : Red = %.2f Green = %.2f Blue = %.2f \n", color.getRed(), color.getGreen(), color.getBlue() ));
    }

    /**
     * setThickCursor sets the drawing line 's thickness by entering float variable.
     * @param thickness the current thickness of the drawing line which is 1 px initially.
     */
    @FXML
    private void  setThickCursor(float thickness){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(thickness);
    }

    /**
     * Sets the opacity of the line to be drawn
     * @param color the current color
     * @param pressure  the new opacity of the color
     */
    @FXML
    private void  setPresCursor(Color color, double pressure){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.currentPress = pressure;
        gc.setStroke(Color.color(color.getRed(), color.getGreen(), color.getBlue(), pressure));
    }

    /**
     * Add the new angle to the current one.
     * @param orientation the angle on degrees
     */
    @FXML
    private void setOrientationCursor(int orientation){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.rotate(orientation);
        //Informs the user of the situation
        showInfo(String.format("Rotation: %d°\n", orientation));
    }

    /**
     * Moves the cursor to its new position
     * @param x position on the horizontal axe.
     * @param y position on the vertical axe.
     */
    @FXML
    private void  moveCursor(int x,int y) {
        imageView.setX(x - widthImage);
        imageView.setY(y - heightImage);

        //Informs the user of the situation
        showInfo(String.format("New position : (%d, %d)\n", x, y));

    }

    /**
     * Draws a line from the cursor's position to the new one (x,y)
     * @param x the position on the horizontal axe
     * @param y the position on the vertical axe
     */
    @FXML
    private void drawLine(int x, int y) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        isCanvaModified = true;
        enabledMenu();
        gc.strokeLine(imageView.getX()-widthImage, imageView.getY()-heightImage, x,y);
        gc.stroke();
        drawCursorImage(x,y);
        //Informs the user of the situation
        showInfo(String.format("New position (%d, %d)\n", x, y));
    }

    //Pour effectuer les tests, Remettre auu bouton submit la méthode #handleSubmit()
    @FXML
    private void draw(){
        drawCursorImage(0,0);
        setThickCursor(4.5F);
        setPresCursor(Color.DARKCYAN,0.3);
        drawLine(200, 200);
        setColorCursor(Color.OLIVE);
        //setPresCursor(Color.OLIVE,1);
        setThickCursor(12F);
        drawLine(300, 300);
        rotateCursor(180);
        setColorCursor(Color.BROWN);
        setPresCursor(Color.BROWN,0.4);
        rotateCursor(90);
        setThickCursor(1);
        drawLine(400,400);
        hideCursor();
        showCursor();
    }

    /**
     * This method create a new canvas which crush the former one.
     */
    @FXML
    public void handleNew(){
        canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        imageView.setRotate(0);
        showCursor();
        drawCursorImage(0 ,0);
        historyFlow.getChildren().clear();
        setConsole(new LYnkConsole());
        this.commandList = new ArrayList<>();
        this.statementBuffer = new ArrayList<>();
        showInfo("Canvas has been reinitialized.\n");
        disableMenu();
    }

    /**
     * This method exports the canvas in user's chosen directory with ".png" extension.
     */
    @FXML
    public void handleExport(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        if (file != null) {
            if (!file.getName().endsWith(".png")) {
                file = new File(file.getPath() + ".png");
            }
            try {
                WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                canvas.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.print("error");
            }
        }
    }

    /**
     * This method imports a lynk file and executes the content
     */
    @FXML
    public void handleImport(){
        FileChooser fileChooser = new FileChooser();


        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("LYnk Files", "*.lynk");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            try {
                String content = new String(Files.readAllBytes(file.toPath()));
                console.verifyInput(content);
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Could not load file");
                alert.setContentText("Could not load data from file: " + file.getPath());
                alert.showAndWait();
            }
        }
    }

    /**
     * This method saves an object in the current file.
     */
    @FXML
    public void handleSave(){
        File file = mainApp.getFilePath();
        if (file != null) {
            saveToFile(file);
            mainApp.setFilePath(file);
        }else{
            handleSaveAs();
        }

    }

    /**
 +     * This method saves an object in user's chosen file.
     */
    @FXML
    public void handleSaveAs(){
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("LYnk Files", "*.lynk");

        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            if(!file.getPath().endsWith(".lynk")){
                file = new File(file.getPath() + ".lynk");
            }
            saveToFile(file);
        }
    }

    private void saveToFile(File file){
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Statement s : this.statementBuffer) {
                bw.write(s.toString());
            }
            bw.close();
        } catch(IOException e){
            showError("file cannot be written to " + file.getPath());
        }
    }
    /**
     * The method shows the authors on this project
     */
    @FXML
    public void handleAbout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Chromatynk Project");
        alert.setHeaderText("About");
        alert.setContentText("""
                Authors :
                Olivier Compagnon-–Minarro
                Clément Delamotte
                Matthias Franchini
                Célian Mignot
                Hénoch Xu
                """);

        alert.showAndWait();
    }

    /**
     * The method stops properly the programs when the users click on Item on upper bar.
     */
    @FXML
    private void handleExit(){
        System.exit(0);
    }

    /**
     * The method to cancel the change in execution speed
     */
    @FXML
    private void handleCancel(){
        this.speedPopup.close();
    }

    /**
     * The method to change the execution speed
     */
    @FXML
    private void handleChange(){
        if(isInputSpeedValid()){
            setStepperSpeed(Double.parseDouble(speedField.getText()));
            System.out.println(stepperSpeed);
            this.speedPopup.close();

            //Informs the user of the situation
            showInfo(String.format("Execution speed changed : new speed %.2f\n", stepperSpeed));
        }
    }

    @FXML
    private void handleSpeed(){
        mainApp.showSpeedPopUp();
        showSpeedExecution();
    }
    /**
     * The method draws a cursor on canvas
     */
    @FXML
    public void drawCursorImage(int x, int y){
        imageView.setX(x - widthImage);
        imageView.setY(y - heightImage);
    }

    /**
     * Erases a cursor on canvas
     */
    @FXML
    public void eraseCursorImage(){
        imageView.setImage(null);
    }

    /**
     * Changes the orientation of the cursor
     * @param angle the angle the cursor points to.
     */
    public void rotateCursor(float angle){
        this.imageView.setRotate(imageView.getRotate() + angle);
    }


    /**
     * this method shows the selected cursor.
     */
    @FXML
    public void showCursor(){
        this.imageView.setVisible(true);
    }

    /**
     *  this method hides the selected cursor
     */
    @FXML
    public void hideCursor(){
        this.imageView.setVisible(false);
    }

    public double getStepperSpeed(){
        return this.stepperSpeed;

    }
    public void setStepperSpeed(double stepperSpeed){
        this.stepperSpeed=stepperSpeed;
    }

    @FXML
    private void showSpeedExecution(){
        this.speedField.setText(String.format("%.2f", stepperSpeed));
    }

    /**
     * Adds an error message to historyFlow
     * @param error the error to be displayed
     */
    private void showError(String error){
        Text text = new Text(error);
        text.setFill(Color.RED);
        historyFlow.getChildren().add(text);
    }

    /**
     * Adds an alert message to historyFlow
     * @param warning the warning to be displayed
     */
    private void showWarning(String warning){
        Text text = new Text(warning);
        text.setFill(Color.YELLOW);
        historyFlow.getChildren().add(text);
    }

    /**
     * Adds all <code>{@link LYnkIssue}</code> from the <code>{@link LYnkConsole}</code> to the historyFlow.
     */
    private void showIssues(){
        for(LYnkIssue issue : console.getIssues()){
            if( issue.isError() ){
                showError(issue + "\n");
            }
            if( issue.isWarning() ){
                showWarning(issue + "\n");
            }
        }
    }

    /**
     * Adds an information message to historyFlow
     * @param info the information to be displayed
     */
    private void showInfo(String info){
        System.out.println(info); //Debugging Controller's actions on cursors and variables
        Text text = new Text(info);
        text.setFill(Color.BLACK);
        historyFlow.getChildren().add(text);
    }

    /**
     * Checks if the speedField value is a positive number
     * @return a boolean to tell if the input is valid
     */
    private boolean isInputSpeedValid(){
        String errorMessage = "";
        if (speedField.getText() == null || speedField.getText().length() == 0) {
            errorMessage += "Speed cannot be empty!\n";
        }else {
            try {
                Double.parseDouble(speedField.getText());
            }catch (NumberFormatException e){ //Remplacer par NegativeNumberException
                errorMessage += "No valid speed (must be a positive number)!\n";
            }
        }

        if (errorMessage.isEmpty()) {
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(this.speedPopup);
            alert.setTitle("Invalid Speed");
            alert.setHeaderText("Please enter a valid speed!");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    private void setConsole(LYnkConsole console){
        this.console = console;
    }
}
