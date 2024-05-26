package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;

/**
 * A NUM declaration's representation
 */
public class NumberDeclaration extends Statement{
    private String variableName;
    private ArithmeticExpression expression;
    public NumberDeclaration(String variableName, ArithmeticExpression expression, LYnkVariableImpl varContext){
        super("NUM", varContext);
        this.variableName = variableName;
        this.expression = expression;
    }
    public NumberDeclaration(String variableName, LYnkVariableImpl varContext){
        super("NUM", varContext);
        this.variableName = variableName;
        this.expression = new ArithmeticExpression(0);
    }

    public String getVariableName() {
        return variableName;
    }

    public ArithmeticExpression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.variableName + " = " + this.expression.toString() + "\n";
    }
}
