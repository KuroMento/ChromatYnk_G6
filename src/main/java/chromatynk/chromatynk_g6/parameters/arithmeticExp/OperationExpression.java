package chromatynk.chromatynk_g6.parameters.arithmeticExp;

import chromatynk.chromatynk_g6.parameters.NumberOperator;

public class OperationExpression extends ArithmeticExpression{
    private ArithmeticExpression left;
    private ArithmeticExpression right;
    private NumberOperator operator;

    public OperationExpression(ArithmeticExpression left, NumberOperator operator, ArithmeticExpression right){
        super(0d);
        this.left = left;
        this.operator = operator;
        this.right = right;
        setValue(compare());
    }

    @Override
    public double evaluate(){
        return compare();
    }

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

    @Override
    public String toString(){
        return left.toString() + operator.toString() + right.toString();
    }

}
