package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;

/**
 * A COLOR statement's representation
 */
public class ColorStatement extends Statement{
    private ArithmeticExpression red;
    private ArithmeticExpression green;
    private ArithmeticExpression blue;

    private String hexcode;

    /**
     * Constructor of ColorStatement if the color is on format RGB
     * @param red R color parameter
     * @param green G color parameter
     * @param blue B color parameter
     * @param varContext The variable context
     */
    public ColorStatement(ArithmeticExpression red, ArithmeticExpression green, ArithmeticExpression blue, LYnkVariableImpl varContext){
        super("COLOR", varContext);
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Construction of ColorStatement if the color is on format hexadecimal
     * @param colorHex the hexadecimal color
     * @param varContext The variable context
     */
    public ColorStatement(String colorHex, LYnkVariableImpl varContext){
        super("COLOR", varContext);
        this.hexcode = colorHex;
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

    public String getHexcode(){ return this.hexcode; }

    public boolean isHexCode(){ return this.hexcode != null; }
    public boolean isHSV(){ return (0 <= this.red.evaluate() && this.red.evaluate() <= 1) && (0 <= this.green.evaluate() && this.green.evaluate() <= 1) && (0 <= this.blue.evaluate() && this.blue.evaluate() <= 1); }
    public boolean isRGB(){ return (0 <= this.red.evaluate() && this.red.evaluate() <= 255) && (0 <= this.green.evaluate() && this.green.evaluate() <= 255) && (0 <= this.blue.evaluate() && this.blue.evaluate() <= 255);}

    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString() {
        if( isHexCode()) {
            return super.toString() + " " + hexcode + "\n";
        }
        if( isRGB() ) {
            return super.toString() + " " + this.red + " " + this.green + " " + this.blue + "\n";
        }
        return super.toString() + " " + this.red + " " + this.green + " " + this.blue + "\n";
    }
}
