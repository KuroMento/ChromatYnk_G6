package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;

/**
 * A POS statement's representation
 */
public class PositionStatement extends Statement{
    private ArithmeticExpression x;
    private ArithmeticExpression y;

    /**
     * Constructor of PositionStatement
     * @param x The horizontal position
     * @param y The vertical position
     * @param varContext The variable context
     */
    public PositionStatement(ArithmeticExpression x, ArithmeticExpression y, LYnkVariableImpl varContext){
        super("POS", varContext);
        this.x = x;
        this.y = y;
    }

    public ArithmeticExpression getX() {
        return x;
    }

    public ArithmeticExpression getY() {
        return y;
    }

    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + " " + this.x.toString() + " " + this.y.toString() + "\n";
    }
}
