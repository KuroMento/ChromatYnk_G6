package chromatynk.chromatynk_g6.parameters.arithmeticExp;

import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;
import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

public class ArithmeticVariable extends ArithmeticExpression{
    private LYnkVariableImpl varContext;
    private String identification;

    /** constructor
     *
     * @param identification
     * @param varContext
     * @param value
     */
    public ArithmeticVariable(String identification, LYnkVariableImpl varContext, double value){
        super(value);
        this.identification = identification;
        this.varContext = varContext;
    }
    /**
     * This method returns a boolean.
     * @return
     */
    @Override
    public double evaluate(){
        try{
            final Double result = this.varContext.getNumVarValue(this.identification);
            return result.doubleValue();
        }
        catch (VariableDoesNotExistException e){
            return getValue();
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
