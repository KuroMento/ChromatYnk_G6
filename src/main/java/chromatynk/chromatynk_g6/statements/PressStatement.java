package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;


/**
 * A PRESS statement's representation
 */
public class PressStatement extends Statement{
    private ArithmeticExpression expression;

    public PressStatement(ArithmeticExpression expression, LYnkVariableImpl varContext){
        super("PRESS", varContext);
        this.expression = expression;
    }

    public ArithmeticExpression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return super.toString() + "  " + this.expression.toString() + " \n";
    }
}
