package chromatynk.chromatynk_g6.exceptions;

public class MissingCursorException extends CursorException {
    public MissingCursorException(String msg){
        super(msg);
    }

    public MissingCursorException(){
        super("The cursor doesn't exists or is missing!");
    }
}
