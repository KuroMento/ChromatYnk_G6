package chromatynk.chromatynk_g6.parameters.arithmeticExp;

public class ArithmeticExpression {
    private double value;

    public ArithmeticExpression(double value){
        this.value = value;
    }

    public double evaluate(){
        return this.value;
    }

    public double getValue(){ return this.value; }
    public void setValue(double value){ this.value = value; }

    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
