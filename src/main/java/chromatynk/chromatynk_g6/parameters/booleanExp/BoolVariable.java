package chromatynk.chromatynk_g6.parameters.booleanExp;

import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;
import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

public class BoolVariable extends BooleanExpression{
    private LYnkVariableImpl varContext;
    private String identification;

    public BoolVariable(String identification, LYnkVariableImpl varContext, boolean value){
        super(value);
        this.identification = identification;
        this.varContext = varContext;
    }

    @Override
    public boolean evaluate(){
        try{
            final Boolean bool = this.varContext.getBoolVarValue(this.identification);
            final boolean result = bool.booleanValue();
            return result;
        }
        catch(VariableDoesNotExistException e){
            return getValue();
        }
    }
}
