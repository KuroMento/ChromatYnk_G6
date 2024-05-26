package chromatynk.chromatynk_g6.parameters.booleanExp;

public class OrExpression extends BooleanExpression{
    private BooleanExpression left;
    private BooleanExpression right;

    /** constructor
     * 
     * @param left
     * @param right
     */
    public OrExpression(BooleanExpression left, BooleanExpression right){
        super(left.evaluate() || right.evaluate());
        this.left = left;
        this.right = right;
        setExpression(toString());
    }
    /**
     * This method returns a boolean.
     * @return
     */
    @Override
    public boolean evaluate(){
        return this.left.evaluate() || this.right.evaluate();
    }
    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString(){
        return left.toString() + " OR " + right.toString();
    }
}
