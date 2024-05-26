package chromatynk.chromatynk_g6.parameters.booleanExp;

import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticComparison;
import chromatynk.chromatynk_g6.parameters.literalExp.LiteralComparison;
import chromatynk.chromatynk_g6.parameters.literalExp.LiteralExpression;
import javafx.geometry.Orientation;

public class BooleanExpression {
    private boolean value;
    private String expression;

    /** constructor
     *
     * @param value
     */
    public BooleanExpression(boolean value){
        this.value = value;
    }
    /**
     * This method compares and evaluates the right and left part of the entering command.
     * @return
     */
    public boolean evaluate(){
        return this.value;
    }

    public boolean getValue(){ return this.value; }

    public void setValue(boolean value){ this.value = value; }

    public String getExpression(){ return this.expression; }
    public void setExpression(String expression){ this.expression = expression; }
    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString(){
        return this.expression;
    }

    public BooleanExpression expressionType(BooleanExpression expression){
        if( expression instanceof AndExpression) {
            return (AndExpression) expression;
        }
        if( expression instanceof OrExpression) {
            return (OrExpression) expression;
        }
        if( expression instanceof NotExpression) {
            return (NotExpression) expression;
        }
        return expression;
    }
}
