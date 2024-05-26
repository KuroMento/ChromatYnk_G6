package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.booleanExp.BooleanExpression;
import chromatynk.chromatynk_g6.parameters.booleanExp.BooleanParenthesis;

/**
 * A IF statement's representation
 */
public class IfStatement extends Statement{
    private BooleanExpression expression;
    private BlockStatement block;

    /**
     * Constructor of IfStatement
     * @param expression The boolean expression
     * @param block The block statement
     * @param varContext The variable context
     */
    public IfStatement(BooleanExpression expression, BlockStatement block, LYnkVariableImpl varContext){
        super("IF", varContext);
        this.expression = expression;
        this.block = block;
    }

    public BooleanExpression getExpression() {
        return expression;
    }

    public BlockStatement getBlock() {
        return block;
    }

    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString() {
        if( this.expression instanceof BooleanParenthesis){
            return super.toString() + this.expression.toString() + this.block.toString() + "\n";
        }
        return super.toString() + " (" + this.expression.toString() + ")" + this.block.toString() + "\n";
    }
}
