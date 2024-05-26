package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.booleanExp.BooleanExpression;
import chromatynk.chromatynk_g6.parameters.booleanExp.FalseExpression;

/**
 * A BOOL declaration's representation
 */
public class BoolDeclaration extends Statement{
    private String variableName;
    private BooleanExpression expression;
    public BoolDeclaration(String variableName, BooleanExpression expression, LYnkVariableImpl varContext){
        super("BOOL", varContext);
        this.variableName = variableName;
        this.expression = expression;
    }
    public BoolDeclaration(String variableName, LYnkVariableImpl varContext){
        super("BOOL", varContext);
        this.variableName = variableName;
        this.expression = new FalseExpression();
    }

    public String getVariableName() {
        return variableName;
    }

    public BooleanExpression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.variableName + " = " + this.expression.toString() + "\n";
    }
}
