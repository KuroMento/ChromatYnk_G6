package chromatynk.chromatynk_g6.parameters.arithmeticExp;

public class ArithmeticParenthesis extends ArithmeticExpression{
    private ArithmeticExpression arithmetic;

    public ArithmeticParenthesis(ArithmeticExpression arithmetic){
        super(arithmetic.evaluate());
        this.arithmetic = arithmetic;
    }

    @Override
    public double evaluate(){
        return this.arithmetic.evaluate();
    }

    @Override
    public String toString(){
        return "(" + arithmetic.toString() + ")";
    }
}
