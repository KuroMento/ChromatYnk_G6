package chromatynk.chromatynk_g6.parameters.booleanExp;

public class TrueExpression extends BooleanExpression{
    public TrueExpression(){
        super(true);
    }

    @Override
    public boolean evaluate(){
        return getValue();
    }
}
