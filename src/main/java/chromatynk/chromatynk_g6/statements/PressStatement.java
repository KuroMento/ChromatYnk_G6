package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;


/**
 * A PRESS statement's representation
 */
public class PressStatement extends Statement{
    private ArithmeticExpression expression;

    /**
     * Constructor of PressStatement
     * @param expression The arithmetic expression
     * @param varContext The variable context
     */
    public PressStatement(ArithmeticExpression expression, LYnkVariableImpl varContext){
        super("PRESS", varContext);
        this.expression = expression;
    }

    public ArithmeticExpression getExpression() {
        return expression;
    }
    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + "  " + this.expression.toString() + " \n";
    }
}
