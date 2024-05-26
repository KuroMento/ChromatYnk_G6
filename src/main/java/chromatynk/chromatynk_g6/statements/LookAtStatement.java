package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;
import chromatynk.chromatynk_g6.parameters.arithmeticExp.ArithmeticExpression;

/**
 * A LOOKAT statement's representation
 */
public class LookAtStatement extends Statement{
    private long cursorId;
    private ArithmeticExpression x;
    private ArithmeticExpression y;
    private boolean isIdLookAt;

    public LookAtStatement(long cursorId, LYnkVariableImpl varContext){
        super("LOOKAT", varContext);
        this.cursorId = cursorId;
        this.isIdLookAt = true;
    }
    public LookAtStatement(ArithmeticExpression x, ArithmeticExpression y, LYnkVariableImpl varContext){
        super("LOOKAT", varContext);
        this.x = x;
        this.y = y;
        this.isIdLookAt = false;
    }

    public long getCursorId() {
        return cursorId;
    }

    public ArithmeticExpression getX() {
        return x;
    }

    public ArithmeticExpression getY() {
        return y;
    }

    public boolean isIdLookAt() {
        return this.isIdLookAt;
    }

    @Override
    public String toString() {
        if(isIdLookAt()){
            return super.toString() + " " + this.cursorId + "\n";
        }
        return super.toString() + " " + this.x + " " + this.y + "\n";
    }
}
