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
    public Interpreter(String path){
        read(path); //temporary
        this.info = null;
    }

}
