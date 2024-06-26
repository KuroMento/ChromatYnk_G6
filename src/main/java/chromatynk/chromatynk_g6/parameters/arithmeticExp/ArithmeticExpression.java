package chromatynk.chromatynk_g6.parameters.arithmeticExp;

public class ArithmeticExpression {
    private double value;

    /**
     * Constructor
     * @param value
     */
    public ArithmeticExpression(double value){
        this.value = value;
    }
    /**
     * This method returns a boolean.
     * @return
     */
    public double evaluate(){
        return this.value;
    }

    public double getValue(){ return this.value; }
    public void setValue(double value){ this.value = value; }
    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString(){
        if( this instanceof ArithmeticVariable variable){
            return variable.toString();
        }
        if( this instanceof OperationExpression operationExpression){
            return operationExpression.toString();
        }
        return String.valueOf(value);
    }
}
