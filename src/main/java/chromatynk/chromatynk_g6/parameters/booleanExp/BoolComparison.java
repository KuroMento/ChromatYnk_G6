package chromatynk.chromatynk_g6.parameters.booleanExp;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.BoolOperator;

public class BoolComparison extends BooleanExpression{
    private BooleanExpression left;
    private BooleanExpression right;
    private BoolOperator operator;

    /** constructor
     *
     * @param left
     * @param operator
     * @param right
     */
    public BoolComparison(BooleanExpression left, BoolOperator operator, BooleanExpression right){
        super(true);
        this.left = left;
        this.operator = operator;
        this.right = right;
        setValue(compare());
        setExpression(toString());
    }
    /**
     * This method returns a boolean.
     * @return
     */
    @Override
    public boolean evaluate(){
        return compare();
    }
    /**
     * This method compares and evaluates the right and left part of the entering command.
     * @return
     */
    public boolean compare(){
        final boolean leftValue = left.evaluate();
        final boolean rightValue = right.evaluate();
        switch (this.operator){
            case EQUAL: return (leftValue == rightValue);
            case NOT_EQUAL: return (leftValue != rightValue);
            default: return getValue();
        }
    }
    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString(){
        return left.toString() + operator.toString() + right.toString();
    }
}
