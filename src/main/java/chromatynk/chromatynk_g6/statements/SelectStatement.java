package chromatynk.chromatynk_g6.statements;

import chromatynk.chromatynk_g6.interpreter.LYnkVariableImpl;


/**
 * A SELECT statement's representation
 */
public class SelectStatement extends Statement{
    private long cursorId;
    public SelectStatement(long cursorId, LYnkVariableImpl varContext){
        super("SELECT", varContext);
        this.cursorId = cursorId;
    }

    public long getCursorId() {
        return cursorId;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.cursorId + "\n";
    }
}
