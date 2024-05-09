package chromatynk.chromatynk_g6.exceptions.variableExceptions;

import chromatynk.chromatynk_g6.Variable;

public class VariableDoesNotExistException extends Exception{
    public VariableDoesNotExistException(){
        super();
    }
    public VariableDoesNotExistException(String s){
        super(s);
    }
}
