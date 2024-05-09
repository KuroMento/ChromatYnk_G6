package chromatynk.chromatynk_g6.exceptions.cursorExceptions;

public class CursorAlreadyExistingException extends CursorException {
    public CursorAlreadyExistingException(){
        super("This id is already taken, please chose an other one");
    }

    public CursorAlreadyExistingException(String msg){
        super(msg);
    }
}
