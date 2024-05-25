package chromatynk.chromatynk_g6.parameters.literalExp;

import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;
import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

public class LiteralVariable extends LiteralExpression{
    private LYnkVariableImpl varContext;
    private String identification;
    public LiteralVariable(String value, LYnkVariableImpl varContext, String identification){
        super(value);
        this.varContext = varContext;
        this.identification = identification;
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

    @Override
    public String toString(){
        return this.identification;
    }
}
