package chromatynk.chromatynk_g6.exceptions.cursorExceptions;

public class MimicStackEmptyException extends CursorException{
    public MimicStackEmptyException(String msg){
        super(msg);
    }

    public MimicStackEmptyException(){
        super("This cursor does not have mimics!");
    }
}
