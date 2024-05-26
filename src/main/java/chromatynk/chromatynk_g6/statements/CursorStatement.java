package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;

/**
 * A CURSOR statement's representation
 */
public class CursorStatement extends Statement{
    private long cursorId;
    public CursorStatement(long cursorId, LYnkVariableImpl varContext){
        super("CURSOR", varContext);
        this.cursorId = cursorId;
    }

    public long getCursorId() {
        return this.cursorId;
    }

    @Override
    public String toString() {
        return super.toString() + " " + cursorId + "\n";
    }
}
