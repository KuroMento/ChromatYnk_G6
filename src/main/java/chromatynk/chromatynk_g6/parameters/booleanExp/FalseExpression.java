package chromatynk.chromatynk_g6.parameters.booleanExp;

public class FalseExpression extends BooleanExpression{
    /** constructor
     *
     */
    public FalseExpression(){
        super(false);
        setExpression(toString());
    }
    /**
     * This method returns a boolean.
     * @return
     */
    @Override
    public boolean evaluate(){
        return getValue();
    }
    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString(){ return "FALSE"; }
}
