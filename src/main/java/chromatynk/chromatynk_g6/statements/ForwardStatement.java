package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;

/**
 * A FWD statement's representation
 */
public class ForwardStatement extends Statement{
    private ArithmeticExpression expression;
    public ForwardStatement(ArithmeticExpression expression, LYnkVariableImpl varContext){
        super("FWD", varContext);
        this.expression = expression;
    }

    public ArithmeticExpression getExpression() {
        return this.expression;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.expression.toString() + "\n";
    }
}
