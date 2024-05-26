package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;

/**
 * A THICK statement's representation
 */
public class ThickStatement extends Statement{
    private ArithmeticExpression expression;

    public ThickStatement(ArithmeticExpression expression, LYnkVariableImpl varContext){
        super("THICK", varContext);
        this.expression = expression;
    }

    public ArithmeticExpression getExpression() {
        return expression;
    }

    @Override
    public String toString(){
        return "THICK " + expression.toString() + "\n";
    }
}
