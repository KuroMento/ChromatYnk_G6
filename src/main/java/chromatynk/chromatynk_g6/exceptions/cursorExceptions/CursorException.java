package chromatynk.chromatynk_g6.exceptions.cursorExceptions;

public class CursorException extends Exception{

    public CursorException(){
        super("An Exception was thrown due to a Cursor class");
    }
    public CursorException(String msg){
        super(msg);
    }
}
