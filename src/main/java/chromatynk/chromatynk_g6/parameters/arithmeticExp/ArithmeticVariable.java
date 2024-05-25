package chromatynk.chromatynk_g6.parameters.arithmeticExp;

import chromatynk.chromatynk_g6.exceptions.variableExceptions.VariableDoesNotExistException;
import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

public class ArithmeticVariable extends ArithmeticExpression{
    private LYnkVariableImpl varContext;
    private String identification;

    public ArithmeticVariable(String identification, LYnkVariableImpl varContext, double value){
        super(value);
        this.identification = identification;
        this.varContext = varContext;
    }

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

    @Override
    public String toString(){
        return this.identification;
    }
}
