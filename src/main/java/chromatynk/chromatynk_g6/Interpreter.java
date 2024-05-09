package chromatynk.chromatynk_g6;

import chromatynk.chromatynk_g6.exceptions.*;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.CursorAlreadyExistingException;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.InvalidColorException;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.MissingCursorException;
import chromatynk.chromatynk_g6.exceptions.variableExceptions.InvalidVariableTypeException;
import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Queue;
public class Interpreter {
    CursorManager cursorManager = new CursorManager();
    VariableManager varList = new VariableManager();
    /**
     * Define the way the interpreter will handle instructions at runtime.
     */
    private Behaviour behaviour;
    /**
     * Stock the different instructions to be executed from a line edit or a loaded file using FIFO as standard.
     */
    private Queue<String> info;

    // Constructor (JavaDoc to do when better implementation)
    public Interpreter(){
    }

    public Interpreter(String path){ //temporary constructor, will be removed when interpreter will be more advanced
        this.instructions = null;
        read(path);
    }
    
    // Getter/Setter
    public Behaviour getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    public Queue<String> getInfo() {
        return info;
    }

    public void setInfo(Queue<String> info) {
        this.info = info;
    }

    /**
     * allows to read an imported script and to stock the lines in memory.
     * @param path the path to the script.
     */
    public void read(String path) { //used to read an imported script
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String temp;
            while (br.ready()) {
                temp = br.readLine();
                System.out.println(temp);
                info.add(temp); //allows to stock entries in the history
            }
            br.close();
            System.out.println();
        } catch (Exception e) {
            System.out.println("Erreur :" + e);
        }
    }
    
    // Constructor (JavaDoc to do when better implementation)
    public Interpreter(){
    }
    public Interpreter(String path){ //temporary constructor, will be removed when interpreter will be more advanced
        read(path);
        cursorList = new HashMap<Long, Cursor>();
        this.info = null;
    }

    /**
     * creates default conditions at the start of the application
     */
    public void starting(){
        Color defaultColor = Color.rgb(0,0,0);
        Cursor cursor1 = new Cursor(0l,0,0,1,0,defaultColor, 1,true); //default cursor
        cursorList.put(cursor1.getId(), cursor1);
    }

    /**
     * Check if the argument is a hexadecimal color
     * @param arg
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
     * Searches for the max value within an array of float.
     * @param args the array of float to search from
     * @return the maw value of the array
     */
    public static float MaxValue(float[] args){
        float max = args[0];
        for(int i=1;i<args.length;i++){
            if(max<args[i]){
                max=args[i];
            }
        }
        return max;
    }

    /**
     * Check if the color is on the format 0-1 or 0-255
     * @param args the line passed in the interpreter with COLOR as args[0]
     * @return True if the Color format is correct (between 0 and 1).
     */
    public static boolean isFloatColor(String args) throws OutOfRangeException{
        if(Float.parseFloat(args) < 0 || Float.parseFloat(args) > 255){
            throw new OutOfRangeException("The numbers for the color must be between 0 and 1 or between 0 and 255");
        }
        if( Float.parseFloat(args) >= 1 || Float.parseFloat(args) <= 255){
            return false;
        }
    return true;
    }

    public static int[] intColor(String[] args) throws  OutOfRangeException{
        float red = Float.parseFloat(args[1]);
        float green = Float.parseFloat(args[2]);
        float blue = Float.parseFloat(args[3]);
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

    public void executeAllInfo() throws InvalidNumberArgumentsException, NegativeNumberException, CursorAlreadyExistingException, VariableDoesNotExistException, InvalidNameException, InvalidSymbolException, InvalidColorException, OutOfRangeException, MissingCursorException { //in the main, the first id is 0
        while (! instructions.isEmpty()){
           String command = instructions.remove();
           String[] args =command.toUpperCase().split(" ");
           
           //for (int i= 0; i < args.length; i++){
               switch(args[0]) {
                        //Simple Instruction
                   case "FWD" :
                       if (lineWithoutPercents.length != 2){
                           throw new InvalidNumberArgumentsException();
                       }
                       cursorList.get(0).forward(Integer.parseInt(args[1]));
                       break;
                   case "BWD":
                        if(args.length != 2){
                            throw  new InvalidNumberArgumentsException();
                        }
                       break;

                   case "TURN":
                       if(args.length != 2){
                           throw  new InvalidNumberArgumentsException();
                       }
                       break;

                   case "MOV" :
                       if(args.length != 3){
                           throw  new InvalidNumberArgumentsException();
                       }
                       break;

                   case "POS" :
                       if(args.length != 3){
                           throw  new InvalidNumberArgumentsException();
                       }
                       break;

                   case "HIDE" :
                       if(args.length != 1){
                           throw  new InvalidNumberArgumentsException();
                       }
                       cursorManager.getSelectedCursor().hide();
                       break;

                   case "SHOW" :
                       if(args.length != 1){
                           throw  new InvalidNumberArgumentsException();
                       }
                       cursorManager.getSelectedCursor().show();
                       break;

                   case "PRESS" :
                       if(args.length != 2){
                           throw  new InvalidNumberArgumentsException();
                       }
                       break;

                   case "COLOR" :
                       if(!(args.length == 2 || args.length == 4)){
                           throw  new InvalidNumberArgumentsException();
                       }
                        if(args.length == 2){
                            if(!isHexa(args[1])){
                                throw new InvalidColorException();
                            }
                            else{
                                cursorManager.getSelectedCursor().setColor(Color.web(args[1]));
                            }
                        }
                        if(args.length == 4){



                        }
                       break;

                   case "THICK" :
                       if(args.length != 2){
                           throw  new InvalidNumberArgumentsException();
                       }
                       break;

                   case "LOOKAT" :
                       if(!(args.length == 2 || args.length == 3)){
                           throw  new InvalidNumberArgumentsException();
                       }
                       break;

                       //Cursor Management
                   case "CURSOR" :
                       if(args.length != 2){
                           throw new InvalidNumberArgumentsException();
                        }
                       break;
                   case "SELECT" :
                       if(args.length != 2){
                           throw new InvalidNumberArgumentsException();
                       }
                       break;
                   case "REMOVE" :
                       if(args.length != 2){
                           throw new InvalidNumberArgumentsException();
                       }
                       long selectedId = Long.parseLong(args[1]);
                       if(!(cursorManager.cursorExist(selectedId))){
                           throw new MissingCursorException("This cursor does not exist");
                       }
                       cursorManager.removeCursor(selectedId);
                       break;
                        //Instruction block
                   case "IF" :
                       if(args.length != 2){
                           throw new InvalidNumberArgumentsException();
                       }
                       break;
                   case "FOR" :
                       if(!(args.length == 4 || args.length == 6 || args.length == 8)){
                           throw new InvalidNumberArgumentsException();
                       }
                       break;
                   case "WHILE" :
                       if(args.length != 2){
                           throw new InvalidNumberArgumentsException();
                       }
                       break;
                   case "MIMIC" :
                       if(args.length != 2){
                           throw new InvalidNumberArgumentsException();
                       }
                       break;
                   case "MIRROR" :
                       if(!(args.length == 5 || args.length == 3)){
                           throw new InvalidNumberArgumentsException();
                       }
                       break;
                        //Variables and properties
                   case "NUM" :
                       if(!(args.length == 2 || args.length == 4)){
                           throw new InvalidNumberArgumentsException();
                       }
                       if(!args[2].equals("=")){
                           throw new InvalidSymbolException("The format should be : STR NUM = number");
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
                       if(!(args.length == 2 || args.length == 4)){
                           throw new InvalidNumberArgumentsException();
                       }
                       break;
                   case "BOOL" :
                       if(!(args.length == 2 || args.length == 3)){
                           throw new InvalidNumberArgumentsException();
                       }
                       break;
                   case "DEL" :
                       if(args.length != 2){
                           throw new InvalidNumberArgumentsException();
                       }
                       varList.delete(args[1]);
                        break;
                   case "NOT" :
                       if(args.length != 2){
                           throw new InvalidNumberArgumentsException();
                       }
                       break;
                   default:
                       System.out.println("Command not found/Unknown command");
                       break;
           }
        }
    }

    public boolean notVariable(String[] line){}
/*
    public boolean andVariable(String[] line){}

    public boolean orVariable(String[] line){}*/

    public boolean ifVariable(String[] line){
        if(line.length == 0){
            return true;
        }
        try {
            if(line[0] == "NOT") {
                return notVariable(line);
            } else {
                if (line[1] == "AND") {
                }
                if (line[1] == "OR") {
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Unexpected argument in command line + " + line.toString());
        }
    }

    public void add(String command){
        info.add(command);
    }

    public void remove(String command) {
        info.remove(command);
    }
}
