package chromatynk.chromatynk_g6.parameters.literalExp;

import chromatynk.chromatynk_g6.diagnostic.LYnkValidation;
import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.ArithmeticOperator;
import chromatynk.chromatynk_g6.parameters.booleanExp.BooleanExpression;

public class LiteralComparison extends BooleanExpression {
    private LiteralExpression left;
    private LiteralExpression right;

    private ArithmeticOperator operator;

    /** constructor
     *
     * @param left
     * @param operator
     * @param right
     */
    public LiteralComparison(LiteralExpression left, ArithmeticOperator operator, LiteralExpression right){
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
        final int comparison = left.evaluate().compareTo(right.evaluate());
        switch (this.operator){
            case LESS :
                if( comparison < 0){
                    return true;
                }
                else{
                    return false;
                }
            case GREATER :
                if( comparison > 0){
                    return true;
                }
                else{
                    return false;
                }
            case LESS_EQUAL:
                if( comparison <= 0){
                    return true;
                }
                else{
                    return false;
                }
            case GREATER_EQUAL:
                if( comparison >= 0){
                    return true;
                }
                else{
                    return false;
                }
            case EQUAL:
                if( comparison == 0 ){
                    return true;
                }
                else{
                    return false;
                }
            case NOT_EQUAL:
                if( comparison != 0 ){
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
