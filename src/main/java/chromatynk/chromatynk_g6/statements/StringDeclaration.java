package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

public class StringDeclaration extends Statement {
    private String variableName;
    private String expression;
    public StringDeclaration(String variableName, String expression, LYnkVariableImpl varContext){
        super("STR", varContext);
        this.variableName = variableName;
        this.expression = expression;
    }
    public StringDeclaration(String variableName, LYnkVariableImpl varContext){
        super("STR", varContext);
        this.variableName = variableName;
        this.expression = "";
    }

    public String getVariableName() {
        return variableName;
    }

    public String getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.variableName + " " + this.expression.toString();
    }
}