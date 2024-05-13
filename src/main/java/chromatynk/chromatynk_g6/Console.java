package chromatynk.chromatynk_g6;

import java.util.ArrayList;

public class Console{
    private Interpreter log;
    private ArrayList<String> history;
    private int i = 0;
    /*
    interpreter lit une ligne
    -> Ligne correcte => execution de ligne + ajout historique
    -> Ligne erronée => Aucune execution + ajout erreur historique

     */

    public Console(){
        history = new ArrayList<String>();
    }

    public ArrayList<String> getHistory(){
        return this.history;
    }

    public void setHistory(ArrayList<String> history){
        this.history = history;
    }

    public int getI(){
        return this.i;
    }

    public void setI(int i){
        this.i = i;
    }

    /**
     * Add the entered command or the related error in the historic.
     * @param line The line entered inside the consbole.
     */
    public void addLine(String line){
        history.add(line);
        System.out.println(this.i + history.get(this.i)); //temporary way to show the logged text, the historic will be shown into its own container when implemented
        setI(this.i + 1);
    }
}
