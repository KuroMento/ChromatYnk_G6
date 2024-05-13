package chromatynk.chromatynk_g6.exceptions;

import java.util.EmptyStackException;

public class EmptyFileException extends Exception{
    public EmptyFileException(){
        super();
    }
    public EmptyFileException(String msg){
        super(msg);
    }
}
