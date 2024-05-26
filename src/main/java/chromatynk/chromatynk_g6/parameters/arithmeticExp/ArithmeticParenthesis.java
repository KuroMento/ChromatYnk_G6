package chromatynk.chromatynk_g6.parameters.arithmeticExp;

public class ArithmeticParenthesis extends ArithmeticExpression{
    private ArithmeticExpression arithmetic;

    /** constructor
     *
     * @param arithmetic
     */
    public ArithmeticParenthesis(ArithmeticExpression arithmetic){
        super(arithmetic.evaluate());
        this.arithmetic = arithmetic;
    }
    /**
     * This method returns a boolean.
     * @return
     */
    @Override
    public double evaluate(){
        return this.arithmetic.evaluate();
    }
    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString(){
        return "(" + arithmetic.toString() + ")";
    }
}
