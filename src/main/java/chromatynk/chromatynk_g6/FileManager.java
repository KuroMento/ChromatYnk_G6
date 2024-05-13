package chromatynk.chromatynk_g6;

import chromatynk.chromatynk_g6.exceptions.EmptyFileException;

import java.io.*;

public class FileManager {

    private String currentFile;
    /**
     * The unique interpreter in the project to manage file and requests.
     */
    private Interpreter interpreter;

    public FileManager(){
        this.interpreter = new Interpreter();
    }

    public Interpreter getInterpreter(){return this.interpreter;}
    private void setInterpreter(Interpreter interpreter) {this.interpreter = interpreter;}
    public void  setCurrentFile(String file){
        this.currentFile = file;
    }
    public String getCurrentFile(){
        return this.currentFile;
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
                this.interpreter.getInfo().add(temp); //allows to stock entries in the history
            }
            br.close();
            this.interpreter.executeAllInfo();
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error :" + e);
        }
    }

    /**
     * Use the history to generate a file.
     * @param path the path of new file.
     */
    public void save(String path) throws EmptyFileException {
        try {
            if (this.interpreter.getConsole().getHistory().isEmpty()){
                throw new EmptyFileException();
            }
            FileWriter fw = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(fw);

            for (String line : this.interpreter.getConsole().getHistory()) {
                    bw.write(line);
                    bw.newLine();
            }
            bw.close();
        }
        catch (Exception e){
            System.out.println("Error " + e);
        }
    }
}

