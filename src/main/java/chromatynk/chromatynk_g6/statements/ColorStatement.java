package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;

public class ColorStatement extends Statement{
    private ArithmeticExpression red;
    private ArithmeticExpression green;
    private ArithmeticExpression blue;
    public ColorStatement(ArithmeticExpression red, ArithmeticExpression green, ArithmeticExpression blue, LYnkVariableImpl varContext){
        super("COLOR", varContext);
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public ArithmeticExpression getRed() {
        return red;
    }

    public ArithmeticExpression getGreen() {
        return green;
    }

    public ArithmeticExpression getBlue() {
        return blue;
    }
}
