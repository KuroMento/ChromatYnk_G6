package chromatynk.chromatynk_g6.parameters.arithmeticExp;

import chromatynk.chromatynk_g6.parameters.NumberOperator;

public class OperationExpression extends ArithmeticExpression{
    private ArithmeticExpression left;
    private ArithmeticExpression right;
    private NumberOperator operator;

    /** constructor
     *
     * @param left
     * @param operator
     * @param right
     */
    public OperationExpression(ArithmeticExpression left, NumberOperator operator, ArithmeticExpression right){
        super(0d);
        this.left = left;
        this.operator = operator;
        this.right = right;
        setValue(compare());
    }
    /**
     * This method returns a boolean.
     * @return
     */
    @Override
    public double evaluate(){
        return compare();
    }
    /**
     * This method compares and evaluates the right and left part of the entering command.
     * @return
     */
    public double compare(){
        final double leftValue = this.left.evaluate();
        final double rightValue = this.right.evaluate();
        switch (this.operator){
            case PLUS: return leftValue + rightValue;
            case MINUS: return leftValue - rightValue;
            case MULTIPLICATION: return leftValue * rightValue;
            case DIVISION: return leftValue / rightValue;
        }
        return getValue();
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
