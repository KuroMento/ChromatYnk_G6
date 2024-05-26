package chromatynk.chromatynk_g6.parameters.arithmeticExp;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.ArithmeticOperator;
import chromatynk.chromatynk_g6.parameters.booleanExp.BooleanExpression;
import chromatynk.chromatynk_g6.parameters.literalExp.LiteralExpression;

public class ArithmeticComparison extends BooleanExpression {
    private ArithmeticExpression left;
    private ArithmeticExpression right;

    private ArithmeticOperator operator;

    /**
     * constructor
     * @param left
     * @param operator
     * @param right
     */
    public ArithmeticComparison(ArithmeticExpression left, ArithmeticOperator operator, ArithmeticExpression right){
        super(true);
        this.left = left;
        this.right = right;
        this.operator = operator;
        final boolean compValue = compare();
        setValue(compValue);
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
        final double leftValue = this.left.evaluate();
        final double rightValue = this.right.evaluate();
        switch (this.operator){
            case LESS :
                if( leftValue < rightValue){
                    return true;
                }
                else{
                    return false;
                }
            case GREATER :
                if( leftValue > rightValue){
                    return true;
                }
                else{
                    return false;
                }
            case LESS_EQUAL:
                if( leftValue <= rightValue){
                    return true;
                }
                else{
                    return false;
                }
            case GREATER_EQUAL:
                if( leftValue >= rightValue){
                    return true;
                }
                else{
                    return false;
                }
            case EQUAL:
                if( leftValue == rightValue ){
                    return true;
                }
                else{
                    return false;
                }
            case NOT_EQUAL:
                if( leftValue != rightValue ){
                    return true;
                }
                else{
                    return false;
                }
            default:
                return getValue();
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
