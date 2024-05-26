package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

/**
 * A STRING declaration's representation
 */
public class StringDeclaration extends Statement {
    private String variableName;
    private String expression;

    /**
     * Constructor of StringDeclaration
     * @param variableName The variable name
     * @param expression The string expression
     * @param varContext The variable context
     */
    public StringDeclaration(String variableName, String expression, LYnkVariableImpl varContext){
        super("STR", varContext);
        this.variableName = variableName;
        this.expression = expression;
    }

    /**
     * Constructor of StringDeclaration
     * @param variableName The variable name
     * @param varContext The variable context
     */
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

    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + " " + this.variableName + " = " + this.expression.toString() + "\n";
    }
}