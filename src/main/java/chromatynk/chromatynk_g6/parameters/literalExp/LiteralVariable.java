package chromatynk.chromatynk_g6.parameters.literalExp;

import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;
import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

public class LiteralVariable extends LiteralExpression{
    private LYnkVariableImpl varContext;
    public LiteralVariable(String value, LYnkVariableImpl varContext){
        super(value);
        this.varContext = varContext;
    }

    @Override
    public String evaluate(){
        try{
            final String result = this.varContext.getStrVarValue(super.evaluate());
            return result;
        }
        catch( VariableDoesNotExistException e){
            return super.evaluate();
        }
    }
}
