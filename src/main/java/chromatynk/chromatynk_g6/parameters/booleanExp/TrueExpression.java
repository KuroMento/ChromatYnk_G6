package chromatynk.chromatynk_g6.parameters.booleanExp;

public class TrueExpression extends BooleanExpression{
    public TrueExpression(){
        super(true);
        setExpression(toString());
    }

    @Override
    public boolean evaluate(){
        return getValue();
    }

    @Override
    public String toString(){ return "TRUE"; }
}
