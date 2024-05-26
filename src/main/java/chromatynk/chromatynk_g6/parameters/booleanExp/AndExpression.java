package chromatynk.chromatynk_g6.parameters.booleanExp;

public class AndExpression extends BooleanExpression{
    private BooleanExpression left;
    private BooleanExpression right;

    public AndExpression(BooleanExpression left, BooleanExpression right){
        super(left.evaluate() && right.evaluate());
        this.left = left;
        this.right = right;
        setExpression(toString());
    }

    @Override
    public boolean evaluate(){
        return this.left.evaluate() && this.right.evaluate();
    }

    @Override
    public String toString(){
        return left.toString() + " AND " + right.toString();
    }
}
