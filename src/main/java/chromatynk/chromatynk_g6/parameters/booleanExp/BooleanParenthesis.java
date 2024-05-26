package chromatynk.chromatynk_g6.parameters.booleanExp;

public class BooleanParenthesis extends BooleanExpression{
    private BooleanExpression boolExp;

    /** constructor
     *
     * @param boolExp
     */
    public BooleanParenthesis(BooleanExpression boolExp){
        super(boolExp.evaluate());
        this.boolExp = boolExp;
        setExpression(toString());
    }
    /**
     * This method returns a boolean.
     * @return
     */
    @Override
    public boolean evaluate(){
        return this.boolExp.evaluate();
    }
    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString(){
        return "(" + boolExp.toString() + ")";
    }
}
