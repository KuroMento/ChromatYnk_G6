package chromatynk.chromatynk_g6.controller;

import chromatynk.chromatynk_g6.MainApp;
import chromatynk.chromatynk_g6.diagnostic.LYnkIssue;
import chromatynk.chromatynk_g6.exceptions.NegativeNumberException;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.CursorAlreadyExistingException;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.CursorException;
import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;
import chromatynk.chromatynk_g6.interpreter.Cursor;
import chromatynk.chromatynk_g6.interpreter.CursorManager;
import chromatynk.chromatynk_g6.interpreter.LYnkConsole;
import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.statements.*;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
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
import java.util.ArrayList;
import java.util.List;

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

    private List<Statement> commandList;
    private List<Statement> statementBuffer;


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
        imageView.setRotate(90);
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
        int width = (int)canvas.getWidth();
        int height = (int)canvas.getHeight();
        cursorManager.setAllBorder(width, height);
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
                double backwardValue = ((BackwardStatement) command).getExpression().evaluate();
                showInfo("Backward: " +  backwardValue + "px"  + "\n");
                cursorManager.getSelectedCursor().forward(-backwardValue);
                drawLine(cursorManager.getSelectedCursor().getPosX(),cursorManager.getSelectedCursor().getPosY());
                long backwardId = cursorManager.getSelectedCursorId();
                for(Cursor mimicCursor : cursorManager.getSelectedCursor().getMimics()){
                    select(mimicCursor.getId());
                    cursorManager.getSelectedCursor().forward(-backwardValue);
                    drawLine(mimicCursor.getPosX(), mimicCursor.getPosY());
                }
                for(Cursor mirrorCursor : cursorManager.getSelectedCursor().getMirror()){
                    select(mirrorCursor.getId());
                    cursorManager.getSelectedCursor().forward(-backwardValue);
                    drawLine(mirrorCursor.getPosX(), mirrorCursor.getPosY());
                }
                select(backwardId);
                break;
            case "TURN":
                cursorManager.turn((float) ((RotationStatement) command).getExpression().evaluate());
                showInfo("Rotation: " + (float) ((RotationStatement) command).getExpression().evaluate() + "°"  + "\n");
                rotateCursor(cursorManager.getSelectedCursor().getRotationAngle());
                break;
            case "MOV":
                cursorManager.move((int) ((MoveStatement) command).getX().evaluate(), (int) ((MoveStatement) command).getY().evaluate());
                showInfo("Moved by: x=" + (int) ((MoveStatement) command).getX().evaluate() + ", y=" + (int) ((MoveStatement) command).getY().evaluate() + "\n");
                moveCursor(cursorManager.getSelectedCursor().getPosX(), cursorManager.getSelectedCursor().getPosY());
                break;
            case "POS":
                cursorManager.position((int) ((PositionStatement) command).getX().evaluate(), (int) ((PositionStatement) command).getY().evaluate());
                showInfo("New Position: x=" + (int) ((PositionStatement) command).getX().evaluate() + ", y=" + (int) ((PositionStatement) command).getY().evaluate() + "\n");
                moveCursor(cursorManager.getSelectedCursor().getPosX(), cursorManager.getSelectedCursor().getPosY());
                break;
            case "HIDE":
                showInfo("Hiding selected cursor: " + cursorManager.getSelectedCursor() + "\n");
                cursorManager.hide();
                hideCursor();
                break;
            case "SHOW":
                showInfo("Showing selected cursor: " + cursorManager.getSelectedCursor() + "\n");
                cursorManager.show();
                showCursor();
                break;
            case "PRESS":
                showInfo("Pressing cursor n°" + cursorManager.getSelectedCursor() + " : " + ((PressStatement) command).getExpression().evaluate() + "\n" );
                cursorManager.press(((PressStatement) command).getExpression().evaluate());
                setPresCursor(cursorManager.getSelectedCursor().getColor(), cursorManager.getSelectedCursor().getOpacity());
                break;
            case "COLOR":
                ColorStatement color = ((ColorStatement) command);
                if (color.isHexCode()) {
                    String hexCode = color.getHexcode();
                    int resultRed = Integer.valueOf(hexCode.substring(1, 3), 16);
                    int resultGreen = Integer.valueOf(hexCode.substring(3, 5), 16);
                    int resultBlue = Integer.valueOf(hexCode.substring(5, 7), 16);
                    cursorManager.color(new Color((double) resultRed /255, (double)resultGreen/255, (double)resultBlue/255, cursorManager.getSelectedCursor().getOpacity()));
                }
                else {
                    if (color.isRGB()) {
                        cursorManager.color(new Color(color.getRed().evaluate() / 255, color.getGreen().evaluate() / 255, color.getBlue().evaluate() / 255, cursorManager.getSelectedCursor().getOpacity()));
                    }
                    else {
                        if (color.isHSV()) {
                            cursorManager.color(Color.hsb(color.getRed().evaluate(), color.getGreen().evaluate(), color.getBlue().evaluate()));
                        }
                    }
                }
                setColorCursor(cursorManager.getSelectedCursor().getColor());
                break;
            case "THICK":
                try {
                    cursorManager.thick((float) ((ThickStatement) command).getExpression().evaluate());
                    setThickCursor(cursorManager.getSelectedCursor().getThickness());
                    showInfo("Thickness: " + (float) ((ThickStatement) command).getExpression().evaluate() + "px" + "\n");
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
                    showInfo("Looking at: x=" + look.getX().evaluate() + ", Y=" + look.getY().evaluate() + "\n");
                }
                rotateCursor(cursorManager.getSelectedCursor().getRotationAngle());
                break;
            case "CURSOR":
                CursorStatement cursorStatement = (CursorStatement) command;
                showInfo("Creating Cursor n°" + cursorStatement.getCursorId() + "\n");
                break;
            case "SELECT":
                long cursorId = ((SelectStatement) command).getCursorId();
                select(cursorId);
                showInfo(String.format("Select Cursor n° %d \n", cursorId));
                break;
            case "REMOVE":
                long removeCursorId = ((RemoveStatement) command).getCursorId();
                showInfo(String.format("Remove Cursor n° %d \n", removeCursorId));
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
                try {
                    varContext.delete(forStatement.getVariableName());
                } catch (VariableDoesNotExistException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "WHILE":
                WhileStatement whileStatement = (WhileStatement) command;
                showInfo("Entering WHILE : " + String.valueOf(whileStatement.getExpression().evaluate()) + "\n");
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
                    showInfo("Line Mirror: x1=" + (int) mirrorStatement.getX1().evaluate() + ", y1=" + (int) mirrorStatement.getY1().evaluate() + ", x2=" + (int) mirrorStatement.getX2().evaluate() + ", y2=" + (int) mirrorStatement.getY2().evaluate());
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
                showInfo("String variable : " + stringDeclaration.getVariableName() + " = " + stringDeclaration.getExpression() + "\n");
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
     * Select a cursor with its id
     * @param cursorId long id of the cursor to select
     */
    public void select(long cursorId){
        try {
            cursorManager.selectCursor(cursorId);
        } catch (CursorException e) {
            throw new RuntimeException(e);
        }
        rotateCursor(cursorManager.getSelectedCursor().getRotationAngle());
        setThickCursor(cursorManager.getSelectedCursor().getThickness());
        setPresCursor(cursorManager.getSelectedCursor().getColor(),cursorManager.getSelectedCursor().getOpacity());
        drawCursorImage(cursorManager.getSelectedCursor().getPosX(),cursorManager.getSelectedCursor().getPosY());
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
        gc.strokeLine(imageView.getX()+widthImage, imageView.getY()+heightImage, x,y);
        gc.stroke();
        drawCursorImage(x,y);
        //Informs the user of the situation
        showInfo(String.format("New position (%d, %d)\n", x, y));
    }

    /**
     * This method create a new canvas which crush the former one.
     */
    @FXML
    public void handleNew(){
        canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        imageView.setRotate(90);
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
        this.imageView.setRotate(90 + angle);
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
