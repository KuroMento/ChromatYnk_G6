package chromatynk.chromatynk_g6.exceptions;

public class InvalidNumberArgumentsException extends Exception{
    public InvalidNumberArgumentsException(){
        super("Too many or too few arguments entered");
    }
}
