package chromatynk.chromatynk_g6.parameters.booleanExp;

public class FalseExpression extends BooleanExpression{
    public FalseExpression(){
        super(false);
    }
    @Override
    public boolean evaluate(){
        return getValue();
    }

    @Override
    public String toString(){ return "FALSE"; }
}
