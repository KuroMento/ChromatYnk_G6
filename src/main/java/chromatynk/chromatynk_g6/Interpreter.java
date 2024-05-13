package chromatynk.chromatynk_g6;

import chromatynk.chromatynk_g6.exceptions.*;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.CursorAlreadyExistingException;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.CursorException;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.InvalidColorException;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.MissingCursorException;
import chromatynk.chromatynk_g6.exceptions.variableExceptions.InvalidVariableTypeException;
import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class Interpreter {
    private CursorManager cursorManager;
    private VariableManager varList;
    private Console console;
    /**
     * Define the way the interpreter will handle instructions at runtime.
     */
    private Behaviour behaviour;
    /**
     * Stock the different instructions to be executed from a line edit or a loaded file using FIFO as standard.
     */
    private Queue<String> instructions;

    // Constructor (JavaDoc to do when better implementation)
    public Interpreter(){
        this.cursorManager = new CursorManager();
        this.varList = new VariableManager();
        this.console = new Console();
        this.behaviour = Behaviour.DIRECT;
        this.instructions = new ArrayDeque<>();
    }
    
    // Getter/Setter

    public Queue<String> getInfo() {
        return instructions;
    }

    public void setInfo(Queue<String> info) {
        this.instructions = info;
    }



    public Console getConsole(){
        return this.console;
    }

    /**
     * This method enters a line of command, checks if there are any % and remove them if it is the case and replace the number in front of the % by the pixel value
     * @param line the line of command
     */
    public static String[] percentUsed(String[] line){
        ArrayList<Integer> resolution = new ArrayList<>(); // store the height and width of the image
        resolution.add(1920); //temporary resolution, will be replaced by the exact resolution of the canvas
        resolution.add(1080);
        int resolutionAxis = 0; // 0: x-axis of the image; 1: y-axis of the image
        double val;
        for(int i = 1; i< line.length; i++){
            //If a % is detected the previous value in percent is replaced by its pixel value
            if(line[i].equals("%")){
                try {
                    val = (int) ((Double.parseDouble(line[i - 1]) / 100) * resolution.get(resolutionAxis));
                    line[i - 1] = String.valueOf(val);
                    line[i] = "";
                    resolutionAxis = (resolutionAxis + 1) % 2;
                }
                //If the value to apply % is not a double
                catch(NumberFormatException e){
                    System.out.println("Expected a value to apply % in " + Arrays.toString(line));
                }
            }
        }
        List<String> list = new ArrayList<>();
        for (String s : line) {
            if (!s.equals("")) {
                list.add(s);
            }
        }
        String[] stringArray = list.toArray(new String[0]);
        return stringArray;
    }

    /**
     * Check if the argument is a hexadecimal color
     * @param arg String of a value
     * @return True if the argument has a valid hexadecimal format
     */
    public static boolean isHexa(String arg){
        if (arg.length() == 7){
            char c = arg.charAt(0);
            if(c != '#'){
                return false;
            }
            for(int i = 1; i < arg.length(); i++){
                c = arg.charAt(i);
                int value = Character.getNumericValue(c);
                if (value < 0 || value > 15){
                    return false;
                }
            }
            return true;
        }
        return false;
    }



    /**
     * Check if the color is on the format 0-1 or 0-255
     * @param args the line passed in the interpreter with COLOR as args[0]
     * @return True if the Color format is correct (between 0 and 1).
     */
    public static boolean isFloatColor(String args) throws OutOfRangeException{
        if(Float.parseFloat(args) < 0 || Float.parseFloat(args) > 255){
            throw new OutOfRangeException("The numbers for the color must be between 0 and 1 or between 0 and 255.");
        }
        return !(Float.parseFloat(args) >= 1) && !(Float.parseFloat(args) <= 255);
    }

    /**
     * Transform float colors in RGB colors
     * @param args colors to check
     * @return  colors between 0 and 255
     * @throws OutOfRangeException the entered are not a color
     */
    public static int[] intColor(String[] args) throws  OutOfRangeException{
        int[] valRGB = new int[3];
        for(int i = 1; i < args.length; i++) {
            if(isFloatColor(args[i])){
                valRGB[i] = (int)(Float.parseFloat(args[i])*255);
            }
            else{
                valRGB[i] = Integer.parseInt(args[i]);
            }
        }
        return valRGB;
    }


    /**
     * Execute all the command stored in instructions in one shot, ignoring errors if they happened.
     *
     * @throws <code>InvalidNumberArgumentsException</code>
     * @throws <code>NegativeNumberException</code>
     */

    public void executeAllInfo() throws InvalidNumberArgumentsException, NegativeNumberException, CursorException, VariableDoesNotExistException, InvalidNameException, InvalidSymbolException, InvalidColorException, OutOfRangeException {
        while (!instructions.isEmpty()) {
            String command = instructions.remove();
            console.addLine(command);
            String[] args = command.toUpperCase().split(" ");
            nextOperation(args);
        }
    }

    /**
     * Execute a line given
     * @param args String[] line of operation
     * @throws InvalidNumberArgumentsException
     * @throws NegativeNumberException
     * @throws CursorException
     * @throws VariableDoesNotExistException
     * @throws InvalidNameException
     * @throws InvalidSymbolException
     * @throws InvalidColorException
     * @throws OutOfRangeException
     */
    public void nextOperation(String[] args) throws InvalidNumberArgumentsException, NegativeNumberException, CursorException, VariableDoesNotExistException, InvalidNameException, InvalidSymbolException, InvalidColorException, OutOfRangeException {
        String[] lineWithoutPercents;
        lineWithoutPercents = percentUsed(args);
        switch(args[0]) {
            //Simple Instruction
            case "FWD" :
                if (lineWithoutPercents.length != 2){
                    // Console.addLine("Error")
                    console.addLine("The line should be on the following format : 'FWD value'.");
                    throw new InvalidNumberArgumentsException();
                }
                cursorManager.getSelectedCursor().forward(Integer.parseInt(lineWithoutPercents[1]));
                break;
            case "BWD":
                if(lineWithoutPercents.length != 2){
                    console.addLine("The line should be on the following format : 'BWD value'.");
                    throw new InvalidNumberArgumentsException();
                }
                cursorManager.getSelectedCursor().backward(Integer.parseInt(lineWithoutPercents[1]));
                break;
            case "TURN":
                if(args.length != 2){
                    console.addLine("The line should be on the following format : 'TURN value'.");
                    throw  new InvalidNumberArgumentsException();
                }
                cursorManager.getSelectedCursor().rotateCursor(Float.parseFloat(args[1]));
                break;

            case "MOV" :
                if(lineWithoutPercents.length != 3){
                    console.addLine("The line should be on the following format : 'MOV value (%) value (%)'.");
                    throw new InvalidNumberArgumentsException();
                }
                cursorManager.getSelectedCursor().moveCursor(Integer.parseInt(lineWithoutPercents[1]),Integer.parseInt(lineWithoutPercents[2]));
                break;

            case "POS" :
                if(args.length != 3){
                    console.addLine("The line should be on the following format : 'POS value (%) value (%)'.");
                    throw  new InvalidNumberArgumentsException();
                }
                cursorManager.getSelectedCursor().placeCursor(Integer.parseInt(lineWithoutPercents[1]),Integer.parseInt(lineWithoutPercents[2]));
                break;

            case "HIDE" :
                if(args.length != 1){
                    console.addLine("The line should be on the following format : 'HIDE'.");
                    throw  new InvalidNumberArgumentsException();
                }
                cursorManager.getSelectedCursor().hide();
                break;

            case "SHOW" :
                if(args.length != 1){
                    console.addLine("The line should be on the following format : 'SHOW'.");
                    throw  new InvalidNumberArgumentsException();
                }
                cursorManager.getSelectedCursor().show();
                break;

            case "PRESS" :
                if(args.length != 2 && args.length != 3){
                    console.addLine("The line should be on the following format : 'PRESS value (%)'.");
                    throw  new InvalidNumberArgumentsException();
                }
                if(args.length == 2){
                    if(!(Float.parseFloat(args[1]) >= 0 && Float.parseFloat(args[1]) <= 1)){
                        console.addLine("Opacity must be between 0 and 1.");
                        throw new OutOfRangeException("Opacity must be between 0 and 1.");
                    }
                    cursorManager.getSelectedCursor().setOpacity(Float.parseFloat(args[1]));
                }
                if(args.length == 3){
                    if(!(Float.parseFloat(args[1]) >= 0 && Float.parseFloat(args[1]) <= 100)){
                        console.addLine("Opacity must be between 0 and 100.");
                        throw new OutOfRangeException("Opacity must be between 0 and 100.");
                    }
                    cursorManager.getSelectedCursor().setOpacity(Float.parseFloat(args[1])/100);
                }
                break;

            case "COLOR" :
                if(!(args.length == 2 || args.length == 4)){
                    console.addLine("The line should be on the following format : 'COLOR #RRGGBB' or 'COLOR red green blue'.");
                    throw  new InvalidNumberArgumentsException();
                }
                if(args.length == 2){
                    if(!isHexa(args[1])){
                        console.addLine("The color entered is not on the correct hexadecimal format.");
                        throw new InvalidColorException();
                    }
                    else{
                        cursorManager.getSelectedCursor().setColor(Color.web(args[1]));
                    }
                }
                if(args.length == 4){
                    int[] RGB = intColor(args);
                    cursorManager.getSelectedCursor().setColor(Color.rgb(RGB[1], RGB[2], RGB[3]));
                }
                break;

            case "THICK" :
                if(args.length != 2){
                    console.addLine("The line should be on the following format : 'THICK value'.");
                    throw  new InvalidNumberArgumentsException();
                }
                cursorManager.getSelectedCursor().setThickness(Float.parseFloat(args[1]));
                break;

            case "LOOKAT" :
                if(!(lineWithoutPercents.length != 2 && lineWithoutPercents.length != 3)){
                    console.addLine("The line should be on the following format : 'LOOKAT cursorID' or 'LOOKAT x (%) y (%)'.");
                    throw  new InvalidNumberArgumentsException();
                }
                if(lineWithoutPercents.length == 2){
                    cursorManager.getSelectedCursor().lookAt(cursorManager.getSelectedCursor().getPosX(),cursorManager.getSelectedCursor().getPosY());
                }
                if(lineWithoutPercents.length == 3){
                    cursorManager.getSelectedCursor().lookAt(Integer.parseInt(lineWithoutPercents[1]),Integer.parseInt(lineWithoutPercents[2]));
                }
                break;

            //Cursor Management
            case "CURSOR" :
                if(args.length != 2){
                    console.addLine("The line should be on the following format : 'CURSOR cursorID'.");
                    throw new InvalidNumberArgumentsException();
                }
                if (cursorManager.cursorExist(Long.parseLong(args[1]))){
                    console.addLine("The cursor you tried to create already exists.");
                    throw new CursorAlreadyExistingException();
                }
                cursorManager.addCursor(Long.parseLong(args[1]));
                break;
            case "SELECT" :
                if(args.length != 2){
                    console.addLine("The line should be on the following format : 'SELECT cursorID'.");
                    throw new InvalidNumberArgumentsException();
                }
                if(!(cursorManager.cursorExist(Long.parseLong(args[1])))){
                    console.addLine("The selected cursor does not exist.");
                    throw new MissingCursorException("The selected cursor does not exist.");
                }
                cursorManager.selectCursor(Long.parseLong(args[1]));
                break;
            case "REMOVE" :
                if(args.length != 2){
                    console.addLine("The line should be on the following format : 'REMOVE cursorID'.");
                    throw new InvalidNumberArgumentsException();
                }
                long selectedId = Long.parseLong(args[1]);
                if(!(cursorManager.cursorExist(selectedId))){
                    console.addLine("This cursor does not exist.");
                    throw new MissingCursorException("This cursor does not exist.");
                }
                cursorManager.removeCursor(selectedId);
                break;
            //Instruction block
            case "IF" :
                if(args.length < 2){
                    console.addLine("The line should be on the following format : 'IF boolean'.");
                    throw new InvalidNumberArgumentsException();
                }
                break;
            case "FOR" :
                if(!(args.length == 4 || args.length == 6 || args.length == 8)){
                    console.addLine("The line should be on the following format : 'FOR name (FROM v1) TO v2 (step v3)'.");
                    throw new InvalidNumberArgumentsException();
                }
                break;
            case "WHILE" :
                if(args.length < 2){
                    console.addLine("The line should be on the following format : 'WHILE boolean'.");
                    throw new InvalidNumberArgumentsException();
                }
                break;
            case "MIMIC" :
                if(args.length != 2){
                    console.addLine("The line should be on the following format : 'MIMIC cursorID'.");
                    throw new InvalidNumberArgumentsException();
                }
                break;
            case "MIRROR" :
                if(!(lineWithoutPercents.length != 2 && lineWithoutPercents.length != 4)){
                    console.addLine("The line should be on the following format : 'MIRROR x (%) y (%)' or 'MIRROR x1 (%) y1 (%) x2(%) y2 (%)'.");
                    throw new InvalidNumberArgumentsException();
                }
                break;
            //Variables and properties
            case "NUM" :
                if(!(args.length == 2) || !(args.length == 4)){
                    console.addLine("The line should be on the following format : 'NUM name (= value)'.");
                    throw new InvalidNumberArgumentsException();
                }
                if(!args[2].equals("=")){
                    console.addLine("The format should be : 'NUM name = value'.");
                    throw new InvalidSymbolException("The format should be : 'NUM name = value'.");
                }
                if(args.length == 4) {
                    double newVariable = Double.parseDouble(args[3].trim());
                    varList.addVariableDouble(args[1], newVariable);
                }
                if(args.length == 2){
                    varList.addVariableDouble(args[1]);
                }
                break;
            case "STR" :
                if(!(args.length == 2) || !(args.length == 4)){
                    console.addLine("The line should be on the following format : 'STR name (= value)'.");
                    throw new InvalidNumberArgumentsException();
                }
                if(!args[2].equals("=")){
                    console.addLine("The format should be : 'STR name = word/sentence'.");
                    throw new InvalidSymbolException("The format should be : 'STR name = word/sentence'.");
                }
                if(args.length == 4) {
                    varList.addVariableString(args[1],args[3]);
                }
                if(args.length == 2){
                    varList.addVariableString(args[1]);
                }
                break;
            case "BOOL" :
                if(!(args.length == 2) || !(args.length == 4)){
                    console.addLine("The line should be on the following format : 'BOOL name (= value)'.");
                    throw new InvalidNumberArgumentsException();
                }
                if(!args[2].equals("=")){
                    console.addLine("The format should be : 'BOOL name = true/false'.");
                    throw new InvalidSymbolException("The format should be : 'BOOL name = true/false'.");
                }
                if(args.length == 4) {
                    if (args[3].equals("TRUE")) {
                        varList.addVariableBool(args[1], true);
                    }
                    if (args[3].equals("FALSE")) {
                        varList.addVariableBool(args[1]);
                    }
                }
                if(args.length == 2){
                    varList.addVariableBool(args[1]);
                }
                break;
            case "DEL" :
                if(args.length != 2){
                    console.addLine("The line should be on the following format : 'DEL name'.");
                    throw new InvalidNumberArgumentsException();
                }
                varList.delete(args[1]);
                break;
            default:
                console.addLine("Command not found/Unknown command.");
                System.out.println("Command not found/Unknown command.");
                break;
        }
    }

    /**
     * Get a subStringArray of line from start to end included
     * @param line String[]
     * @param start start of the new String[]
     * @param end end of the new String[]
     * @return the subarray of String from start to end
     */
    public static String[] subArray(String[] line, int start, int end){
        String result = "";
        for(int i=start; i<=end; i++){
            result = result + line[i] + " ";
        }
        String[] args = result.split(" ");
        return(args);
    }

    /**
     * Takes a line of command in argument and choose the boolean operation to do.
     * @param line The line of command.
     * @return if the operation is true or false
     * @throws InvalidInputException A variable is not of expected type
     */
    public boolean nextOperation(String[] line) throws InvalidInputException{
        if(line[0].equals("NOT")){
            return notVariable(subArray(line, 1, line.length-1));
        }
        switch (line[1]) {
            case "AND":
                return andVariable(line[0], subArray(line, 2, line.length-1));
            case "OR":
                return orVariable(line[0], subArray(line, 2, line.length-1));
            case "==":
                return equalVariable(line[0], subArray(line, 2, line.length-1));
            case "!=":
                return !equalVariable(line[0], subArray(line, 2, line.length-1));
            case ">":
                return greaterThanVariable(line[0], subArray(line, 2, line.length-1));
            case "<":
                return !greaterThanOrEqualVariable(line[0], subArray(line, 2, line.length-1));
            case ">=":
                return greaterThanOrEqualVariable(line[0], subArray(line, 2, line.length-1));
            case "<=":
                return !greaterThanVariable(line[0], subArray(line, 2, line.length-1));
            case ")":
                return valueBool(line[0]);
            default:
                throw new InvalidInputException("The symbol entered is not recognised");
        }
    }

    /**
     * Takes a line of command in argument and if there is a mathematical computation between the () to do then return the result.
     * @param line The line of command.
     * @return Return the result of the operation between the () so the boolean comparison can be done after.
     * @throws InvalidInputException A variable is not of expected type
     */
    public double nextOperationMath(String[] line) throws InvalidInputException{
        switch (line[1]){
            case "+":
                return addMath(line[0], subArray(line, 2, line.length-1));
            case "-":
                return subtractionMath(line[0], subArray(line, 2, line.length-1));
            case "/":
                return divisionMath(line[0], subArray(line, 2, line.length-1));
            case "*":
                return multiplicationMath(line[0], subArray(line, 2, line.length-1));
            case ")":
                return valueMath(line[0]);
            default:
                throw new InvalidInputException("The symbol entered is not recognised");
        }
    }

    /**
     * Verify if the entry before and after the NOT is true or false.
     * @param line The instruction remaining at the right of NOT
     * @return line with the first value being the valuation of not
     */
    public boolean notVariable(String[] line) throws InvalidInputException{
        try {
            if (varList.isVariable(line[0])) {
                //If there is only one operation
                if (line.length == 1) {
                    return varList.notVariable(line[0]);
                }
                //If there is more operation
                if (line.length >= 2) {
                    line[0] = String.valueOf(!varList.getVariableBool(line[0]));
                    line[0] = line[0].toUpperCase();
                    return nextOperation(line);
                }
            }
            //If not is applied to an operation block
            if (line[0].equals("(")) {
                return !nextOperation(subArray(line, 1, line.length - 1));
            }
        }
        catch(VariableDoesNotExistException e){
            System.out.println("A variable given does not exist");
        }
        catch(InvalidVariableTypeException e){
            System.out.println("A variable is not of expected type");
        }
        return false;
    }

    /**
     * Verify if the entry before and after the AND is true or false.
     * @param var1 String variable name at the left of AND
     * @param line The instruction remaining at the right of AND
     * @return line with the first value being the valuation of and
     * @throws InvalidInputException A variable is not of expected type
     */
    public boolean andVariable(String var1, String[] line) throws InvalidInputException{
        try {
            if(varList.isVariable(line[0])){
                //If there is only one operation
                if(line.length == 1){
                    return varList.andVariable(var1,line[0]);
                }
                if(line.length >= 2){
                    line[0] = String.valueOf(varList.andVariable(var1,line[0]));
                    line[0] = line[0].toUpperCase();
                    return nextOperation(line);
                }
            }
            if(line[0].equals("(")){
                return nextOperation(subArray(line,1,line.length-1));
            }
        }
        catch (VariableDoesNotExistException e){
            System.out.println("A variable given does not exist");
        }
        catch (InvalidVariableTypeException e){
            System.out.println("A variable is not of expected type");
        }
        return false;
    }

    /**
     * Verify if the entry before and after the OR is true or false.
     * @param var1 String variable name at the left of OR
     * @param line The instruction remaining at the right of OR
     * @return line with the first value being the valuation of if
     * @throws InvalidInputException A variable is not of expected type
     */
    public boolean orVariable(String var1, String[] line) throws InvalidInputException{
        try{
            if(varList.isVariable(line[0])){
                //If there is only one variable
                if(line.length == 1){
                    return varList.orVariable(var1, line[0]);
                }
                //If there is more content
                if(line.length >= 2){
                    line[0] = String.valueOf(varList.orVariable(var1, line[0])).toUpperCase();
                    return nextOperation(line);
                }
            }
            if(line[0].equals("(")){
                return varList.orVariable(var1, String.valueOf(nextOperation(subArray(line, 1, line.length-1))).toUpperCase());
            }
        }
        catch(VariableDoesNotExistException e){
            System.out.println("A variable given does not exist");
        }
        catch(InvalidVariableTypeException e){
            System.out.println("A variable is not of expected type");
        }
        return false;
    }

    /**
     * Verify if the entry before and after the == is true or false.
     * @param var1 String variable name at the left of ==
     * @param line The instruction remaining at the right of ==
     * @return line with the first value being the valuation of equal
     * @throws InvalidInputException A variable is not of expected type
     */
    public boolean equalVariable(String var1, String[] line) throws InvalidInputException{
        try {
            if(varList.isVariable(line[0])){
                //If there is only one operation
                if(line.length == 1){
                    return varList.equalVariable(var1,line[0]);
                }
                if(line.length >= 2){
                    line[0] = String.valueOf(varList.equalVariable(var1,line[0]));
                    line[0] = line[0].toUpperCase();
                    return nextOperation(line);
                }
            }
            if(line[0].equals("(")){
                return varList.equalVariable(var1, String.valueOf(nextOperationMath(subArray(line, 1, line.length-1))));
            }
        }
        catch (VariableDoesNotExistException e){
            System.out.println("A variable given does not exist");
        }
        catch (InvalidVariableTypeException e){
            System.out.println("A variable is not of expected type");
        }
        return false;
    }

    /**
     * Verify if the entry before and after the > is true or false.
     * @param var1 String variable name at the left of >
     * @param line The instruction remaining at the right of >
     * @return line with the first value being the valuation of >
     * @throws InvalidInputException A variable is not of expected type
     */
    public boolean greaterThanVariable(String var1, String[] line) throws InvalidInputException{
        try {
            if(varList.isVariable(line[0])){
                //If there is only one operation
                if(line.length == 1){
                    return varList.greaterThan(var1,line[0]);
                }
                line[0] = String.valueOf(varList.greaterThan(var1, line[0]));
                line[0] = line[0].toUpperCase();
                return nextOperation(line);
            }
            if(line[0].equals("(")){
                return varList.greaterThan(var1, String.valueOf(nextOperationMath(subArray(line, 1, line.length-1))));
            }
        }
        catch (VariableDoesNotExistException e){
            System.out.println("A variable given does not exist");
        }
        catch (InvalidVariableTypeException e){
            System.out.println("A variable is not of expected type");
        }
        return false;
    }

    /**
     * Verify if the entry before and after the >= is true or false.
     * @param var1 String variable name at the left of >=
     * @param line The instruction remaining at the right of >=
     * @return line with the first value being the valuation of >=
     * @throws InvalidInputException A variable is not of expected type
     */
    public boolean greaterThanOrEqualVariable(String var1, String[] line) throws InvalidInputException{
        try {
            if(varList.isVariable(line[0])){
                //If there is only one operation
                if(line.length == 1){
                    return varList.greaterThan(var1,line[0]) || varList.equalVariable(var1,line[0]);
                }
                if(line.length >= 2){
                    line[0] = String.valueOf(varList.greaterThan(var1,line[0]) || varList.equalVariable(var1,line[0]));
                    line[0] = line[0].toUpperCase();
                    return nextOperation(line);
                }
            }
            if(line[0].equals("(")){
                return varList.greaterThan(var1, String.valueOf(nextOperationMath(subArray(line, 1, line.length-1))));
            }
        }
        catch (VariableDoesNotExistException e){
            System.out.println("A variable given does not exist");
        }
        catch (InvalidVariableTypeException e){
            System.out.println("A variable is not of expected type");
        }
        return false;
    }

    /**
     * Add two string arguments as double
     * @param var1 The first element
     * @param line The instruction remaining at the right of +
     * @return line with the first value being the valuation of +
     */
    public double addMath(String var1, String[] line) throws InvalidInputException{
        try {
            //if var1 is a variable
            if (varList.isVariable(var1)) {
                //if line[0] is a second variable
                if (varList.isVariable(line[0])) {
                    line[0] = String.valueOf(varList.addMath(var1, line[0])); // 1:variable 2:variable
                    return nextOperationMath(line);
                } else {
                    line[0] = String.valueOf(varList.getVariableDouble(var1) + Integer.parseInt(line[0])); // 1:variable 2:value
                    return nextOperationMath(line);
                }
            } else {
                //if line[0] is a variable
                if (varList.isVariable(line[0])) {
                    line[0] = String.valueOf(Integer.parseInt(var1) + varList.getVariableDouble(line[0])); //1: value 2 : variable
                    return nextOperationMath(line);
                } else {
                    line[0] = String.valueOf(Integer.parseInt(var1) + Integer.parseInt(line[0])); // 1:value 2:value
                    return nextOperationMath(line);
                }
            }
        }
        catch(InvalidInputException e){
            System.out.println("The command is not recognised");
        }
        catch(VariableDoesNotExistException e){
            System.out.println("A variable does not exist in line " + Arrays.toString(line));
        }
        catch(InvalidVariableTypeException e){
            System.out.println("A variable is not of expected double type");
        }
        return 0;
    }

    /**
     * Subtract two string arguments as double
     * @param var1 The first element
     * @param line The instruction remaining at the right of AND
     * @return line with the first value being the valuation of -
     */
    public double subtractionMath(String var1, String[] line) throws InvalidInputException{
        try {
            //if var1 is a variable
            if (varList.isVariable(var1)) {
                //if line[0] is a second variable
                if (varList.isVariable(line[0])) {
                    line[0] = String.valueOf(varList.substractMath(var1, line[0]));
                    return nextOperationMath(line);
                } else {
                    line[0] = String.valueOf(varList.getVariableDouble(var1) - Integer.parseInt(line[0]));
                    return nextOperationMath(line);
                }
            } else {
                //if line[0] is a variable
                if (varList.isVariable(line[0])) {
                    line[0] = String.valueOf(Integer.parseInt(var1) - varList.getVariableDouble(line[0]));
                    return nextOperationMath(line);
                } else {
                    line[0] = String.valueOf(Integer.parseInt(var1) - Integer.parseInt(line[0]));
                    return nextOperationMath(line);
                }
            }
        }
        catch(InvalidInputException e){
            System.out.println("The command is not recognised");
        }
        catch(VariableDoesNotExistException e){
            System.out.println("A variable does not exist in line " + Arrays.toString(line));
        }
        catch(InvalidVariableTypeException e){
            System.out.println("A variable is not of expected double type");
        }
        return 0;
    }

    /**
     * Multiply two string arguments as double
     * @param var1 The first element
     * @param line The instruction remaining at the right of AND
     * @return line with the first value being the valuation of *
     */
    public double multiplicationMath(String var1, String[] line) throws InvalidInputException{
        try {
            //if var1 is a variable
            if (varList.isVariable(var1)) {
                //if line[0] is a second variable
                if (varList.isVariable(line[0])) {
                    line[0] = String.valueOf(varList.multiplyMath(var1, line[0]));
                    return nextOperationMath(line);
                } else {
                    line[0] = String.valueOf(varList.getVariableDouble(var1) * Integer.parseInt(line[0]));
                    return nextOperationMath(line);
                }
            } else {
                //if line[0] is a variable
                if (varList.isVariable(line[0])) {
                    line[0] = String.valueOf(Integer.parseInt(var1) * varList.getVariableDouble(line[0]));
                    return nextOperationMath(line);
                } else {
                    line[0] = String.valueOf(Integer.parseInt(var1) * Integer.parseInt(line[0]));
                    return nextOperationMath(line);
                }
            }
        }
        catch(InvalidInputException e){
            System.out.println("The command is not recognised");
        }
        catch(VariableDoesNotExistException e){
            System.out.println("A variable does not exist in line " + Arrays.toString(line));
        }
        catch(InvalidVariableTypeException e){
            System.out.println("A variable is not of expected double type");
        }
        return 0;
    }

    /**
     * Divide two string arguments as double
     * @param var1 The first element
     * @param line The instruction remaining at the right of AND
     * @return line with the first value being the valuation of /
     */
    public double divisionMath(String var1, String[] line) throws InvalidInputException{
        try {
            //if var1 is a variable
            if (varList.isVariable(var1)) {
                //if line[0] is a second variable
                if (varList.isVariable(line[0])) {
                    line[0] = String.valueOf(varList.divideMath(var1, line[0]));
                    return nextOperationMath(line);
                } else {
                    line[0] = String.valueOf(varList.getVariableDouble(var1) / Integer.parseInt(line[0]));
                    return nextOperationMath(line);
                }
            } else {
                //if line[0] is a variable
                if (varList.isVariable(line[0])) {
                    line[0] = String.valueOf(Integer.parseInt(var1) / varList.getVariableDouble(line[0]));
                    return nextOperationMath(line);
                } else {
                    line[0] = String.valueOf(Integer.parseInt(var1) / Integer.parseInt(line[0]));
                    return nextOperationMath(line);
                }
            }
        }
        catch(InvalidInputException e){
            System.out.println("The command is not recognised");
        }
        catch(VariableDoesNotExistException e){
            System.out.println("A variable does not exist in line " + Arrays.toString(line));
        }
        catch(InvalidVariableTypeException e){
            System.out.println("A variable is not of expected double type");
        }
        return 0;
    }

    /**
     * Get the double value of a string
     * @param name String name of the variable or value
     * @return double value of name
     */
    public double valueMath(String name){
        try {
            if (varList.isVariable(name)) {
                return varList.getVariableDouble(name);
            }
            return Double.parseDouble(name);
        }
        catch(VariableDoesNotExistException e){
            System.out.println("Variable " + name + " does not exist");
        }
        catch(InvalidVariableTypeException e){
            System.out.println("Variable " + name + " is not of double type");
        }
        return 0;
    }

    /**
     * Get the boolean value of a string
     * @param name String name of the variable or value
     * @return boolean value of name
     */
    public boolean valueBool(String name){
        try {
            return varList.getVariableBool(name);
        } catch (InvalidVariableTypeException e) {
            System.out.println("Variable " + name + " is not of boolean type");
        } catch (VariableDoesNotExistException e) {
            System.out.println("Variable " + name + " does not exist");
        }
        return false;
    }

    public void add(String command){
        instructions.add(command);
    }

    public void remove(String command) {
        instructions.remove(command);
    }
}
