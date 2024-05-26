package chromatynk.chromatynk_g6.parameters.literalExp;

import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;
import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

public class LiteralVariable extends LiteralExpression{
    private LYnkVariableImpl varContext;
    private String identification;

    /** constructor
     *
     * @param value
     * @param varContext
     * @param identification
     */
    public LiteralVariable(String value, LYnkVariableImpl varContext, String identification){
        super(value);
        this.varContext = varContext;
        this.identification = identification;
    }
    /**
     * This method returns a String.
     * @return
     */

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
    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString(){
        return this.identification;
    }
}
