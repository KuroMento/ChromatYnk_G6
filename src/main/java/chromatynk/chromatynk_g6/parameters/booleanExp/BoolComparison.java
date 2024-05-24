package chromatynk.chromatynk_g6.parameters.booleanExp;

import chromatynk.chromatynk_g6.parameters.BoolOperator;

public class BoolComparison extends BooleanExpression{
    private BooleanExpression left;
    private BooleanExpression right;
    private BoolOperator operator;

    public BoolComparison(BooleanExpression left, BoolOperator operator, BooleanExpression right){
        super(true);
        this.left = left;
        this.operator = operator;
        this.right = right;
        setValue(compare());
    }

    @Override
    public boolean evaluate(){
        return compare();
    }

    public boolean compare(){
        final boolean leftValue = left.evaluate();
        final boolean rightValue = right.evaluate();
        switch (this.operator){
            case EQUAL: return (leftValue == rightValue);
            case NOT_EQUAL: return (leftValue != rightValue);
            default: return getValue();
        }
    }
}
