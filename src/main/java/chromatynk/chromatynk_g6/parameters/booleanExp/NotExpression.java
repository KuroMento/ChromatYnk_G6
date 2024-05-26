package chromatynk.chromatynk_g6.parameters.booleanExp;

public class NotExpression extends BooleanExpression{
    private BooleanExpression boolExp;

    /** constructor
     *
     * @param booleanExpression
     */
    public NotExpression(BooleanExpression booleanExpression){
        super(!booleanExpression.evaluate());
        this.boolExp = booleanExpression;
        setExpression(toString());
    }
    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString(){ return "NOT " + boolExp.toString(); }
}
