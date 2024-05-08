package chromatynk.chromatynk_g6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Queue;
public class Interpreter {
    /**
     * Define the way the interpreter will handle instructions at runtime.
     */
    private Behaviour behaviour;

    /**
     * Stock the different instructions to be executed from a line edit or a loaded file using FIFO as standard.
     */
    private Queue<String> info;

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
     * Execute all the command stored in info
     * @throws <code>InvalidNumberArgumentsException</code>
     */
    public void executeInfo() throws InvalidNumberArgumentsException, NegativeNumberException {
        while (! info.isEmpty()){
           String command = info.remove();
           String[] args =command.toUpperCase().split(" ");
           
           //for (int i= 0; i < args.length; i++){
               switch(args[0]) {
                        //Simple Instruction
                   case "FWD" :
                       if (args.length != 2) {
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
                       break;

                   case "SHOW" :
                       if(args.length != 1){
                           throw  new InvalidNumberArgumentsException();
                       }
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
                       if(!(args.length == 2 || args.length == 3)){
                           throw new InvalidNumberArgumentsException();
                       }
                       break;
                   case "STR" :
                       if(!(args.length == 2 || args.length == 3)){
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
                        break;
                   default:
                       System.out.println("Command not found/Unknown command");
                       break;
           }
        }
    }


    public void add(String command){
        info.add(command);
    }

    public void remove(String command) {
        info.remove(command);
    }
}
