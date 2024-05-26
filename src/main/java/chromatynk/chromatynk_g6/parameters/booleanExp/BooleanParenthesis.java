package chromatynk.chromatynk_g6.parameters.booleanExp;

public class BooleanParenthesis extends BooleanExpression{
    private BooleanExpression boolExp;

    public BooleanParenthesis(BooleanExpression boolExp){
        super(boolExp.evaluate());
        this.boolExp = boolExp;
        setExpression(toString());
    }
    @Override
    public boolean evaluate(){
        return this.boolExp.evaluate();
    }

    @Override
    public String toString(){
        return "(" + boolExp.toString() + ")";
    }
}
