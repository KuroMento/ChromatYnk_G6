package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;

public class BackwardStatement extends Statement{
    private ArithmeticExpression expression;
    public BackwardStatement(ArithmeticExpression expression, LYnkVariableImpl varContext){
        super("BWD", varContext);
        this.expression = expression;
    }

    public ArithmeticExpression getExpression() {
        return this.expression;
    }
}
