package chromatynk.chromatynk_g6.parameters.booleanExp;

public class NotExpression extends BooleanExpression{
    public NotExpression(BooleanExpression booleanExpression){
        super(!booleanExpression.evaluate());
    }
}
