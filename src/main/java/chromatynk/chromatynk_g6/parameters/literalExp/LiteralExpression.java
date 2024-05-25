package chromatynk.chromatynk_g6.parameters.literalExp;

public class LiteralExpression {
    private String value;

    public LiteralExpression(String value){
        this.value = value;
    }

    public String evaluate(){
        return this.value;
    }

    @Override
    public String toString(){ return this.value; }
}
