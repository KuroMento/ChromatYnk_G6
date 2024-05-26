package chromatynk.chromatynk_g6.parameters.booleanExp;

public class NotExpression extends BooleanExpression{
    private BooleanExpression boolExp;
    public NotExpression(BooleanExpression booleanExpression){
        super(!booleanExpression.evaluate());
        this.boolExp = booleanExpression;
        setExpression(toString());
    }

    @Override
    public String toString(){ return "NOT " + boolExp.toString(); }
}
