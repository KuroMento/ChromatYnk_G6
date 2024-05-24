package chromatynk.chromatynk_g6.parameters.booleanExp;

public class BooleanExpression {
    private boolean value;

    public BooleanExpression(boolean value){
        this.value = value;
    }

    public boolean evaluate(){
        return this.value;
    }

    public boolean getValue(){ return this.value; }
}
