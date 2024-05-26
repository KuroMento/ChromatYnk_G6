package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;

/**
 * A MIRROR statement's representation
 */
public class MirrorStatement extends Statement{
    private ArithmeticExpression x1;
    private ArithmeticExpression  y1;
    private ArithmeticExpression  x2;
    private ArithmeticExpression  y2;
    private boolean isCentralMirror;
    private BlockStatement block;

    /**
     * Constructor of MirrorStatement for a central mirror.
     * @param x1 The horizontal value of the first point
     * @param y1 The vertical value of the first point
     * @param x2 The horizontal value of the second point
     * @param y2 The vertical value of the second point
     * @param block The block for the statement
     * @param varContext The variable context
     */
    public MirrorStatement(ArithmeticExpression x1, ArithmeticExpression  y1, ArithmeticExpression  x2, ArithmeticExpression  y2, BlockStatement block, LYnkVariableImpl varContext){
        super("MIRROR", varContext);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.block = block;
        this.isCentralMirror = false;
    }

    /**
     * Constructor of MirrorStatement for a central mirror.
     * @param x1 The horizontal value of the first point
     * @param y1 The vertical value of the first point
     * @param block The block for the statement
     * @param varContext The variable context
     */
    public MirrorStatement(ArithmeticExpression  x1, ArithmeticExpression  y1, BlockStatement block, LYnkVariableImpl varContext){
        super("MIRROR", varContext);
        this.x1 = x1;
        this.y1 = y1;
        this.block = block;
        this.isCentralMirror = true;
    }
    public ArithmeticExpression  getX1() {
        return x1;
    }

    public ArithmeticExpression  getY1() {
        return y1;
    }

    public ArithmeticExpression  getX2() {
        return x2;
    }

    public ArithmeticExpression  getY2() {
        return y2;
    }

    public BlockStatement getBlock(){ return this.block; }

    /**
     *  Check if the mirror is a central symmetry
     * @return
     */
    public boolean isCentralMirror(){ return this.isCentralMirror; }

    /**
     * This method convert to a String and return it
     * @return
     */
    @Override
    public String toString() {
        if(isCentralMirror()) {
            return super.toString() + " " + this.x1 + " " + this.y1 + " " + this.block + "\n";
        }
        return super.toString() + " " + this.x1 + " " + this.y1 + " " + this.x2 + " " + this.y2 + " " + this.block + "\n";
    }
}