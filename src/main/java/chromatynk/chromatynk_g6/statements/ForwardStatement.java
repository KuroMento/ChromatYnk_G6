package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;

public class ForwardStatement extends Statement{
    private ArithmeticExpression expression;
    public ForwardStatement(ArithmeticExpression expression, LYnkVariableImpl varContext){
        super("FWD", varContext);
        this.expression = expression;
    }

    public ArithmeticExpression getExpression() {
        return this.expression;
    }
}
