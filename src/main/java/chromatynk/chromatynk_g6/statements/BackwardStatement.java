package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;

/**
 * A BWD statement's representation
 */
public class BackwardStatement extends Statement{
    private ArithmeticExpression expression;

    /**
     * Constructor of BackwardStatement
     * @param expression The arithmetic expression
     * @param varContext The variable context
     */
    public BackwardStatement(ArithmeticExpression expression, LYnkVariableImpl varContext){
        super("BWD", varContext);
        this.expression = expression;
    }

    public ArithmeticExpression getExpression() {
        return this.expression;
    }
    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString(){
        return getKeyword() + " " + expression.toString() + "\n";
    }
}
