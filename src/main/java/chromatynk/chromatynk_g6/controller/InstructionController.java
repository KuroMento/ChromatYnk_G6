package chromatynk.chromatynk_g6.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class InstructionController {

    private final String[] instructionsList = {"FWD", "BWD", "TURN", "MOV", "POS", "HIDE", "SHOW", "PRESS", "COLOR", "THICK",
            "LOOKAT", "CURSOR", "SELECT", "REMOVE", "IF", "FOR", "WHILE", "MIMIC", "MIRROR", "NUM", "STR", "BOOL",
            "DEL", "FROM", "TO", "STEP", "AND", "OR", "NOT", "TRUE", "FALSE"};

    @FXML
    private ListView<String> commandList;

    @FXML
    private void initialize(){
        commandList.getItems().addAll(instructionsList);
        commandList.getItems().sort(String::compareTo);
    }

    @FXML
    private void selectCommand(){
        String command = commandList.getSelectionModel().getSelectedItem();
        if(command != null){
            //display on textArea an example
        }
    }

    @FXML
    private void submitCommand(){
        String command; //= inputArea.getText();
        if(!command.trim().isEmpty()){
            //inputArea.clear;
            //inputArea.setPromptText("Enter your instruction here");
        }
    }
}
