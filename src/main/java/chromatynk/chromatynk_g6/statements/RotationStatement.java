package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;

public class RotationStatement extends Statement{
    private ArithmeticExpression expression;
    public RotationStatement(ArithmeticExpression expression, LYnkVariableImpl varContext){
        super("TURN", varContext);
        this.expression = expression;
    }

    public ArithmeticExpression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.expression.toString();
    }
}
