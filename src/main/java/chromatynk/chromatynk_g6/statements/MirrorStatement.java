package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;

public class MirrorStatement extends Statement{
    private ArithmeticExpression x1;
    private ArithmeticExpression  y1;
    private ArithmeticExpression  x2;
    private ArithmeticExpression  y2;
    private boolean isCentralMirror;
    private BlockStatement block;
    public MirrorStatement(ArithmeticExpression x1, ArithmeticExpression  y1, ArithmeticExpression  x2, ArithmeticExpression  y2, BlockStatement block, LYnkVariableImpl varContext){
        super("MIRROR", varContext);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.block = block;
        this.isCentralMirror = false;
    }
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

    public boolean isCentralMirror(){ return this.isCentralMirror; }

    @Override
    public String toString() {
        if(isCentralMirror()) {
            return super.toString() + " " + this.x1 + " " + this.y1;
        }
        return super.toString() + " " + this.x1 + " " + this.y1 + " " + this.x2 + " " + this.y2;
    }
}