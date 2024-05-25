package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;

public class MoveStatement extends Statement{
    private ArithmeticExpression x;
    private ArithmeticExpression y;
    public MoveStatement(ArithmeticExpression x, ArithmeticExpression y, LYnkVariableImpl varContext){
        super("MOV", varContext);
        this.x = x;
        this.y = y;
    }

    public ArithmeticExpression getX() {
        return x;
    }

    public ArithmeticExpression getY() {
        return y;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.x.toString() + " " + this.y.toString();
    }
}
