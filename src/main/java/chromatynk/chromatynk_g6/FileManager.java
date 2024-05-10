package chromatynk.chromatynk_g6;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private String fileName ;


    /**
     * Write a line into a file
     * @param file the file to save the canvas
     * @param line the line to add into file
     */
    public void writeIntoFile(String file, String line){
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(line);

            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
