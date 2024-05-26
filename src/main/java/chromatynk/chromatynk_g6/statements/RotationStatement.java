package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;


/**
 * A TURN statement's representation
 */
public class RotationStatement extends Statement{
    private ArithmeticExpression expression;

    /**
     * Constructor of RotationStatement
     * @param expression The arithmetic expression
     * @param varContext The variable context
     */
    public RotationStatement(ArithmeticExpression expression, LYnkVariableImpl varContext){
        super("TURN", varContext);
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
        return super.toString() + " " + this.expression.toString() + " \n";
    }
}
